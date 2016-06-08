package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.json.*
    
class StatsAPIController {
    def alignmentStatsService
    
    // to test the API: curl -i -X POST -H "Content-Type: application/json" -d '{"run":2,"sample":2,"genome":"sacCer3_cegr"}' localhost:8080/pegr/api/stats?apiKey=
    def save(StatsRegistrationCommand data, String apiKey) {

        if (!data || data.properties.every {key, value -> value == null}) {
            def response = new ResponseMessage(response_code: 500, message: "Error parsing the JSON data!")
            render text: response as JSON, contentType: "text/json", status: 500 
        } else {
            def user = ApiUser.findByApiKey(apiKey)
            if (user) {
                try {
                    alignmentStatsService.save(data, user.name)
                    def message = "Success!"
                    def response = new ResponseMessage(response_code: 200, message: message)
                    render text: response as JSON, contentType: "text/json", status: 200 
                } catch(AlignmentStatsException e) {
                    def response = new ResponseMessage(response_code: 500, message: "Error: ${e.message}")
                    render text: response as JSON, contentType: "text/json", status: 500 
                } catch(Exception e) {
                    log.error "Error: ${e.message}", e
                    def response = new ResponseMessage(response_code: 500, message: "Error!")
                    render text: response as JSON, contentType: "text/json", status: 500 
                }
            } else {
                def response = new ResponseMessage(response_code: 401, message: "Not authorized!")
                render text: response as JSON, contentType: "text/json", status: 401 
            }
        }
    }    
}

class ResponseMessage {
    String response_code
    String message
}

class StatsRegistrationCommand {
    Long run
    Long sample
    String genome
    String toolId
    String workflowId
    String historyId
    String toolCategory
    String parameters
    Map statistics
    List datasets
}



