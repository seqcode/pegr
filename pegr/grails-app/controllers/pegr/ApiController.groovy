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
                            fetchSampleData:'GET',
                            fetchSequenceRunData: 'GET'
                            ]
    
    /*
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
        if (!data || data.properties.every {key, value -> value == null}) {
            code = 500
            message = "Error parsing the JSON data!"
        } else {
            def apiUser = User.findByEmailAndApiKey(data.userEmail, apiKey)
            if (apiUser) {
                try {
                    alignmentStatsService.save(data, apiUser)
                } catch(AlignmentStatsException e) {
                    code = 500
                    message = "Error: ${e.message}"
                } catch(Exception e0) {
                    try {
                        alignmentStatsService.save(data, apiUser)
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
    }    
    
    /*
     * Accept get request, authenticate by the API Key, to query sample data.
     * @param query in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     * @return status code
     */
    def fetchSampleData(QuerySampleRegistrationCommand cmd, String apiKey) {
        def apiUser = User.findByEmailAndApiKey(cmd.userEmail, apiKey)
        def message, data, code
        if (apiUser) {            
            def listParams = [
                max: cmd.max ?: 100,
                sort: cmd.sort ?: "id",
                order: cmd.order ?: "desc",
                offset: cmd.offset
            ]

            def sampleIds = sampleService.search(cmd, listParams).collect {it.id}.toList()
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
     * Accept get request, authenticate by the API Key, to query sample
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
                    def preferredOnly = query.preferredOnly ?: true
                    data = reportService.fetchDataForRun(query.runId, preferredOnly)       
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
}

class ResponseMessage {
    String response_code
    String message
}

/* 
 * Class that defines the underlying structure of input JSON data
 */
class StatsRegistrationCommand {
    Long run
    Long sample
    String genome
    String toolId
    String workflowId
    String historyId
    String workflowStepId
    String userEmail
    String toolCategory
    Map parameters
    List statistics
    List datasets
}

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

class QueryRunRegistrationCommand {
    String userEmail // required
    Boolean preferredOnly
    Long runId // required
}


