package pegr
import grails.gorm.transactions.Transactional
import grails.converters.*
import groovy.json.*

class AlignmentStatsException extends RuntimeException {
    String message
}

/**
* A class to CRUD sequence alignment and analysis, and update the statistics in experiment.
*/
class AlignmentStatsService {
    def utilityService
    
   /**
    * Save the Analysis data accepted from API
    * @param data the data accpeted from API
    * @param apiUser the API user
    * @return the saved analysis
    */
    @Transactional
    def save(StatsRegistrationCommand data, User apiUser) {   
        // check required fields
        checkRequiredFields(data, ["toolCategory", "toolId", "statsToolId"])
        
        def alignment
        if (data.alignmentId) {
            alignment = SequenceAlignment.get(data.alignmentId)
            if (!alignment) {
                throw new AlignmentStatsException(message: "Sequence alignment not found!")
            }
        } else if (data.historyId) {
            alignment = getAlignment(data, apiUser)
        } else {
            throw new AlignmentStatsException(message: "Missing historyId or alignmentId!")
        }

        def analysis = saveAnalysis(data, alignment, apiUser)

        return analysis
    }
    
   /**
    * Create sequence alignment
    * @param data
    * @param apiUser
    * @return created sequence alignment
    */
    def getAlignment(StatsRegistrationCommand data, User apiUser) {
        // check required fields
        checkRequiredFields(data, ["run", "sample", "genome", "workflowId"])
        
        // find the pipeline by workflowId
        def pipeline = Pipeline.findByWorkflowId(data.workflowId)
        
        if (!pipeline) {
            throw new AlignmentStatsException(message: "Pipeline not found for workflow ID ${data.workflowId}!")
        }
        
        // find the sequencing experiment by runId and sampleId
        def experiment = SequencingExperiment.where {sequenceRun.id == data.run && sample.id == data.sample}.find()
        if (!experiment) {
            throw new AlignmentStatsException(message: "Sample ${data.sample} is not found in Run ${data.run}!")
        }
        
        // find the genome
        def genome = Genome.findByName(data.genome)
        if (!genome) {
            throw new AlignmentStatsException(message: "Genome ${data.genome} not found!")
        }           
        
        def alignment = SequenceAlignment.findByHistoryIdAndPipelineAndGenomeAndSequencingExperiment(data.historyId, pipeline, genome, experiment)
        if (!alignment) {
            // create a new alignment.
            alignment = new SequenceAlignment(sequencingExperiment: experiment, 
                                             genome: genome, 
                                             pipeline: pipeline,
                                             historyId: data.historyId,
                                             historyUrl: data.history_url,
                                             isPreferred: false, 
                                             date: new Date())
            alignment.save(flush:true, failOnError: true)
        }
        return alignment 
    }
    
    def checkRequiredFields(def data, List requiredFields) {
        requiredFields.each { field ->
            if (!data.properties[field]) {
                throw new AlignmentStatsException(message: "Missing ${field}!")
            }
        }
    }
    
    def saveAnalysis(StatsRegistrationCommand data, SequenceAlignment theAlignment, User apiUser) {
        // convert statistics, parameter, datasets to string
        def statisticsStr = data.statistics ? JsonOutput.toJson(data.statistics) : null
        def parameterStr = data.parameters ? JsonOutput.toJson(data.parameters) : null
        def datasetsStr = data.datasets ? JsonOutput.toJson(data.datasets) : null
        
        // get the error message
        def err
        if (data.toolStderr && data.toolStderr != "") {
            if (data.toolStderr.length() > 100 ) {
                err = data.toolStderr[0..100]
            } else {
                err = data.toolStderr
            }
        }

        // save analysis. If it's a re-run inside an old history, overwrite the old analysis; else create a new analysis.
        def analysis = findOldAnalysis(data, theAlignment)
        if (analysis) {
            // throw an exception if a different user tries to overwrite the analysis
            if (analysis.user != apiUser) {
                throw new AlignmentStatsException(message: "Analysis cannot be overwritten by a different user!")
            }
            analysis.with {
                parameters = parameterStr
                statistics = statisticsStr
                datasets = datasetsStr
                date = new Date()
                note = err
            }
        } else {
            analysis = new Analysis(alignment: theAlignment,
                                    tool: data.toolId,             
                                    category: data.toolCategory,
                                    step: data.statsToolId,
                                    stepId: data.workflowStepId,
                                    user: apiUser,
                                    parameters: parameterStr,
                                    statistics: statisticsStr,
                                    datasets: datasetsStr,
                                    date: new Date(),
                                    note: err
                                   )
        }
            
        analysis.save(failOnError: true)
        return analysis
    }
    
   /**
    * Process the analysis
    * <p>
    * Save the statistics in alignment and experiment
    * <p>
    * Transform the status note in analysis
    * @param analysisId the analysis ID
    */
    @Transactional
    def processAnalysis(Long analysisId) {
        def analysis = Analysis.get(analysisId)
        if (!analysis) {
            return
        }
        
        def statistics = utilityService.parseJson(analysis.statistics)
        def datasets = utilityService.parseJson(analysis.datasets)
        
        saveStatistics(analysis.alignment, analysis.alignment.sequencingExperiment, statistics)        
        saveDatasets(analysis.alignment, analysis.alignment.sequencingExperiment, analysis.category, datasets, statistics)
        processAnalysisNote(analysis, statistics, datasets)
    }
   
   /**
    * Transform the analysis' status note
    * @param analysis the analysis to be processed
    * @param statistics the parsed statistics JSON 
    * @param datasets the parsed datasets JSON
    */
    def processAnalysisNote(Analysis analysis, List statistics, List datasets) {
        final String peaks = "numberOfPeaks"
        final String peakPairs = "peakPairWis"
        def note
        
        // if the note has been processed, return
        def map = utilityService.parseJson(analysis.note)
        if (map && (map instanceof Map) && map.containsKey("code")) {
            return
        }
        
        // clean up the message
        def error = analysis.note
        if (error) {
            error = error.trim()
            if (error == "") {
                error = null
            }            
        }        
        
        if (error) {
            // filter "Permission denied" 
            if (error.toLowerCase().contains("permission denied")) {
                note = [code: "Permission", message: "Permission denied.", error: error]
            } else {
                note = [code: "Error", error: error]
            }
        } else {
            note = [code: "OK"]
        }
        
        switch (analysis.category) {
            case ["output_genetrack", "output_bedtoolsIntersect"]:
                // check peaks
                if (statistics.any {it-> it.containsKey(peaks) && it[peaks] == 0}) {
                    note.code = "Zero"
                    note.message = "No Peaks."
                }
                break
            case "output_cwpair2":
                // check peak pairs        
                if (statistics.any {it-> it.containsKey(peakPairs) && it[peakPairs] == 0}) {
                    note.code = "Zero"
                    note.message = "No Peak Pairs."
                }
                break
            case "output_repeatMasker":
                // check repeat masker fasta
                def url = queryDatasetsUri(datasets, "fasta")
                if (url) {
                    def data = new URL(url).getText([connectTimeout: 6000, readTimeout: 2000])
                    if(data == "") {
                        note.code = "Zero"
                        note.message = "No sequences."
                    }
                }
                break
            case "output_meme":
                // check the number of motifs
                if (statistics.any {it -> it.containsKey("motifCount") && it["motifCount"] }) {
                    // Do nothing
                } else {
                    note.code = "Zero"
                    note.message = "No motifs."
                }
                break
        }
        
        analysis.note = JsonOutput.toJson(note)
        analysis.save(failOnError: true)
    }

   /**
    * Get the motif count from meme file. Obsolete.
    * @param datasets a list of maps
    * @return motifCount
    */
    def getMotifCountFromMeme(List datasets) {
        def motifCount = 0
        def memeFile = queryDatasetsUri(datasets, "txt")
        if (memeFile) {
            def data = new URL(memeFile).getText([connectTimeout: 6000, readTimeout: 2000])
            // count the number of motifs
            motifCount = (data =~ /letter-probability matrix/).count
        }
        return motifCount
    }
    
   /**
    * Save the statistics in alignment and experiment
    * @param alignment the alignment to be updated
    * @param experiment the experiment to be updated
    * @param statistics the parsed statistics JSON
    */
    def saveStatistics(SequenceAlignment alignment, SequencingExperiment experiment, List statistics) {
        if (statistics) {
            def updatedInAlignment = copyProperties(statistics, alignment)
            if (updatedInAlignment > 0) {
                alignment.date = new Date()
                alignment.save(failOnError: true)
            } 
            def updatedInExperiment = copyProperties(statistics, experiment)
            if (updatedInExperiment > 0) {
                experiment.save(failOnError: true)
            } 
        }
    }
    
   /**
    * Save the datasets in alignment and experiment
    * @param alignment the alignment to be updated
    * @param experiment the experiment to be updated
    * @param statistics the parsed statistics JSON
    */
    def saveDatasets(SequenceAlignment alignment, SequencingExperiment experiment, String category, List datasets, List statistics) {
        try {
            def jsonSlurper = new JsonSlurper()
            switch (category) {
                case ["output_fastqRead1", "output_fastqRead2"]: // fastq
                    def fastq = [:]
                    if (experiment.fastqFile) {
                        fastq = jsonSlurper.parseText(experiment.fastqFile)
                    }
                    def newFastq = queryDatasetsUri(datasets, "fastqsanger.gz")
                    if (newFastq) {
                        def key = "read${category[-1]}"
                        fastq[key] = newFastq
                        experiment.fastqFile = JsonOutput.toJson(fastq)
                        experiment.save(faileOnError: true)
                    }                    
                    break
                case "output_fastqc": // fastqc report
                    def fastqc = [:]
                    if (experiment.fastqcReport) {
                        fastqc = jsonSlurper.parseText(experiment.fastqcReport)
                    }
                    def newFastqc = queryDatasetsUriWithRead(datasets, statistics, "html")
                    if (newFastqc) {
                        fastqc[newFastqc.read] = newFastqc.data
                        experiment.fastqcReport = JsonOutput.toJson(fastqc)
                        experiment.save(faileOnError: true)
                    }
                    break
                case "output_samtoolFilter": // bam
                    def newBam = queryDatasetsUri(datasets, "bam")
                    if (newBam) {
                        alignment.bamFile = newBam
                        alignment.save(failOnError: true)
                    }         
                    break
                case "output_bigwigForward": // bigwig_forward
                    def bigwig = queryDatasetsUri(datasets, "bigwig")
                    if (bigwig) {
                        alignment.bigwigForwardFile = bigwig
                        alignment.save(failOnError: true)
                    }
                    break
                case "output_bigwigReverse": // bigwig_reverse
                    def bigwig = queryDatasetsUri(datasets, "bigwig")
                    if (bigwig) {
                        alignment.bigwigReverseFile = bigwig
                        alignment.save(failOnError: true)
                    }
                    break
                case "output_peHistogram": //pe histogram
                    def newHistogram = queryDatasetsUri(datasets, "tabular")
                    if (newHistogram) {
                        alignment.peHistogram = newHistogram
                        alignment.save(failOnError: true)
                    }                    
                    break
            }
        } catch (Exception e) {
            log.error e
            throw new AlignmentStatsException(message: "Error saving the datasets!")
        }
    }
    
   /**
    * Find the existing analysis with the same alignment and step
    * <p>
    * Special handling: tag pileup
    * @param data data received from API
    * @param alignment alignment
    * @return existing analysis
    */
    def findOldAnalysis(StatsRegistrationCommand data, SequenceAlignment alignment) {
        def oldAnalysis = null
        switch (data.toolCategory) {
            case "output_tagPileup":
                // special handling of multip Tag Pileup calls
                def MOTIF_ID = "input2X__identifier__"
                def newMotifId = (data.parameters && data.parameters.containsKey(MOTIF_ID)) ? data.parameters[MOTIF_ID] : null 
                if (newMotifId) {
                    def oldAnalysisList = Analysis.findAllByStepAndAlignment(data.statsToolId, alignment)
                    for (int i = 0; i< oldAnalysisList.size(); ++i) {
                        def oldMotifId = utilityService.queryJson(oldAnalysisList[i].parameters, MOTIF_ID)
                        if (oldMotifId && oldMotifId == newMotifId) {
                            oldAnalysis = oldAnalysisList[i]
                            break
                        }
                    }
                }
                break
            default:
                oldAnalysis = Analysis.findByStepAndAlignment(data.statsToolId, alignment)
                break
        }
        return oldAnalysis
    }
    
   /**
    * Copy properties from source to target
    * @param source a list of maps or a map
    * @param target target object
    * return number of updated properties
    */
    def copyProperties(source, target) {
        def updatedProperties = 0
        if (source instanceof List) {
            source.each {
                updatedProperties += copyMap(it, target)
            }
        } else {
            updatedProperties = copyMap(source, target)
        }
        return updatedProperties
    }
    
   /**
    * Copy properties from source map to target object.
    * @param source source map
    * @param target target object
    * @return number of updated properties
    */
    def copyMap(source, target) {
        def updatedProperties = 0
        def readKey = source.containsKey("read") ? "read${source.read}" : "read"
        source.each { key, value ->
            if (key != "read" && target.hasProperty(key) && value != null) {
                // skip read 2's adapter dimer count
                if (!(key == "adapterDimerCount" && readKey == "read2")) {
                    try {
                        target[key] = value    
                    } catch(org.codehaus.groovy.runtime.typehandling.GroovyCastException e) {
                        log.error e
                        throw new AlignmentStatsException(message: e.message)
                    }                
                    updatedProperties++
                }
            }
        }
        return updatedProperties
    }

   /**
    * Query datasets for a given type's uri
    * @param jsonList a list of datasets
    * @param type given type
    * @return requested uri for the given type
    */
    def queryDatasetsUri(List jsonList, String type) {
        def data = jsonList?.find { d -> d.type == type }?.uri
        return data
    }
    
   /**
    * Query datasets for a given type's uri
    * @param datasets the datasets string
    * @param type given type
    * @return requested uri for the given type
    */
    def queryDatasetsUri(String datasets, String type) {
        def jsonList = utilityService.parseJson(datasets)
        def result = queryDatasetsUri(jsonList, type)
    } 

   /**
    * Query datasets for a given type's uri
    * @param jsonList a list of datasets
    * @param type given type
    * @return the list of URIs for the given type
    */
    def queryDatasetsUriList(List jsonList, String type) {
        def data = jsonList ? jsonList.findAll { d -> d.type == type }.collect { it.uri }.toList() : []
        return data
    }
    
   /**
    * Query datasets for a given type's uri
    * @param datasets datasets string
    * @param type given type
    * @return the list of URIs for the given type
    */
    def queryDatasetsUriList(String datasets, String type) {
        def jsonList = utilityService.parseJson(datasets)
        def results = queryDatasetsUriList(jsonList, type)
        return results
    } 
   
   /**
    * Query datasets for a given type's uri
    * @param datasets a list of datasets
    * @param statistics a list of statistics containing the read id
    * @param type given type
    * @return a map with read ID and the requested uri for the given type
    */
    def queryDatasetsUriWithRead(List datasets, List statistics, String type) {
        // find the read ID
        def readNum 
        if (statistics) {
            statistics.each { map ->
                if (map.containsKey("read")) {
                    readNum = map["read"]
                }
            }
        }
        // query the data
        def data = queryDatasetsUri(datasets, type)
        if (data) {
            return [read: "read${readNum}", data: data]
        } else {
            return null
        }
    }
    
   /**
    * Query datasets for a given type's uri
    * @param datasets datasets string
    * @param statistics a list of statistics containing the read id
    * @param type given type
    * @return a map with read ID and the requested uri for the given type
    */
    def queryDatasetsUriWithRead(String datasets, String statistics, String type) {
        def readNum = utilityService.queryJson(statistics, "read")
        def data = queryDatasetsUri(datasets, type)
        if (data) {
            return [read: "read${readNum}", data: data]
        } else {
            return null
        }
    }
}