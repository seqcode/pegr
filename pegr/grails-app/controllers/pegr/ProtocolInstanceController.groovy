package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProtocolInstanceController {
	def springSecurityService
	
    def show() {
		
	}
	
	@Transactional
	def create() {
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
				render(view: "edit", 
					model: [protocolInstance: protocolInstance, sampleId: params.sampleId])
			} else {
				render status: 500
			}
		}else {
			render status: 500
		}
	}
		
	def edit(ProtocolInstance protocolInstance, Integer sampleId) {
		[]
	}
}
