package pegr

class SampleController {

    static scaffold = true
    
    def index(Integer max) {
        max = Math.min(max ?: 10, 100)
        respond Sample.list(params), model:[sampleCount: Sample.count()]
    }
	
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
		redirect(action: "createCellSource", id: "${sample.id}")

    }
    
	def show(Integer id) {
		def sample = Sample.get(id)
		[sampleInstance:sample, sampleId: sample.id]
	}
	
    def showCellSource(Integer sampleId) {
        def sample = Sample.get(sampleId)
		
		if (sample.cellSource) {
			def item = Item.where {
				(type.name=="Cell Source") && (referenceId == sample.cellSource.id)
			}.get(max: 1)
			[cellSourceInstance: sample.cellSource, itemInstance: item, sampleId: sampleId]
		} else {
			redirect(action:"createCellSource", params: [sampleId: sampleId])
		}
    }
	
	def createCellSource() {
		if (params.sampleId) {
			if(request.method != "POST") {
				render(view: "createCellSource", model:[sampleId: params.sampleId]) 
			}else{
				withForm {
					def sample = Sample.get(params.sampleId)
					if(sample) {
						def cellSource = new CellSource(params)
						def itemType = ItemType.findByName("Cell Source")
						def item = new Item(type: itemType)
						item.properties['barcode', 'location'] = params
						if (cellSource.validate() && cellSource.save(flush:true)) {
							sample.cellSource = cellSource
							sample.save()
							
							item.referenceId = cellSource.id
							if (item.validate()){
								item.save(flush:true)
								redirect(action: "showCellSource", params: [sampleId: params.sampleId])
							}else {
								flash.message = "Invalid inputs"
								render( model: [cellSourceInstance: cellSource,
																	itemInstance: item,
																	sampleId: sample.id])
							}
						} else {
							flash.message = "Invalid inputs"
							render( model: [cellSourceInstance: cellSource,
																	itemInstance: item,
																	sampleId: sample.id])
						}
					}
				}
			}
		}else {
			render "Missing Sample ID!"
		}
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
