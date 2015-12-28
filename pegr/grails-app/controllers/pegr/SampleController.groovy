package pegr

class SampleController {

    def index(Integer max) {
        max = Math.min(max ?: 10, 100)
        respond Sample.list(params), model:[sampleCount: Sample.count()]
    }
	
	def addProtocolGroup() {
		
		
	}
	
	def addProtocolInstance() {
		
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
    
    def createTechnicalReplicate(){
        
    }
    
    def createBiologicalReplicate(){
        
    }
    
}
