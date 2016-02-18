package pegr
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ItemController {
    def private final allowedTypes = ['image/png':'png', 'image/jpeg':'jpg', 'image/jpg':'jpg', 'image/gif':'gif']
    
    def itemService
    
    def index(){
        redirect(action: "list", params: [typeId: 1])
    }
    
    def list(Integer max, Long typeId) {
        params.max = Math.min(max ?: 15, 100)
        if(!typeId) {
            typeId = 1
        }
        def itemType = ItemType.get(typeId)
        switch (itemType.category) {
            case ItemTypeCategory.ANTIBODY:
                redirect(controller: "antibody", action: "list", params: params)
                break
            default:
                def items = Item.where{ type.id == typeId }
                [objectList: items.list(params), objectCount: items.count(), currentType: itemType]
        }
    }
    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render status: 404
        }
        def folder = getImageFolder(id)
        def images = folder.listFiles().findAll{getFileExtension(it.name) in allowedTypes.values()}
        switch (item.type.category) {
            case ItemTypeCategory.TRACED_SAMPLE:
                def traces = []
                def tmp = item
                while(tmp.parent) {
                    traces.push(tmp.parent)
                    tmp = tmp.parent
                }
                def cellSource = CellSource.findByItem(item)
                [item: item, images: images, traces: traces, cellSource: cellSource]
                break
            default:
                [item: item, images: images]
        }                    
    }
  
    def preview(Long typeId, String barcode) {
        if (barcode?.trim()){
            def itemType = typeId ? ItemType.get(typeId):null
            if (itemType) { 
                def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
                if (item) {
                    switch (itemType.category) {
                        case ItemTypeCategory.ANTIBODY:
                            def antibody = Antibody.findByItem(item)
                            redirect(controller: "antibody", action: "show", id: antibody?.id)
                            break
                        default:
                            redirect(action: "show", id: item.id)
                    }
                }else {
                    item = new Item(type: itemType, barcode: barcode)
                    switch (itemType.category) {
                        case ItemTypeCategory.ANTIBODY:
                            redirect(controller: "antibody", action: "create", params: [barcode:barcode])
                            break
                        case ItemTypeCategory.TRACED_SAMPLE:
                            render(view: "createTracedSample", model: [item: item])
                            break
                        default:
                            render(view: "create", model: [item: item])
                    }
                }
            }else {
                flash.message = "Item type not found!"
                redirect(action: "list", params: [typeId: typeId])
            }
        }else {
            flash.message = "Barcode cannot be empty!"
            redirect(action: "list", params: [typeId: typeId])
        }
    }
    
    def create(Item item) {
        [item: item]
    }
    
    def createTracedSample(Item item, CellSource object) {
        [item: item, object: object]
    }
    
    def save() {
        withForm{        
            def item = new Item(params)
            try {
                itemService.save(item)
                flash.message = "New item added!"
                redirect(action: "show", id: item.id)
            }catch(ItemException e) {
                request.message = e.message
                render(view: "create", model: [item:item])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "create", model: [item:item])
            }
        }
    }
    
    def saveWithCellSource() {
        withForm{        
            def item = new Item(params)
            def object = new CellSource(params)
            try {
                itemService.save(item, object)
                flash.message = "New traced sample added!"
                redirect(action: "show", id: item.id)
            }catch(ItemException e) {
                request.message = e.message
                render(view: "createTracedSample", model: [item:item, object: object])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "createTracedSample", model: [item:item, object: object])
            }
        }
    }

    def edit(Item item) {
        [item: item]
    }
    
    def update(Long itemId) {
        def item = Item.get(itemId)
        if (item) {
            item.properties = params
            try {
                itemService.save(item)
                flash.message = "Item update!"
                redirect(action: "show", id: params.itemId)
            }catch(ItemException e) {
                request.message = e.message
                render(view:'edit', model:[item: item])
            }            
        } else {
            flash.message = "Item not found!"
            redirect(controller: "item", action: "list")
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
        def typeId = Item.get(id)?.type?.id
        try {
            def folder = getImageFolder(id)
            itemService.delete(id, folder)
            flash.message = "Item deleted!"
    		redirect(action: 'list', params: [typeId: typeId])
        } catch(ItemException e) {
            flash.message = e.message
            redirect(action: 'show', id: id)
        }
    }
}
