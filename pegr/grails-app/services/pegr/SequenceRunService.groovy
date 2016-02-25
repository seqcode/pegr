package pegr
import grails.converters.XML
import grails.transaction.Transactional

class SequenceRunException extends RuntimeException {
    String message
}

class SequenceRunService {
    def springSecurityService
    def grailsApplication
    
    @Transactional
    void save(SequenceRun run) {
        run.lane = 0
        run.user = springSecurityService.currentUser
        run.status = RunStatus.PREP
        if(!run.save(flush: true)) {
            throw new SequenceRunException(message: "Invalid input!")
        }
    }
    
    @Transactional
    def addSample(Long sampleId, Long runId) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new SequenceRunException(message: "Sample not found!")
        }
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }
        if (SequencingExperiment.findBySampleAndSequenceRun(sample, run)) {
            throw new SequenceRunException(message: "Sample already included in this run!")
        }
        def experiment = new SequencingExperiment(sample: sample, sequenceRun: run) 
        if (!experiment.save(flush: true)) {
            throw new SequenceRunException(message: "Error including this sample to the sequence run!")
        }        
    }
    
    @Transactional
    List addBag(Long bagId, Long runId) {
        def bag = ProtocolInstanceBag.get(bagId)
        if (!bag) {
            throw new SequenceRunException(message: "Protocol instance bag not found!")
        }        
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }
        def messages = []
        bag.tracedSamples.each {
            if (SequencingExperiment.findBySampleAndSequenceRun(it, run)) {
                messages.push("Sample already included in this run!")
            } else {
                def experiment = new SequencingExperiment(sample: it, sequenceRun: run) 
                if (!experiment.save(flush: true)) {
                    message.push("Error including this sample to the sequence run!")
                } 
            }
        }
        return messages
    }
    
    @Transactional
    void removeExperiment(Long experimentId) {
        def experiment = SequencingExperiment.get(experimentId)
        if (experiment) {
            experiment.delete()
        } else {
            throw new SequenceRunException(message: "Experiment not found!")
        }
    }
    
    @Transactional
    void updateGenome(String experimentIdStr, List genomeIds) {
        Long experimentId = Long.parseLong(experimentIdStr) 
        def experiment = SequencingExperiment.get(experimentId) 
        if (!experiment) {
            throw new SequenceRunException(message: "Experiment not found!")
        }
        def toDelete = experiment.alignments
        def toAdd = []
        genomeIds.each { genomeIdStr ->
            def genomeId = Long.parseLong(genomeIdStr)
            def oldAlign = toDelete.find{it.genome.id == genomeId}
            if (oldAlign) {
                toDelete.remove(oldAlign)
            } else {
                def genome = Genome.get(genomeId)
                if (genome) {
                    toAdd.push(new SequenceAlignment(sequencingExperiment: experiment, genome: genome))
                } else {
                    throw new SequenceRunException(message: "Genome #${genomeId} not found for Sample ${experiment.sample.id}!")
                }
            }
        }
        toDelete.each{
            it.delete()
        }
        toAdd.each{
            it.save()
        }
    }
    
    @Transactional
    void run(Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }
        generateRunFiles(run)
        run.status = RunStatus.RUN
        run.save()
    }
    
    void generateRunFiles(SequenceRun run) {
        // make the directory
        def folderName = "files/runInfos/${run.id}"
        File folder = new File(folderName); 
        if (!folder.exists()) { 
            folder.mkdirs(); 
        } 
        File runInfoFile = new File(folder, "cegr_run_info.txt")
        runInfoFile.createNewFile();
        // get parameters
        runInfoFile.withWriter{
            run.experiments.each { experiment -> 
                def xmlNames = []
                experiment.alignments.eachWithIndex { alignment, idx ->
                    def xmlName = generateXmlFile(alignment, run.id, experiment.sample.id, idx, folder)
                    xmlNames.push(xmlName)
                }
                def indicesString = experiment.sample?.sequenceIndices.collect{it.sequence}.join(",")
                def xmlNamesString= xmlNames.join(",")
                def data = "${run.id} ; ${experiment.sample?.id} ; ${indicesString} ; ${xmlNamesString}"           
                it.println data
            }
        }      
    }
    
    String generateXmlFile(SequenceAlignment alignment, Long runId, Long sampleId, int idx, File folder) {
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        def alignmentParams = new Payload( 
            parameters : new Parameters (
                step : [new Step( param: [
                                new Param(name: "reference_genome", value: alignment.genome.name)
                            ])
                       ]
            ))
        def converter = alignmentParams as XML
        converter.render(new java.io.FileWriter(file))
        return filename
    }
    
}

class Payload {
    Parameters parameters
}

class Parameters {
    Step[] step
}

class Step {
    Param[] param
}

class Param {
    String name
    String value
}