package pegr
import grails.transaction.Transactional

class ProtocolController {
	def springSecurityService
	def protocolService
    
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
                render "<div class='errors'>Protocol Instance not found!</div>"
            }
        }catch(Exception e) {
            log.error "Error saving item", e
            render "<div class='errors'>Error saving this item!</div>"
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
            render "<div class='errors'>Error linking this item to the protocol instance!</div>"
        }   
    }
    
    def searchAjax() {        
        try {
            def items = Item.withCriteria {
                and {
                        eq "type.id", Long.parseLong(params.typeId)
                        eq "barcode", params.barcode                    
                    }
            }
            if (items && items.size()) {
                def protocolInstance = ProtocolInstance.get(params.prtclInstId)
                def linkedItemIds = protocolInstance.items*.id
                render template: "searchItems", model: [items:items, prtclInstId:params.prtclInstId, linkedItemIds: linkedItemIds]
                
            } else {
                render "<div class='errors'>No item found!</div>"
            }
        }catch(Exception e) {
            log.error "Error searching item within protocol instance", e
            render status: 404
        }
    }
}
