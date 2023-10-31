package pegr
import groovy.json.* 

def jsonSlurper = new JsonSlurper()

SequenceAlignment.list().each { alignment ->
    try {
        def analysis = Analysis.findByCategoryAndAlignment('output_peHistogram', alignment)
        def jsonList = jsonSlurper.parseText(analysis.datasets)
        alignment.peHistogram = jsonList?.find { d -> d.type == "tabular" }?.uri
        alignment.save(failOnError: true)
    } catch(Exception e) {
        
    }    
}
