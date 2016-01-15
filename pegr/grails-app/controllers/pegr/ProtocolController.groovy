package pegr
import grails.transaction.Transactional

class ProtocolController {
	def springSecurityService
	def protocolService
    
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
    
	def createInstanceForSample() {
        try {
            def protocolInstance = protocolService.createInstanceForSample(params.protocolId, params.sampleId, params.priorProtInstId)
            render(view: "editInstanceForSample", 
                    model: [protocolInstance: protocolInstance, sampleId: params.sampleId])
        } catch(Exception e) {
            flash.message = e.message
            redirect(action: "showProtocolsForSample", params: [sampleId: params.sampleId])
        }
	}

	def showInstanceForSample(Integer prtclInstanceId, Integer sampleId) {
        def protocolInstance = ProtocolInstance.get(prtclInstanceId)
        if (protocolInstance) {
            render(view: "editInstanceForSample", 
					model: [protocolInstance:protocolInstance, sampleId: sampleId])
        } else {
            render status: 500
        }
	}
    
    @Transactional
    def addItemToPrtclInstanceAjax(){
        try {
            def item = new Item(params)
            def protocolInstance = ProtocolInstance.get(params.prtclInstId)
            if (protocolInstance) {
                if (item.save(flush:true) ) {
                    protocolInstance.addToItems(item).save(flush: true)

                    render template: '/item/detailSquare', collection: protocolInstance.items, var: 'itemInstance'
                } else {
                    render template: '/layouts/error', bean: item
                }
            }else {
                render "Protocol Instance not found!"
            }
        }catch(Exception e) {
            log.error "Error saving item", e
            render "Error saving this item!"
        }   
    }
    
    @Transactional
    def linkItemToPrtclInstanceAjax() {
        try {
            def item = Item.get(params.itemId)
            def protocolInstance = ProtocolInstance.get(params.prtclInstId)
            if (protocolInstance && item) {    
                protocolInstance.addToItems(item).save(flush: true)
                render template: '/item/detailSquare', collection: protocolInstance.items, var: 'itemInstance'                
            }else {
                throw new Exception()
            }
        }catch(Exception e) { 
            log.error "Error linking item to protocol instance", e
            render "Error linking this item to the protocol instance!"
        }   
    }
    
    def searchAjax() {        
        def items = Item.withCriteria {
            and {
                    eq "type.id", Long.parseLong(params.typeId)
                    eq "barcode", params.barcode                    
                }
        }
        render template: "searchItems", model: [items:items, prtclInstId:params.prtclInstId]
    }
    
    @Transactional
    def completePrtclInst() {
        def protocolInstance = ProtocolInstance.get(params.prtclInstId)
        protocolInstance.completed = true
        protocolInstance.save(flush:true)
        redirect(action:"showProtocolsForSample", params: [sampleId: params.sampleId])
    }
}
