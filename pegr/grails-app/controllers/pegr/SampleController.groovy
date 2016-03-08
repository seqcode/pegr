package pegr
import groovy.json.*
    
class SampleController {
    
    def index(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "id"
            params.order = "desc"
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
    
    def showChecked(){
        
        def ids
        if (session.checkedSample){
            session.checkedSample.append(params.checkedSample)
        }else{
            ids = params.checkedSample
        }
        
        def sampleList = []
        ids.each{
            def sample = Sample.get(Long.parseLong(it))
            if (sample) {
                sampleList.push(sample)
            }
        }
        session.checkedSample = null
        [sampleList: sampleList, ids: ids]
    }
    
    def searchForm() {
        
    }
    
    def search() {       
        def sampleProps = Sample.metaClass.properties*.name
        def c = Sample.createCriteria()
        params.max = 15
        def samples = c.list(params) {
            and {
               if (params.species) {
                    cellSource {
                        strain {
                            species {
                                ilike "name", "%${params.species}%"
                            }
                        }
                    }
                }
                if (params.strain) {
                    cellSource {
                        strain {
                            ilike "name", "%${params.strain}%"
                        }                    
                    }
                }
                if (params.antibody) {
                    antibody {
                        ilike "catalogNumber", "%${params.antibody}%"
                    }
                }
                eq("status", SampleStatus.COMPLETED)
            }
            order("id", "desc")
        }
        [sampleList: samples]
    }
    
    def clearCheckedSampleAjax(){
        if (session.checkedSample) {
            session.checkedSample = null
        }
        return
    }
    
}
