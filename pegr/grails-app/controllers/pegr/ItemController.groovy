package pegr

import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ItemController {

    def itemService
    
    def index(){}
    
    def list(Integer max, Long typeId) {
        params.max = Math.min(max ?: 10, 100)
        def items = Item.where{
            if(typeId) {type.id == typeId}
        }

        [itemList: items.list(params), itemCount: items.count()]
    }
    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render status: 404
        }
        def object = itemService.getObjectFromItem(item.type.objectType, item.referenceId)
        def itemController = item.type.objectType ?: "item"
        [item: item, object: object, itemController: itemController]
    }
  
    def preview(Long typeId, String barcode) {
        def itemType = ItemType.get(typeId)
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
        if (item) {
            redirect(action: "show", id: item.id)
        }else {
            item = new Item(type: itemType, barcode: barcode)
            def itemController = itemType.objectType ?: "item"
            render(view: "create", model: [item:item, itemController: itemController])
        }
    }
    
    def save() {
        try {
            itemService.save(params)
            flash.message = "New item added!"
            redirect(action: "index")
        }catch(ItemException e) {
            flash.message = e.message
            redirect(action: "index")
        }
    }

    def edit(Item item) {
        def object = itemService.getObjectFromItem(item.type.objectType, item.referenceId)
        def itemController = item.type.objectType ?: "item"
        [item: item, object: object, itemController: itemController]
    }
    
    def update() {
        try {
            itemService.update(params)
            flash.message = "Item update!"
            redirect(action: "show", id: params.itemId)
        }catch(ItemException e) {
            flash.message = e.message
            redirect(action: "index")
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
