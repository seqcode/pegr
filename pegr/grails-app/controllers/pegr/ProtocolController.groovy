package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProtocolController {
	def springSecurityService
	
    def showProtocolsAjax(){
        if (params.id.isInteger()){
			def protocolGroup = ProtocolGroup.get(params.id)
            render template: 'protocolsDetails', bean: protocolGroup
        } else{
            render "Please select a protocol group."
        }
	
    }
    
    @Transactional
	def updateProtocolGroupForSample() {
        if(request.method=="POST") {
            if (!params.id.isInteger()){
                flash.message = "Please select a protocol group."
                render(view: "updateProtocolGroupForSample", model: [sampleId: params.sampleId])
            }else{
                def sample = Sample.get(params.sampleId)
                def protocolGroup = ProtocolGroup.get(params.id)
                if(sample && protocolGroup)  {
                    sample.protocolGroup = protocolGroup
                    sample.save(flush:true)
                    redirect(action: "showProtocolsForSample", params: [sampleId: params.sampleId])
                } else {
                    render status: 500
                }		
            }
        } else {
            [sampleId: params.sampleId]
        }
	}
	
     
	def showProtocolsForSample(Integer sampleId) {
		def sample = Sample.get(sampleId)
        if (!sample.protocolGroup){
            redirect(action: "updateProtocolGroupForSample", params: [sampleId: params.sampleId])
        }
		// fetch protocol instances
		def protocolInstances 
		def n = 0
		if(sample.latestProtocolInstance) {
			def ProtocolInstance p = sample.latestProtocolInstance
			protocolInstances = [p]
			n++
			while(p.prior) {
				p = p.prior
				protocolInstances.add(0, p)
				n++
			}
		}
		
		[protocolGroup: sample.protocolGroup, protocolInstances: protocolInstances, protInstCount: n, sampleId: sample.id]
	}
    
	@Transactional
	def createInstanceForSample() {
		def user = springSecurityService.currentUser
		def priorProtInst = null
		if (params.priorProtInstId) {
			priorProtInst = ProtocolInstance.get(params.priorProtInstId)
		}
		def protocol = Protocol.get(params.protocolId)
		def protocolInstance = new ProtocolInstance(protocol: protocol, 
			user: user,
			prior: priorProtInst,
            completed: false)
		if (protocolInstance.validate() && protocolInstance.save(flush: true)) {
			// update the latest protocol instance for sample
			def sample = Sample.get(params.sampleId)
			if (sample){
				sample.latestProtocolInstance = protocolInstance
				sample.save(flush: true)
				render(view: "editInstanceForSample", 
					model: [protocolInstance: protocolInstance, sampleId: params.sampleId])
			} else {
				render status: 500
			}
		}else {
			render status: 500
		}
	}
		
    @Transactional
	def showInstanceForSample(Integer prtclInstanceId, Integer sampleId) {
        def protocolInstance = ProtocolInstance.get(prtclInstanceId)
        if (protocolInstance) {
		  [protocolInstance:protocolInstance, sampleId: sampleId]
        } else {
            render status: 500
        }
	}
    
    @Transactional
    def addItemToPrtclInstanceAjax(){
        withForm {
            def item = new Item(params)
            if (item.validate() && item.save(flush: true)) {
                render template: '/item/details', bean: item, var: 'itemInstance'
            } else {
                render "Invalid inputs for the new item!"
            }
        }
    }
}
