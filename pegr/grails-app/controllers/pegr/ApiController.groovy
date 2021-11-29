package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.json.*
    
class ApiException extends RuntimeException {
    String message
}
    
class ApiController {
    def alignmentStatsService
    def reportService
    def sampleService
    def utilityService
    def ngsRepoService
    
    static allowedMethods = [stats:'POST',
                            fetchSampleData:'POST',
                            deleteSampleList: 'POST',
                            fetchSequenceRunData: 'POST',
                            deleteAnalysisHistories: 'POST',
                            fetchSequenceRunInfo: 'POST',
                            getSequenceRunStatus: 'POST',
                            setSequenceRunStatus: 'POST'
                            ]
    
    /**
     * Accept post request, authenticate by the API Key, to save data into Analysis, 
     * and parse data prior to and including the Alignment.
     * @param data Input data in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     * @return status code
     */
    def stats(StatsRegistrationCommand data, String apiKey) {
        def message = "Success!"
        def code = 200
        def analysis
        if (!data || data.properties.every {key, value -> value == null}) {
            code = 500
            message = "Error parsing the JSON data!"
        } else {
            def apiUser = User.findByEmailAndApiKey(data.userEmail, apiKey)
            if (apiUser) {
                try {
                    analysis = alignmentStatsService.save(data, apiUser)
                } catch(AlignmentStatsException e) {
                    code = 500
                    message = "Error: ${e.message}"
                } catch(Exception e0) {
                    try {
                        analysis = alignmentStatsService.save(data, apiUser)
                    } catch(Exception e) {
                        log.error "Error: ${e.message}", e
                        code = 500
                        message = "Error!"
                    }                    
                }
            } else {
                code = 401
                message = "Not authorized!" 
            }
        }
        def response = new ResponseMessage(response_code: code, message: message)
        render text: response as JSON, contentType: "text/json", status: code 
        if (analysis) {
             ProcessAnalysisJob.triggerNow([id: analysis.id])    
        }
    }
    
    /**
     * Accept post request, authenticate by the API Key, to query sample data.
     * @param query in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     * @return status code
     */
    def fetchSampleData(QuerySampleRegistrationCommand cmd, String apiKey) {
        def apiUser = User.findByEmailAndApiKey(cmd.userEmail, apiKey)
        def message, data, code
        if (apiUser) {
            def sampleIds = sampleService.search(cmd).collect {it.id}.toList()
            if (sampleIds.size() == 0) {
                code = 404
                message = "No sample has been found!"
            } else {
                data = reportService.fetchDataForSamples(sampleIds, cmd.preferredOnly)     
                code = 200
                message = "Success!"
            }
        } else {
            code = 401
            message = "Not authorized!" 
        }   
        def results = [data: data, message: message] as JSON
        render text: results, contentType: "text/json", status: code
    }
    
    /*
     * Accept post request, authenticate by the API Key, to query sample
     * data in a sequence run.
     * @param query in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     * @return status code
     */
    def fetchSequenceRunData(QueryRunRegistrationCommand query, String apiKey) {
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)
        def message, data, code
        if (apiUser) {
            if (query.runId) {
                try {
                    def preferredOnly = query.preferredOnly
                    data = reportService.fetchDataForRun(query.runId, preferredOnly)       
                    code = 200
                    message = "Success fetching data from Run ${query.runId}!"
                } catch (ReportException e) {
                    code = 500
                    message = e.message
                }
            } else {
                code = 404
                message = "Sequence Run ID is missing!"
            }
        } else {
            code = 401
            message = "Not authorized!" 
        }   
        def results = [data: data, message: message] as JSON
        render text: results, contentType: "text/json", status: code
    }
    
    /*
     * Accept post request, authenticate by the API Key, to delete samples
     * @param query in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     */
    def deleteSampleList(DelSampleRegistrationCommand query, String apiKey) {
        def message = ""
        def code = 200
        
        // get the user
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)

        // only admin is allowed to use this API
        if (apiUser && apiUser.isAdmin()) {
            if (!query.sampleIds) {
                code = 500
                message = "No sample ID provided!"
            } else {
                query.sampleIds.each {
                    def sample = Sample.get(it.toLong())
                    if (!sample) {
                        message += "Sample ${it} not found! "
                        code = 500
                    } else {
                        try {
                            sampleService.delete(sample)
                        } catch(SampleException e) {
                            message += "Error deleting sample ${it}! ${e.message} "
                            code = 500
                        }
                    }
                }
                if (message == "") {
                    message = "Success!"
                }
            }
        } else {
            code = 401
            message = "Not authorized!"
        }
        
        def results = [message: message] as JSON
        render text: results, contentType: "text/json", status: code
    }
    
    /*
     * Accept post request, authenticate by the API Key, to delete histories
     * @param query in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     */
    def deleteAnalysisHistories(DelAnalysisHistoryCmd query, String apiKey) {
        def message = ""
        def code = 200
        
        // get the user
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)

        // only admin is allowed to use this API
        if (apiUser && apiUser.isAdmin()) {
            if (!query.historyIds) {
                code = 500
                message = "No analysis history ID provided!"
            } else {
                query.historyIds.each {
                    def alignment = SequenceAlignment.findByHistoryId(it)
                    if (!alignment) {
                        message += "Analysis history ${it} not found! "
                        code = 500
                    } else {
                        try {
                            reportService.deleteAlignment(alignment.id)
                        } catch(ReportException e) {
                            message += "Error deleting analysis history ${it}! ${e.message} "
                            code = 500
                        }
                    }
                }
                if (message == "") {
                    message = "Success!"
                }
            }
        } else {
            code = 401
            message = "Not authorized!"
        }
        
        def results = [message: message] as JSON
        render text: results, contentType: "text/json", status: code
    }    
    
    
    def getRunFromIdOrDirectory(Long runId, String directory) {
        def run_from_id = null
        def run_from_dir = null
        def run = null
        
        if (runId == null && directory == null) {
            throw new ApiException(message: "Please provide either the sequence run's ID or directory!")
        }
        
        if (runId) {
            try {
                run_from_id = SequenceRun.get(runId)
            } catch (Exception e) {
                throw new ApiException(message: "The sequence run cannot be found based on the provided sequence run ID!")
            }
            run = run_from_id
        }
        
        if (directory) {
            run_from_dir = SequenceRun.findByDirectoryName(directory)
            if (run_from_dir) {
                run = run_from_dir
            } else {
                throw new ApiException(message: "The sequence run cannot be found based on the provided directory!")
            }
        }
        
        if (run_from_id && run_from_dir) {
            if (run_from_id != run_from_dir) {
                throw new ApiException(message: "The provided sequence run ID and directory point to different sequence runs!")
            }
        }
        
        if (run == null) {
            throw new ApiException(message: "Sequence run cannot be found!")
        }
        
        return run
    }
    
    
    def fetchSequenceRunInfo(SequenceRunInfoCmd query, String apiKey) {
        def message = ""
        def code = 200
        def timeout = 300 * 1000
        
        // get the user
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)
        def runId = query.runId
        
        if (apiUser) {
            String RUN_INFO_TEMP = "runInfo${runId}"           

            try {
                // generate the run info files
                def run = getRunFromIdOrDirectory(runId, query.directory)

                // clean path
                def remoteRoot = query.remoteRoot.trim()

                def remotePath

                if (!run.directoryName) {
                    message = "Error! Please check the directory name of Run #${runId}!"
                    code = 500
                    def results = [message: message] as JSON
                    render text: results, contentType: "text/json", status: code
                } else {
                    remotePath = new File(remoteRoot, run.directoryName).getPath()
                
                    def localRoot = utilityService.getFilesRoot()
                    def localFolder = new File(localRoot, RUN_INFO_TEMP)

                    ngsRepoService.generateRunFilesInFolder(run, remotePath, localFolder)

                    // compress the files
                    def compressedFilename = "runInfo${runId}.tar.gz"
                    def compressedFile = new File(localRoot, compressedFilename)
                    def command = "tar -czf ${compressedFile.getPath()} ${RUN_INFO_TEMP}"
                    def envVars = []
                    def proc = command.execute(envVars, localRoot)
                    proc.waitForOrKill(timeout)

                    response.setContentType("application/gzip")
                    response.setHeader("Content-disposition", "attachment;filename=\"${compressedFilename}\"")
                    response.outputStream << compressedFile.getBytes()
                    // remove the compressed file
                    compressedFile.delete()
                    // remove the original files
                    command = "rm -R ${localFolder.getPath()}"
                    command.execute()
                    webRequest.renderView = false
                }
            } catch(Exception e) {
                message = "Error! ${e.message}"
                code = 500
                def results = [message: message] as JSON
                render text: results, contentType: "text/json", status: code
            } finally {
                response.getOutputStream().flush()
                response.getOutputStream().close()
            }
        } else {
            def results = [message: "Not authorized!"] as JSON
            code = 401
            render text: results, contentType: "text/json", status: code
        }
 
    }
    
    def getSequenceRunStatus(GetSequenceRunStatusCmd query, String apiKey) {
        // get the user
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)

        // only admin is allowed to use this API
        if (apiUser) {
            try {
                def code = 200
                def run = getRunFromIdOrDirectory(query.runId, query.directory)
                def results = [status: run.status.name(), message: "Success!"] as JSON
                render text: results, contentType: "text/json", status: code
            } catch(Exception e) {
                def code = 500
                def results = [message: "Error! ${e.message}"] as JSON
                render text: results, contentType: "text/json", status: code
            }
        } else {
            def code = 401
            def results = [message: "Not authorized!"] as JSON
            render text: results, contentType: "text/json", status: code
        }
    }   
    
    def setSequenceRunStatus(SetSequenceRunStatusCmd query, String apiKey) {
        // get the user
        def apiUser = User.findByEmailAndApiKey(query.userEmail, apiKey)

        // only admin is allowed to use this API
        if (apiUser && apiUser.isAdmin()) {
            try {
                def code = 200
                def run = getRunFromIdOrDirectory(query.runId, query.directory)
                reportService.updateRunStatus(run.id, query.status)
                def results = [message: "Success!"] as JSON
                render text: results, contentType: "text/json", status: code
            } catch(Exception e) {
                def code = 500
                def results = [message: "Error! ${e.message}"] as JSON
                render text: results, contentType: "text/json", status: code
            }
        } else {
            def code = 401
            def results = [message: "Not authorized!"] as JSON
            render text: results, contentType: "text/json", status: code
        }
    }
}


class ResponseMessage {
    String response_code
    String message
}

/* 
 * Class that defines the underlying structure of input JSON data
 */
class StatsRegistrationCommand implements grails.validation.Validateable {
    Long alignmentId
    Long run
    Long sample
    String genome
    String statsToolId
    String toolId
    String workflowId
    String historyId
    String history_url
    String workflowStepId
    String userEmail
    String toolCategory
    String toolStderr
    Map parameters
    List statistics
    List datasets
}

class QuerySampleRegistrationCommand implements grails.validation.Validateable {
    String userEmail // required
    Boolean preferredOnly
    Integer max
    Integer offset
    String sort
    String order
    String species
    String strain
    String antibody
    Long id
    String source
    String sourceId
    String target
    String assay
    Long speciesId
    Long assayId
}

class QueryRunRegistrationCommand implements grails.validation.Validateable {
    String userEmail // required
    Boolean preferredOnly
    Long runId // required
}

class DelSampleRegistrationCommand implements grails.validation.Validateable {
    String userEmail // required
    List sampleIds // required
}

class DelAnalysisHistoryCmd implements grails.validation.Validateable {
    String userEmail // required
    List historyIds // required
}

class SequenceRunInfoCmd implements grails.validation.Validateable {
    String userEmail // required
    Long runId // either runId or directory needs to be provided
    String directory 
    String remoteRoot // required
}

class GetSequenceRunStatusCmd implements grails.validation.Validateable {
    String userEmail // required
    Long runId // either runId or directory needs to be provided
    String directory 
}

class SetSequenceRunStatusCmd implements grails.validation.Validateable {
    String userEmail // required
    Long runId // either runId or directory needs to be provided
    String directory 
    String status // required
}
