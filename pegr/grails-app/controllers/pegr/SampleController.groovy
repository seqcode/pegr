package pegr
import grails.transaction.Transactional
import groovy.json.*
    
@Transactional(readOnly = true)
class SampleController {
    
    def index(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "id"
        }
        def samples = Sample.where{status == SampleStatus.COMPLETED}.list(params)
        [sampleList: samples, sampleCount: Sample.count()]
    }
    
	def show(Integer id) {
		def sample = Sample.get(id)
        def jsonSlurper = new JsonSlurper()
        def notes = [:]
        try {
            notes += jsonSlurper.parseText(sample.note)
        }catch(Exception e){
            
        }
        try {
            notes += jsonSlurper.parseText(sample.prtclInstSummary.note)
        }catch(Exception e){
            
        }        
		[sample: sample, notes: notes]
	}
	
    def search() {       
        def sampleProps = Sample.metaClass.properties*.name
        def samples = Sample.withCriteria {
            "${params.queryType}" {
                params.each { field, value ->
                    if (sampleProps.contains(field) && value) {
                        ilike field, "%{value}%"
                    }
                }
            }
        }
        [samples: samples]
    }
    
    @Transactional
    def createTechnicalReplicate(){
        
    }
    
    @Transactional
    def createBiologicalReplicate(){
        
    }
    
}
