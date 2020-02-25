package pegr

import grails.gorm.services.Service

@Service(AlignType)
interface AlignTypeService {

    AlignType get(Serializable id)

    List<AlignType> list(Map args)

    Long count()

    void delete(Serializable id)

    AlignType save(AlignType alignType)

}