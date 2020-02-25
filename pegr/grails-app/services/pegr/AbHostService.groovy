package pegr

import grails.gorm.services.Service

@Service(AbHost)
interface AbHostService {

    AbHost get(Serializable id)

    List<AbHost> list(Map args)

    Long count()

    void delete(Serializable id)

    AbHost save(AbHost abHost)

}