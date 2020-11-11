package pegr
import grails.converters.*

class ItemTypeController {
    def utilityService
    
    def fetchItemTypesAjax(String superCatalog) {
        def types = ItemType.where{category.superCategory==superCatalog}.list(sort:'name')
        def result = utilityService.objectToSelect2Data(types)
        render result as JSON
    }
}