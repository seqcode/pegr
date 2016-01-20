package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SampleController {

    static scaffold = true
    
    def index(Integer max) {
        max = Math.min(max ?: 10, 100)
        respond Sample.list(params), model:[sampleCount: Sample.count()]
    }
	
    @Transactional
    def create() {
		def sample = new Sample(status: SampleStatus.CREATED)
		if(params.projectId) {
			def project = Project.get(params.projectId)
			if (!project)
				render status: 500
			project.addToSamples(sample)
			project.save(flush: true)	
		}else {
			sample.save(flush: true)
		}
		redirect(controller: "CellCulture", action: "createCellCultureForSample", params: [sampleId: sample.id])

    }
    
	def show(Integer id) {
		def sample = Sample.get(id)
		[sampleInstance:sample, sampleId: sample.id]
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
