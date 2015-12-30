package pegr

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartHttpServletRequest 

@Transactional(readOnly = true)
class ItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Item.list(params), model:[itemInstanceCount: Item.count()]
    }

    def show(Item itemInstance) {
        respond itemInstance
    }

    def create() {
        respond new Item(params)
    }

    def upload() {
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
        def mpf = mpr.getFile("image");

        if(!mpf?.empty && mpf.size < 1024 * 1024) {
            def webrootDir = servletContext.getRealPath("/")
            File fileDest = new File(webrootDir, "images/items.png") 
            mpf.transferTo(fileDest)
        }
    }
    
    @Transactional
    def save(Item itemInstance) {
        if (itemInstance == null) {
            notFound()
            return
        }

        if (itemInstance.hasErrors()) {
            respond itemInstance.errors, view:'create'
            return
        }

        itemInstance.save flush:true
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])
		redirect(controller: 'ItemAdmin', action: 'index') 
		
    }

    def edit(Item itemInstance) {
        respond itemInstance
    }

    @Transactional
    def update(Item itemInstance) {
        if (itemInstance == null) {
            notFound()
            return
        }

        if (itemInstance.hasErrors()) {
            respond itemInstance.errors, view:'edit'
            return
        }

        itemInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Item.label', default: 'Item'), itemInstance.id])
                redirect itemInstance
            }
            '*'{ respond itemInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Item itemInstance) {

        if (itemInstance == null) {
            notFound()
            return
        }

        itemInstance.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Item.label', default: 'Item'), itemInstance.id])
		redirect(controller: 'ItemAdmin', action: 'index')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
