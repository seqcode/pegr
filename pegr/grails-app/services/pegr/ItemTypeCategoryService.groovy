package pegr

import grails.gorm.services.Service

@Service(ItemTypeCategory)
interface ItemTypeCategoryService {

    ItemTypeCategory get(Serializable id)

    List<ItemTypeCategory> list(Map args)

    Long count()

    void delete(Serializable id)

    ItemTypeCategory save(ItemTypeCategory itemTypeCategory)

}