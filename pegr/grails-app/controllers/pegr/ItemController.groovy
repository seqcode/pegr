package pegr
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ItemController {
    def private final allowedTypes = ['image/png':'png', 'image/jpeg':'jpg', 'image/jpg':'jpg', 'image/gif':'gif']
    
    def itemService
    
    def index(){
        redirect(action: "list")
    }
    
    def list(Integer max, Long typeId) {
        params.max = Math.min(max ?: 15, 100)
        if(typeId) {
            def itemType = ItemType.get(typeId)
            if (itemType.name == "Antibody") {
                [objectList: Antibody.list(params), objectCount: Antibody.count(), template: 'antibodyTable']
            } else{
                def items = Item.where{ type.id == typeId }
                [objectList: items.list(params), objectCount: items.count(), template: 'itemTable']
            }
        } else {
            [objectList: Item.list(params), objectCount: Item.count(), template: 'itemTable']
        }
    }
    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render status: 404
        }
        def object = itemService.getObjectFromItem(item)
        def itemController = item.type.objectType ?: "item"
        def folder = getImageFolder(id)
        def images = folder.listFiles().findAll{getFileExtension(it.name) in allowedTypes.values()}
        [item: item, object: object, itemController: itemController, images: images]
    }
  
    def preview(Long typeId, String barcode) {
        if (barcode.trim()){
            def itemType = typeId ? ItemType.get(typeId):null
            if (itemType) { 
                def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
                if (item) {
                    redirect(action: "show", id: item.id)
                }else {
                    item = new Item(type: itemType, barcode: barcode)
                    def itemController = itemType.objectType ?: "item"
                    render(view: "create", model: [item:item, itemController: itemController])
                }
            }else {
                flash.message = "Item type not found!"
                redirect(action: "list")
            }
        }else {
            flash.message = "Barcode cannot be empty!"
            redirect(action: "list")
        }
    }
    
    def create(item, object, itemController) {
        [item:item, object: object, itemController:itemController]
    }
    
    def save() {
        withForm{        
            def item = new Item(params)
            def object = (params.itemController != "item") ? itemService.getClassFromObjectType(params.itemController).clazz.newInstance(params) : null
            try {
                itemService.save(item, object)
                flash.message = "New item added!"
                redirect(action: "list")
            }catch(ItemException e) {
                request.message = e.message
                render(view: "create", model: [item:item, object: object, itemController: params.itemController])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "create", model: [item:item, object: object, itemController: params.itemController])
            }
        }
    }

    def edit(Item item) {
        def object = itemService.getObjectFromItem(item)
        def itemController = item.type.objectType ?: "item"
        [item: item, object: object, itemController: itemController]
    }
    
    def update() {
        def item = Item.get(params.long('itemId'))
        item.properties = params
        def object = null
        if(item.type.objectType) {
            object = itemService.getObjectFromItem(item)
            if (object) {
                object.properties = params
            }else {
                def dc = itemService.getClassFromObjectType(item.type.objectType)
                object = dc.clazz.newInstance(params)
            }
        }  
        try {
            itemService.save(item, object)
            flash.message = "Item update!"
            redirect(action: "show", id: params.itemId)
        }catch(ItemException e) {
            request.message = e.message
			def itemController = item.type.objectType ?: "item"
            render(view:'edit', model:[item: item, object: object, itemController: itemController])
        }
    }
    
    def changeBarcode() {
        def item = Item.get(params.long('id'))
        if(request.method=='POST') {            
            try {
                itemService.changeBarcode(item, params.barcode)
                flash.message = "Barcode updated!"
            }catch(ItemException e) {
                flash.message = e.message
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                flash.message = "Error updating the barcode!"
            }
            redirect(action: "show", id: params.id)
        } else {
            [item: item]
        }
    }
    
    def updateParent(Long itemId, Long parentTypeId, String parentBarcode) {
        if(request.method == "POST") {
            try {
                itemService.updateParent(itemId, parentTypeId, parentBarcode)
            }catch(ItemException e) {
                flash.message = e.message
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                flash.message = "Error updating the parent!"
            }
            redirect(action: "show", id: itemId)
        } else {
            [itemId: itemId]
        }
    }
    
    def upload() {        
        try {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
            def mpf = mpr.getFile("image");
            String fileName = mpf.getOriginalFilename();
            String fileType = mpf.getContentType();

            if(!mpf?.empty && mpf.size < 15 * 1024 * 1024 && allowedTypes.containsKey(fileType)) {
                
                File folder = getImageFolder(params.long('itemId')); 
                if (!folder.exists()) { 
                    folder.mkdirs(); 
                } 
                def n = 1
                File fileDest = new File(folder, n + "." + allowedTypes[fileType]) 
                while(fileDest.exists()) {
                    n++
                    fileDest = new File(folder, n + "." + allowedTypes[fileType]) 
                } 
                mpf.transferTo(fileDest)
                flash.message = "Image uploaded!"
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error uploading the file!"
        }

        redirect(action: "show", id: params.itemId)
    }
    
    
    def displayImage(String img, Long itemId) {
        File folder = getImageFolder(itemId); 
        File image = new File(folder.getAbsolutePath() + File.separator + img)
        if(!image.exists()) {
            response.status = 404
        } else {
            BufferedImage originalImage = ImageIO.read(image)
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
            def fileType = getFileExtension(img)
            ImageIO.write(originalImage, fileType, outputStream)
            byte[] imageInByte = outputStream.toByteArray()
            response.setHeader("Content-Length", imageInByte.length.toString())
            response.contentType = "image/"+fileType
            response.outputStream << imageInByte
            response.outputStream.flush()
        }
    }
    
    def deleteImage(String img, Long itemId) {
        try {
            File folder = getImageFolder(itemId); 
            File image = new File(folder.getAbsolutePath() + File.separator + img)
            image.delete()
            flash.message = "Image deleted!"
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Image cannot be deleted!"
        }
        redirect(action: "show", id: params.itemId)
    }
    
    def getImageFolder(Long itemId){
        File folder = new File("files/items/${itemId}"); 
    }
    
    def getFileExtension(String s) {
        return s.substring(s.lastIndexOf('.') + 1)
    }
    
    def delete(Long id) {

        try {
            def folder = getImageFolder(id)
            itemService.delete(id, folder)
            flash.message = "Item deleted!"
    		redirect(action: 'list')
        } catch(ItemException e) {
            flash.message = e.message
            redirect(action: 'show', id: id)
        }
    }
    
    def strainChangedAjax(Long strainId) {
        def strain = Strain.get(strainId)
        def growthMedias = GrowthMedia.where{
            if(strain?.genotype?.species) {
                (species == null) || (species == strain?.genotype?.species)
            }
        }.list()
        render g.select(id: 'growthMedia', name:'growthMedia.id', from: growthMedias, optionKey: 'id', noSelection:[null:''])
    }
}
