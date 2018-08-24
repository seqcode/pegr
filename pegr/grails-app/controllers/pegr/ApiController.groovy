package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.json.*
    
class ApiController {
    def alignmentStatsService
    def reportService
    def sampleService
    def utilityService
    
    static allowedMethods = [stats:'POST',
                            fetchSampleData:'POST',
                            fetchSequenceRunData: 'POST'
                            ]
    
    def help() {
    }
    
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
                message = "Success! Accepted filters:\n"
                cmd.properties.each { prop, val ->
                    if (!(prop in ["userEmail", "class", "metaClass"]) && val != null)
                    message += "${prop}: ${val} \n"
                }
                if (cmd.id) {
                    message += "id: ${cmd.id} \n"
                }
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
            if (query.runNum) {
                try {
                    def preferredOnly = query.preferredOnly
                    data = reportService.fetchDataForRun(query.runNum, preferredOnly)       
                    code = 200
                    message = "Success fetching data from Run ${query.runNum}!"
                } catch (ReportException e) {
                    code = 500
                    message = e.message
                }
            } else {
                code = 404
                message = "Sequence Run # is missing!"
            }
        } else {
            code = 401
            message = "Not authorized!" 
        }   
        def results = [data: data, message: message] as JSON
        render text: results, contentType: "text/json", status: code
    }
}

class ResponseMessage {
    String response_code
    String message
}

/* 
 * Class that defines the underlying structure of input JSON data
 */
@grails.validation.Validateable
class StatsRegistrationCommand {
    Long alignmentId
    Integer runNum  // axa677: changed the runId to runNum since the id is going to be used only for the backend functions. The end user should only use the runNum to retrieve whatever info needed
    Long sample
    String genome
    String statsToolId
    String toolId
    String workflowId
    String historyId
    String workflowStepId
    String userEmail
    String toolCategory
    String toolStderr
    Map parameters
    List statistics
    List datasets
}

@grails.validation.Validateable
class QuerySampleRegistrationCommand {
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
}

@grails.validation.Validateable
class QueryRunRegistrationCommand {
    String userEmail // required
    Boolean preferredOnly
    Integer runNum  // axa677: changed the runId to runNum since the id is going to be used only for the backend functions. The end user should only use the runNum to retrieve whatever info needed
}


