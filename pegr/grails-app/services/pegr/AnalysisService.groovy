package pegr
import grails.transaction.Transactional
import grails.converters.*
import groovy.json.*

class AnalysisException extends RuntimeException {
    String message
}

/**
* A class to CRUD analysis and analysis workflow run, and update the statistics in sequencing experiment.
*/
class AnalysisService {
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
        
        def analysisWorkflowRun
        if (data.historyId) {
            analysisWorkflowRun = getAnalysisWorkflowRun(data, apiUser)
        } else {
            throw new AnalysisException(message: "Missing historyId!")
        }

        def analysis = saveAnalysis(data, analysisWorkflowRun, apiUser)

        return analysis
    }
    
   /**
    * Create analysis workflow run
    * @param data
    * @param apiUser
    * @return created analysis workflow run
    */
    def getAnalysisWorkflowRun(StatsRegistrationCommand data, User apiUser) {
        // check required fields
        checkRequiredFields(data, ["run", "sample", "genome", "workflowId"])
        
        // find the pipeline by workflowId
        def pipeline = Pipeline.findByWorkflowId(data.workflowId)
        
        if (!pipeline) {
            throw new AnalysisException(message: "Pipeline not found for workflow ID ${data.workflowId}!")
        }
        
        // find the sequencing experiment by runId and sampleId
        def experiment = SequencingExperiment.where {sequenceRun.id == data.run && sample.id == data.sample}.find()
        if (!experiment) {
            throw new AnalysisException(message: "Sample ${data.sample} is not found in Run ${data.run}!")
        }
        
        // find the genome
        def genome = Genome.findByName(data.genome)
        if (!genome) {
            throw new AnalysisException(message: "Genome ${data.genome} not found!")
        }           
        
        def analysisWorkflowRun = AnalysisWorkflowRun.findByHistoryIdAndPipelineAndGenomeAndSequencingExperiment(data.historyId, pipeline, genome, experiment)
        if (!analysisWorkflowRun) {
            // create a new analysisWorkflowRun.
             analysisWorkflowRun = new AnalysisWorkflowRun(sequencingExperiment: experiment, 
                                             genome: genome, 
                                             pipeline: pipeline,
                                             historyId: data.historyId,
                                             historyUrl: data.history_url,
                                             isPreferred: false, 
                                             date: new Date())
            analysisWorkflowRun.save(flush:true, failOnError: true)
        }
        return analysisWorkflowRun 
    }
    
    def checkRequiredFields(def data, List requiredFields) {
        requiredFields.each { field ->
            if (!data.properties[field]) {
                throw new AnalysisException(message: "Missing ${field}!")
            }
        }
    }
    
    def saveAnalysis(StatsRegistrationCommand data, AnalysisWorkflowRun theAnalysisWorkflowRun, User apiUser) {
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
        def analysis = findOldAnalysis(data, theAnalysisWorkflowRun)
        if (analysis) {
            // throw an exception if a different user tries to overwrite the analysis
            if (analysis.user != apiUser) {
                throw new AnalysisException(message: "Analysis cannot be overwritten by a different user!")
            }
            analysis.with {
                parameters = parameterStr
                statistics = statisticsStr
                datasets = datasetsStr
                date = new Date()
                note = err
            }
        } else {
            analysis = new Analysis(analysisWorkflowRun: theAnalysisWorkflowRun,
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
    * Save the statistics in analysis workflow run and experiment
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
        def parameters = utilityService.parseJson(analysis.parameters)
              
        saveParamsAndResults(analysis.analysisWorkflowRun, analysis.analysisWorkflowRun.sequencingExperiment, analysis.category, datasets, statistics, parameters)
        
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
                    def data = new URL(url).getText()
                    if(data == "") {
                        note.code = "Zero"
                        note.message = "No sequences."
                    }
                }
                break
            case "output_meme":
                // count the number of motifs
                def motifCount = getMotifCountFromMeme(datasets)
                    
                // save the number of motifs as a statistics
                analysis.statistics = JsonOutput.toJson([["motifCount":motifCount]])
                    
                // add message if no motif exists
                if (motifCount == 0) {
                    note.code = "Zero"
                    note.message = "No motifs."
                }
                break
        }
        
        analysis.note = JsonOutput.toJson(note)
        analysis.save(failOnError: true)
    }

   /**
    * Get the motif count from meme file
    * @param datasets a list of maps
    * @return motifCount
    */
    def getMotifCountFromMeme(List datasets) {
        def motifCount = 0
        def memeFile = queryDatasetsUri(datasets, "txt")
        if (memeFile) {
            def data = new URL(memeFile).getText()
            // count the number of motifs
            motifCount = (data =~ /letter-probability matrix/).count
        }
        return motifCount
    }
    
   /**
    * Save the datasets in analysis workflow run and experiment
    * @param run the analysis workflow run to be updated
    * @param experiment the experiment to be updated
    * @param category the category of the analysis step
    * @param datasets the parsed datasets JSON
    * @param statistics the parsed statistics JSON
    */
    def saveParamsAndResults(AnalysisWorkflowRun run, SequencingExperiment experiment, String category, List datasets, List statistics, Map parameters) {
        try { 
            def newResults = [:]
            def newParameters = [:]
            
            // copy results from statistics if any
            if (statistics & statistics.size() > 0) {
                statistics.each { it ->
                    statsMap = utilityService.parseJson(it)
                    if (statsMap & statsMap.size() > 0) {
                        newResults << statsMap
                    }
                }   
            }

            // copy results from datasets and parameters. Separate code for each step category because of the current data structure from Galaxy.
            switch (category) {
                case ["output_fastqRead1", "output_fastqRead2"]: // fastq
                    def fastq = [:]
                    if (experiment.fastqFile) {
                        fastq = utilityService.parseJson(experiment.fastqFile)
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
                        fastqc = utilityService.parseJson(experiment.fastqcReport)
                    }
                    def newFastqc = queryDatasetsUriWithRead(datasets, statistics, "html")
                    if (newFastqc) {
                        fastqc[newFastqc.read] = newFastqc.data
                        experiment.fastqcReport = JsonOutput.toJson(fastqc)
                        experiment.save(faileOnError: true)
                    }
                    break
                case "output_samtoolFilter": // bam
                    newResults["bamFile"] = queryDatasetsUri(datasets, "bam")
                    break
                case "output_peHistogram": //pe histogram
                    newResults["peHistogram"] = queryDatasetsUri(datasets, "png")                 
                    break
                case "output_markDuplicates": //bam_raw
                    newResults["bamRaw"] = queryDatasetsUri(datasets, "bam")
                    break
                case "output_bamToScidx": //scidx
                    newResults["scidx"] = queryDatasetsUri(datasets, "scidx")
                    break
                case "output_genetrack": // GeneTrack
                    def params = utilityService.queryJson(parameters, ["filter", "sigma", "exclusion"])
                    newParameters["peakCallingParam"] = getPeakCallingParam(params.filter, params.exclusion, params.sigma)
                    break
                case "output_bedtoolsIntersect": // GeneTrack
                    def stats = utilityService.queryJson(statistics, ["numberOfPeaks", "singletons"])
                    newResults["peaks"] = stats.numberOfPeaks
                    newResults["singletons"] = stats.singletons
                    break
                case "output_cwpair2": // cwpair
                    def params = utilityService.queryJson(parameters, ["up_distance", "down_distance", "binsize"])
                    newParameters["peakPairsParam"] = getPeakPairsParam(params.up_distance, params.down_distance, params.binsize)
                    newResults["peakPairs"] = utilityService.queryJson(statistics, "peakPairWis")
                    newResults["cwpairFile"] = queryDatasetsUri(datasets, "gff")
                    break
                case "output_meme": // meme
                    newResults["memeFile"] = queryDatasetsUri(datasets, "txt")
                    newResults["memeFig"] = queryDatasetsUri(datasets, "html")
                    break
                case "output_fourColorPlot": // four color plot
                    newResults["fourColor"] = queryDatasetsUriList(datasets, "png")
                    break
                case "output_tagPileup": //composite 
                    def motif = utilityService.queryJson(parameters, "input2X__identifier__")
                    def motifId = motif?.find( /\d+/ )?.toInteger()
                    def tabulars = queryDatasetsUriList(datasets, "tabular")
                    if (tabulars && tabulars.size() > 0) {
                        if (motifId && motifId > 0){
                            newResults["composite"][motifId-1] = tabulars.last()
                        }                      
                    }
                    break
            }
            
            // parse the existing results from run, add the new values and save.
            if (newResults.size() > 0) {
                def runResults = utilityService.parseJson(run.results)
                if (!runResults) {
                    runResults = newResults
                } else {
                    runResults << newResults
                }
                run.results = JsonOutput.toJson(runResults)
            }
            
            // parse the existing parameters from run, add the new values and save.
            if (newParameters.size() > 0) {
                def runParameters = utilityService.parseJson(run.parameters)
                if (!runParameters) {
                    runParameters = newParameters
                } else {
                    runParameters << newParameters
                }
                run.parameters = JsonOutput.toJson(runParameters)
            }
            
            run.save(failOnError: true)
        } catch (Exception e) {
            log.error e
            throw new AnalysisException(message: "Error saving the datasets!")
        }
    }
    
   /**
    * Find the existing analysis with the same analysis workflow run and step
    * <p>
    * Special handling: tag pileup
    * @param data data received from API
    * @param run analysis workflow run
    * @return existing analysis
    */
    def findOldAnalysis(StatsRegistrationCommand data, AnalysisWorkflowRun run) {
        def oldAnalysis = null
        switch (data.toolCategory) {
            case "output_tagPileup":
                // special handling of multip Tag Pileup calls
                def MOTIF_ID = "input2X__identifier__"
                def newMotifId = (data.parameters && data.parameters.containsKey(MOTIF_ID)) ? data.parameters[MOTIF_ID] : null 
                if (newMotifId) {
                    def oldAnalysisList = Analysis.findAllByStepAndAnalysisWorkflowRun(data.statsToolId, run)
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
                oldAnalysis = Analysis.findByStepAndAnalysisWorkflowRun(data.statsToolId, run)
                break
        }
        return oldAnalysis
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