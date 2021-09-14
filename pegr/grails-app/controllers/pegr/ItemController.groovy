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
        params.max = Math.min(max ?: 50, 100)
        if(!categoryId) {
            categoryId = 1
        }
        def category = ItemTypeCategory.get(categoryId)
        def itemTypes = itemService.getCategorizedItemTypes()
        
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
                def items
                if (params.active == "active") {
                    items = Item.where { type.category.id == categoryId && active == true}
                } else if (params.active == "inactive") {
                    items = Item.where { type.category.id == categoryId && active == false}
                } else {
                    items = Item.where { type.category.id == categoryId }
                }
                
                def orderLink = utilityService.getInventoryExternalLink()
                [itemList: items.list(params), 
                 itemCount: items.count(), 
                 currentCategory: category,
                 itemTypes: itemTypes,
                 orderLink: orderLink,
                 active: params.active
                ]
        }
    }
    
    def show(Long id) {
        def item = Item.get(id)
        if(!item) {
            render(view: "/404")
            return
        }
        def cellSource = CellSource.findByItem(item)
        if (cellSource) {
            redirect(controller: "cellSource", action: "show", id: cellSource.id)
            return
        }
        def antibody = Antibody.findByItem(item)
        if (antibody) {
            redirect(controller: "antibody", action: "show", id: antibody.id)
            return
        }
        def folder = itemService.getImageFolder(id)
        def images = folder.listFiles()
        switch (item.type.category.superCategory) {
            case ItemTypeSuperCategory.TRACED_SAMPLE:
                def traces = []
                def tmp = item
                while(tmp.parent) {
                    traces << tmp.parent
                    tmp = tmp.parent
                }
                cellSource = CellSource.findByItem(tmp)
                [item: item, images: images, traces: traces, cellSource: cellSource]
                break
            default:
                [item: item, images: images]
        }                    
    }
  
    def preview(Long typeId, String barcode) {
        if (params.generate) {
            def itemType = typeId ? ItemType.get(typeId):null
            try {
                barcode = barcodeService.generateBarcode()
                render(view: "/item/generateBarcode", model: [itemType: itemType, barcode: barcode])
            } catch (BarcodeException e) {
                flash.message = e.message
                redirect(action: "list", params: [typeId: typeId])
            }
        } else if (params.search) {            
            if (barcode?.trim()){
                def item = Item.findByBarcode(barcode)
                if (item) {
                    switch (item.type.category.superCategory) {
                        case ItemTypeSuperCategory.ANTIBODY:
                            def antibody = Antibody.findByItem(item)
                            redirect(controller: "antibody", action: "show", id: antibody?.id)
                            break
                        default:
                            redirect(action: "show", id: item.id)
                    }     
                } else {
                    render(view: "previewCreate", model: [barcode: barcode])
                }                
            } else {
                flash.message = "Barcode cannot be empty!"
                redirect(action: "list", params: [typeId: typeId])
            }
        } else {
            def itemType = typeId ? ItemType.get(typeId):null
            def item = new Item(type: itemType, barcode: barcode)
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
    
    def updateActiveAjax(Long itemId, boolean active) {
        print(active)
        def item = Item.get(itemId)
        item.active = active
        itemService.save(item)
        def label
        switch (active) {
            case true:
                label = "label label-success"
                break
            case false:
                label = "label label-danger"
                break
            default:
                label = "label label-warning"
        }
        def result = [active: active, label: label] as JSON
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
    
    def printBarcode(Long itemId, int row, int col, int copies) {
        def item = Item.get(itemId)
        if (!item) {
            render(view: "/404")
            return
        }
        def items = []
        def nullCount = 5 * (row - 1) + col - 1
        for (int i = 0; i < nullCount; ++i) {
            items << null
        }
        for (int i = 0; i < copies; ++i) {
            items << item 
        }        
        render(view: "/item/generateBarcodeList", model: [barcodeList: items*.barcode, nameList: items*.name*.take(20), date: new Date()])
    }
    
    def batchEdit(Long instanceId) {
        def instance = ProtocolInstance.get(instanceId)
        def items = ProtocolInstanceItems.findAllByFunctionAndProtocolInstance(ProtocolItemFunction.CHILD, instance).sort {it.id}.collect { it.item }
        def itemTypes = items*.type.unique()
        if (itemTypes.size() == 0) {
            render "No items found!"
            return
        } else if (itemTypes.size() >1) {
            render "Items must have the same type!"
            return
        }
        [items: items, itemType: itemTypes[0], instanceId: instanceId]
    }
    
    def batchSave(ItemListCommand cmd) {
        itemService.batchSave(cmd.items)
        flash.message = "The items have been updated!"
        redirect(action: "batchEdit", params: [instanceId: cmd.instanceId])
    }
    
    def queryAjax(String barcode) {
        def item = Item.findByBarcode(barcode)
        def result
        if (item) {
            result = [id: item.id, barcode: item.barcode, name: item.name, type: item.type.name, status: item.status]   
        }
        render result as JSON
        return 
    }
    
    def searchTracedSamplesAjax(String str) {
        def c = Item.createCriteria()
        def items = c.list() {
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
        def itemsArray = []
        items.each {item->
            itemsArray << [item.id, item.type.name, item.name, item.barcode, item.location, item.user.username, item.status]
        }
        render itemsArray as JSON
        return
    }
}


class ItemListCommand {
    Long instanceId
    List<Map> items
}