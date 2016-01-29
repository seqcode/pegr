package pegr
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ItemController {
    def private final allowedTypes = ['image/png':'png', 'image/jpeg':'jpg', 'image/jpg':'jpg', 'image/gif':'gif']
    
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
        def folder = getImageFolder(id)
        def images = folder.listFiles().findAll{getFileExtension(it.name) in allowedTypes.values()}
        [item: item, object: object, itemController: itemController, images: images]
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
    
    def getImageFolder(Long itemId){
        def webrootDir = servletContext.getRealPath("/")
        File folder = new File(webrootDir, "files/items/${itemId}"); 
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
}
