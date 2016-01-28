package pegr

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ItemController {

    def index(){}
    
    def preview(Long typeId, String barcode) {
        def itemType = ItemType.get(typeId)
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
        if (item) {
            redirect(action: "show", id: item.id)
        }else {
            item = new Item(type: itemType, barcode: barcode)
            redirect(action: "edit", params: [typeId:typeId, barcode:barcode])
        }
    }
    
        // TODO
    def antibodyList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Item.list(params), model:[itemInstanceCount: Item.count()]
    }

    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render status: 404
        }
        if(item.type.objectType && item.referenceId){
            def dc = grailsApplication.getDomainClass(item.type.objectType)            
            render(view: item.type.objectType, model: [item: item, object: dc.get(item.referenceId)])   
        } else {
            [item:item]
        }
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
    def save() {
		def item = new Item(params)
        item.save(flush: true)
        if(item.type.objectType){
            def dc = grailsApplication.getDomainClass(item.type.objectType) 
            def object = dc.clazz.newInstance(params)
            object.save(flush: true)
        }
        flash.message = "New item added!"
        redirect(action: "index")
    }

    def edit(Item item) {
        if(item.type.objectType){
            def dc = grailsApplication.getDomainClass(item.type.objectType) 
            def object = item.referenceId ? dc.get(item.referenceId) : null
            render(view: "edit" + item.type.objectType, model: [item: item, object: object]) 
        }else{
            render(view: "edit", model: [item: item])
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
