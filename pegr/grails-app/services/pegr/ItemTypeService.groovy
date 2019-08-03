package pegr

import grails.gorm.services.Service

@Service(ItemType)
interface ItemTypeService {

    ItemType get(Serializable id)

    List<ItemType> list(Map args)

    Long count()

    void delete(Serializable id)

    ItemType save(ItemType itemType)

}