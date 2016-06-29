package pegr
import grails.converters.*

class AntibodyController {
    def itemService
    def antibodyService
    def utilityService
    
    def index(){
        redirect(action: "list")
    }
    
    def list(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        def itemType = ItemType.findByCategory(ItemTypeCategory.ANTIBODY)
        [objectList: Antibody.list(params), objectCount: Antibody.count(), currentType: itemType]
    }
    
    def show(Long id) {
        def antibody = Antibody.get(id)
        if (antibody) {
            [antibody: antibody]
        } else {
            render status: 404
        }
    }
    
    def save(Item item, AntibodyCommand antibodyCommand) {
        withForm{
            try {
                def antibody = antibodyService.save(item, antibodyCommand)
                flash.message = "New antibody added!"
                redirect(action: "show", id: antibody.id)
            }catch(ItemException e) {
                request.message = e.message
                render(view: "create", model: [item:item, antibody: antibodyCommand])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "create", model: [item:item, antibody: antibodyCommand])
            }
        }
    }
    
    def edit(Long antibodyId){
        def antibody = Antibody.get(antibodyId)
        if (!antibody) {
            render(view: "/404")
            return
        }
        def antibodyCommand = new AntibodyCommand(   
            id : antibody.id,
            company : antibody.company?.name,
            catalogNumber : antibody.catalogNumber,
            lotNumber : antibody.lotNumber,
            abHost : antibody.abHost?.name,
            immunogene : antibody.immunogene,
            clonal : antibody.clonal,
            igType : antibody.igType?.name,
            concentration : antibody.concentration)
        [antibody: antibodyCommand]
    }
    
    def update(AntibodyCommand cmd) {
        try {
            antibodyService.update(cmd)
            flash.message = "Antibody update!"
            redirect(action: "show", id: cmd.id)
        } catch(ItemException e) {
            request.message = e.message
            render(view:'edit', model:[antibody: cmd])
        } catch(Exception e) {
            request.message = "Error updating the antibody!"
            log.error "Error: ${e.message}", e
            render(view:'edit', model:[antibody: cmd])
        }
    }
    
    def editItem(Long antibodyId) {
        def antibody = Antibody.get(antibodyId)
        if (antibody) {            
            def item = antibody.item
            if (!item) {
                redirect(action: "addBarcode", params: [antibodyId: antibodyId])
            } else {
                [item: antibody.item, antibodyId: antibodyId]
            }
        } else {
            flash.message = "Antibody not found!"
            redirect(action: 'list')
        }
    }
    
    def updateItem(Long antibodyId, Long itemId) {
        def item = Item.get(itemId)
        item.properties = params
        try {
            itemService.save(item)
            flash.message = "Antibody update!"
            redirect(action: "show", id: antibodyId)
        }catch(ItemException e) {
            request.message = e.message
            render(view:'editItem', model:[item: item, antibodyId: antibodyId])
        }
    }
    
    def addBarcode(Long antibodyId) {
        def antibody = Antibody.get(antibodyId)
        if (antibody) {        
            if (request.method == "POST") {
                withForm {
                    def item = new Item(params)
                    try {
                        antibodyService.save(item, antibody)
                        flash.message = "Barcode added!"
                        redirect(action: "show", id: antibodyId)
                    }catch(ItemException e) {
                        request.message = e.message
                        render(view: "addBarcode", model: [item:item, antibodyId: antibodyId])
                    }                    
                }
            } else {
                def type = ItemType.findByName("Antibody")
                def item = new Item(type: type)
                [item:item, antibodyId: antibodyId]
            }
        } else {
            flash.message = "Antibody not found!"
            redirect(action: 'list')
        }
    }
    
    def delete(Long antibodyId) {
        try {   
            antibodyService.delete(antibodyId)
            flash.message = "Antibody deleted!"
            redirect(action: 'list')
        }catch(AntibodyException e) {
            flash.message = e.message
            redirect(action: 'show', id: antibodyId)
        }        
    }
    
    /* ----------------------------- Ajax ----------------------*/
     def fetchCompanyAjax() {
        def companies = Company.executeQuery("select c.name from Company c")
        render utilityService.stringToSelect2Data(companies) as JSON
    }
    
    def fetchCatalogAjax() {
        def catalogs = Antibody.executeQuery("select distinct a.catalogNumber from Antibody a")
        render utilityService.stringToSelect2Data(catalogs) as JSON
    }
    
    def fetchImmunogeneAjax() {
        def immunogenes = Antibody.executeQuery("select distinct a.immunogene from Antibody a")
        render utilityService.stringToSelect2Data(immunogenes) as JSON
    }
    
    def fetchAntibodyAjax(String catalog) {
        def antibody = Antibody.findByCatalogNumber(catalog)
        def result = [host: antibody?.abHost?.name, 
                immunogene: antibody?.immunogene, 
                clonal: antibody?.clonal?.name(), 
                ig: antibody?.igType?.name, 
                conc: antibody?.concentration,
                targetType: antibody?.defaultTarget?.targetType?.name, 
                target: antibody?.defaultTarget?.name, 
                cterm: antibody?.defaultTarget?.cTermTag,
                nterm: antibody?.defaultTarget?.nTermTag] 
        render result as JSON
    }
    
    def fetchTargetAjax() {
        def targets = Target.list()
        def result = [targets: utilityService.stringToSelect2Data(targets.collect{it.name}.unique()),
                     nterms: utilityService.stringToSelect2Data(targets.collect{it.nTermTag}.unique()),
                     cterms: utilityService.stringToSelect2Data(targets.collect{it.cTermTag}.unique())]
        render result as JSON
    }
    
}

class AntibodyCommand {
    String id
    String company
    String catalogNumber
    String lotNumber
    String abHost
    String immunogene
    String clonal
    String igType
    String concentration
}