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
        def category = ItemTypeCategory.findBySuperCategory(ItemTypeSuperCategory.ANTIBODY)
        def itemTypes = ItemType.list(sort: "name")
        [objectList: Antibody.list(params),
         objectCount: Antibody.count(),
         currentCategory: category,
         itemTypes: itemTypes
        ]
    }

    def show(Long id) {
        def antibody = Antibody.get(id)
        if (antibody) {
            def images
            if (antibody.item) {
                def folder = itemService.getImageFolder(antibody.item.id)
                images = folder.listFiles()
            }
            [antibody: antibody, images: images]
        } else {
            render status: 404
        }
    }

    def save(Item item, AntibodyCommand antibodyCommand) {
        withForm{
            try {
                def antibody = antibodyService.save(item, antibodyCommand)
                itemService.updateCustomizedFields(item, params)
                flash.message = "New antibody added!"
                redirect(action: "show", id: antibody.id)
            }catch(AntibodyException e) {
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
            antibodyId : antibody.id,
            company : antibody.company?.name,
            catalogNumber : antibody.catalogNumber,
            lotNumber : antibody.lotNumber,
            abHost : antibody.abHost?.name,
            immunogene : antibody.immunogene,
            clonal : antibody.clonal,
            igType : antibody.igType?.name,
            concentration : antibody.concentration,
            targetType : antibody.defaultTarget?.targetType,
            target : antibody.defaultTarget?.name,
            cterm : antibody.defaultTarget?.cTermTag,
            nterm : antibody.defaultTarget?.nTermTag
        )
        [antibody: antibodyCommand]
    }

    def update(AntibodyCommand cmd) {
        try {
            antibodyService.update(cmd)
            flash.message = "Antibody update!"
            redirect(action: "show", id: cmd.antibodyId)
        } catch(AntibodyException e) {
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
            itemService.updateCustomizedFields(item, params)
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
                        itemService.updateCustomizedFields(item, params)
                        flash.message = "Barcode added!"
                        redirect(action: "show", id: antibodyId)
                    }catch(AntibodyException e) {
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
        // def targets = Target.list()
        def result = [types: utilityService.stringToSelect2Data(TargetType.list(sort:"name").collect{it.name}),
                    targets: utilityService.stringToSelect2Data(Target.list(sort:"name").collect{it.name}.unique()),
                     nterms: utilityService.stringToSelect2Data(Target.list(sort:"nTermTag").collect{it.nTermTag}.unique()),
                     cterms: utilityService.stringToSelect2Data(Target.list(sort:"cTermTag").collect{it.cTermTag}.unique())]
        render result as JSON
    }

}

class AntibodyCommand implements grails.validation.Validateable {
    Long antibodyId
    String company
    String catalogNumber
    String lotNumber
    String abHost
    String immunogene
    String clonal
    String igType
    String concentration
    String targetType
    String target
    String cterm
    String nterm
}
