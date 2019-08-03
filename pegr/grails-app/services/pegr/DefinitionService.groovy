package pegr

import grails.gorm.services.Service

@Service(Definition)
interface DefinitionService {

    Definition get(Serializable id)

    List<Definition> list(Map args)

    Long count()

    void delete(Serializable id)

    Definition save(Definition definition)

}