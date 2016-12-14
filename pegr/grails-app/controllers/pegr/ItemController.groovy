package pegr
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import grails.converters.*

class ItemController {
    def itemService
    def barcodeService
    def utilityService
    
    def index(){
        redirect(action: "list", params: [categoryId: 1])
    }
    
    def list(Integer max, Long categoryId) {
        params.max = Math.min(max ?: 15, 100)
        if(!categoryId) {
            categoryId = 1
        }
        def category = ItemTypeCategory.get(categoryId)
        def itemTypes = ItemType.list(sort: "name")
        switch (category.name) {
            case "Antibody":
                flash.message = flash.message
                redirect(controller: "antibody", action: "list", params: params)
                break
            case "Cell Stock":
                flash.message = flash.message
                redirect(controller: "cellSource", action: "list", params: params)
                break
            default:
                def items = Item.where { type.category.id == categoryId }
                [itemList: items.list(params), 
                 itemCount: items.count(), 
                 currentCategory: category,
                itemTypes: itemTypes]
        }
    }
    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render(view: "/404")
            return
        }
        def folder = itemService.getImageFolder(id)
        def images = folder.listFiles()
        switch (item.type.category.superCategory) {
            case ItemTypeSuperCategory.TRACED_SAMPLE:
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
        def itemType = typeId ? ItemType.get(typeId):null
        if (params.generate) {
            try {
                barcode = barcodeService.generateBarcode()
                render(view: "/item/generateBarcode", model: [itemType: itemType, barcode: barcode])
            } catch (BarcodeException e) {
                flash.message = e.message
                redirect(action: "list", params: [typeId: typeId])
            }
        } else {
            if (barcode?.trim()){
                if (itemType) { 
                    def item = Item.findByBarcode(barcode)
                    if (item) {
                        if (item.type == itemType) {
                            switch (itemType.category.superCategory) {
                                case ItemTypeSuperCategory.ANTIBODY:
                                    def antibody = Antibody.findByItem(item)
                                    redirect(controller: "antibody", action: "show", id: antibody?.id)
                                    break
                                default:
                                    redirect(action: "show", id: item.id)
                            }
                        } else {
                            flash.message = "The item with barcode ${barcode} has type ${item.type.name}, which does not match the input type ${itemType.name}!"
                            redirect(action: "list", params: [typeId: typeId])
                        }                        
                    }else {
                        item = new Item(type: itemType, barcode: barcode)
                        switch (itemType.category.superCategory) {
                            case ItemTypeSuperCategory.ANTIBODY:
                                render(view: "/antibody/create", model: [item:item])
                                break
                            case ItemTypeSuperCategory.TRACED_SAMPLE:
                                render(view: "/cellSource/create", model: [item: item])
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
    }
    
    def create(Item item) {
        [item: item]
    }
    
    def save() {
        withForm{        
            def item = new Item(params)
            try {
                itemService.save(item)
                itemService.updateCustomizedFields(item, params)
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

    def edit(Long itemId) {
        def item = Item.get(itemId)
        if (item) {
            item.properties = params
            [item: item]
        } else {
            flash.message = "Item not found!"
            redirect(controller: "item", action: "list")
        }        
    }
    
    def update(Long itemId) {
        def item = Item.get(itemId)
        if (item) {
            item.properties = params
            try {
                itemService.save(item)
                itemService.updateCustomizedFields(item, params)
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
            def fieldName = "image"
            def maxByte = 5 * 1024 * 1024
            def folderName = "items" + File.separator + params.itemId
            def allowedFileTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']
            utilityService.upload((MultipartHttpServletRequest)request, fieldName, allowedFileTypes, folderName, maxByte)
            flash.message = "Image uploaded!"
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: params.itemId)
    }
    
    
    def displayImage(String img, Long itemId) {
        File folder = itemService.getImageFolder(itemId); 
        File image = new File(folder, img)
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
    
    def displayBarcode(String barcode, Integer width, Integer height, String formatStr) {
        if (barcode && barcode != "") {
            barcodeService.renderImage(response, barcode, width, height, formatStr)
        }
    }
    
    def deleteImage(String img, Long itemId) {
        try {
            File folder = itemService.getImageFolder(itemId); 
            File image = new File(folder.getAbsolutePath() + File.separator + img)
            image.delete()
            flash.message = "Image deleted!"
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Image cannot be deleted!"
        }
        redirect(action: "show", id: params.itemId)
    }
    
    def getFileExtension(String s) {
        return s.substring(s.lastIndexOf('.') + 1)
    }
    
    def delete(Long itemId) {
        def typeId = Item.get(itemId)?.type?.id
        try {
            itemService.delete(itemId)
            flash.message = "Item deleted!"
    		redirect(action: 'list', params: [typeId: typeId])
        } catch(ItemException e) {
            flash.message = e.message
            redirect(action: 'show', id: itemId)
        }
    }
    
    def generateBarcodeAjax() {
        def barcode = barcodeService.generateBarcode()
        render barcode
    }
    
    def saveProjectAjax(Long itemId, Long projectId) {
        itemService.saveProject(itemId, projectId)
        render ""
    }
    
    def generateBarcodeList() {
        def barcodeList = barcodeService.generateBarcodeList(80)
        [barcodeList: barcodeList, date: new Date()]
    }
    
    def updateStatusAjax(Long itemId, String status) {
        def item = Item.get(itemId)
        item.status = status as ItemStatus
        itemService.save(item)
        def label
        switch (status) {
            case "GOOD":
                label = "label label-success"
                break
            case "BAD":
                label = "label label-danger"
                break
            default:
                label = "label label-warning"
        }
        def result = [status: status, label: label] as JSON
        render result
        return
    }
    
    def search(String str) {
        def c = Item.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort ?: "id",
                order: params.order ?: "desc",
                offset: params.offset
            ]
        def items = c.list(listParams) {
            or {
                ilike "name", "%${str}%"
                eq "barcode", "${str}"
                ilike "location", "%${str}%"
                type {
                    ilike "name", "%${str}%"
                }
                user {
                    ilike "username", "%${str}%"
                }
            }
        }
        [itemList: items, itemCount: items.totalCount, str: str]
    }
}

