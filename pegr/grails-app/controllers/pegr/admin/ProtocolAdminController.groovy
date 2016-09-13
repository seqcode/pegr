package pegr.admin
import pegr.AdminCategory
import pegr.Protocol
import pegr.ProtocolException
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ProtocolAdminController {

	public static AdminCategory category = AdminCategory.PROTOCOLS
    
    def protocolService
    def springSecurityService
    
    def index(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        [protocolList: Protocol.list(params), protocolCount: Protocol.count()]
    }
    
    def show(Long id) {
        def protocol = Protocol.get(id)
        def file = protocolService.getProtocolFile(id)
        [protocol: protocol, file: file.exists() ? file : null]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def protocol = new Protocol(params)
                protocol.user = springSecurityService.currentUser
                def protocolItemTypeIds = [ 
                        (pegr.ProtocolItemFunction.PARENT) : [params.long('startItemTypeId')],
                        (pegr.ProtocolItemFunction.CHILD) : [params.long('endItemTypeId')],
                        (pegr.ProtocolItemFunction.SHARED) : params.list('sharedItemTypeIds'),
                        (pegr.ProtocolItemFunction.START_POOL) : [params.long('startPoolTypeId')],
                        (pegr.ProtocolItemFunction.END_POOL) : [params.long('endPoolTypeId')]
                ]
                try {
                    protocolService.save(protocol, protocolItemTypeIds)
                    protocolService.uploadFile( (MultipartHttpServletRequest)request, protocol.id, "file")
                    flash.message = "New protocol saved!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "create", model: [protocol: protocol, 
                        startItemTypeId:params.startItemTypeId,
                        endItemTypeId:params.endItemTypeId,
                        sharedItemTypeIds:params.sharedItemTypeIds,
                        startPoolTypeId:params.startPoolTypeId,
                        endPoolTypeId:params.endPoolTypeId])
                }catch(Exception e) {
                    request.message = "Error saving this protocol!"
                    log.error "Error: ${e.message}", e
                    render(view: "create", model: [protocol: protocol,
                        startItemTypeId:params.startItemTypeId,
                        endItemTypeId:params.endItemTypeId,
                        sharedItemTypeIds:params.sharedItemTypeIds,
                        startPoolTypeId:params.startPoolTypeId,
                        endPoolTypeId:params.endPoolTypeId])
                }

            }
        }
    }
    
    def edit() {
        def protocol = Protocol.get(params.long('id'))
        if (!protocol) {
            flash.message = "Protocol does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                protocol.properties = params
                def protocolItemTypeIds = [ 
                        (pegr.ProtocolItemFunction.PARENT) : [params.long('startItemTypeId')],
                        (pegr.ProtocolItemFunction.CHILD) : [params.long('endItemTypeId')],
                        (pegr.ProtocolItemFunction.SHARED) : params.list('sharedItemTypeIds'),
                        (pegr.ProtocolItemFunction.START_POOL) : [params.long('startPoolTypeId')],
                        (pegr.ProtocolItemFunction.END_POOL) : [params.long('endPoolTypeId')]
                ]
                try {
                    protocolService.save(protocol, protocolItemTypeIds)
                    flash.message = "Protocol update!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "edit", model: [protocol: protocol,
                        startItemTypeId:params.startItemTypeId,
                        endItemTypeId:params.endItemTypeId,
                        sharedItemTypeIds:params.sharedItemTypeIds,
                        startPoolTypeId:params.startPoolTypeId,
                        endPoolTypeId:params.endPoolTypeId])
                }catch(Exception e) {
                    request.message = "Error updateing this protocol!"
                    log.error "Error: ${e.message}", e
                    render(view: "edit", model: [protocol: protocol,
                        startItemTypeId:params.startItemTypeId,
                        endItemTypeId:params.endItemTypeId,
                        sharedItemTypeIds:params.sharedItemTypeIds,
                        startPoolTypeId:params.startPoolTypeId,
                        endPoolTypeId:params.endPoolTypeId])
                }
            }
        }else {
            [protocol: protocol,
            startItemTypeId:protocol?.startItemType?.id,
            endItemTypeId:protocol?.endItemType?.id,
            sharedItemTypeIds:protocol?.sharedItemTypes*.id,
            startPoolTypeId:protocol?.startPoolType?.id,
            endPoolTypeId:protocol?.endPoolType?.id]
        }
    }
    
    
    def delete(Long id) {
        try {
            protocolService.delete(id)
            flash.message = "Protocol deleted!"
            redirect(action: "index")
        }catch(ProtocolException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        }
    }
    
    def upload(Long protocolId) {
        try {
            protocolService.uploadFile( (MultipartHttpServletRequest)request, protocolId, "file")
            flash.message = "Protocol uploaded!"
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error uploading the file!"
        }

        redirect(action: "show", id: protocolId)
    }

    def deleteFile(Long protocolId) {
        try {
            def file = protocolService.getProtocolFile(protocolId)
            file.delete()
            flash.message = "File deleted!"
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error deleting the file!"
        }
        redirect(action: "show", id: protocolId)
    }
    
    def renderFile(Long protocolId) {
        def file = protocolService.getProtocolFile(protocolId)
        response.setHeader "Content-disposition", "inline; filename=${file.getName()}"
        response.contentType = 'application/pdf'
        response.outputStream << file
        response.outputStream.flush()
        render(contentType: "application/pdf", contentDisposition: "inline", file: file, fileName: file.getName())
    }
}
