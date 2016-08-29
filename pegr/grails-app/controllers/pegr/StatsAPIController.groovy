package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.json.*
    
class StatsAPIController {
    def alignmentStatsService
    
    /*
     * Accept API calls, authenticate by the API Key, save the raw data into Analysis, 
     * and parse data prior to and including the Alignment. To test the API: curl -i -X POST -H 
     * "Content-Type: application/json" -d '{"run":2,"sample":2,"genome":"sacCer3_cegr"}' 
     * <hostname>:<port>/<application-name>/api/stats?apiKey=
     * @param data Input data in the format of JSON dictionary
     * @param apiKey API Key used to authenticate the user
     * @return response in the format of JSON dictionary, including a response_code and a message. 
     * @return status code
     */
    def save(StatsRegistrationCommand data, String apiKey) {
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



