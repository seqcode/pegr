package pegr

import grails.gorm.services.Service

@Service(Sex)
interface SexService {

    Sex get(Serializable id)

    List<Sex> list(Map args)

    Long count()

    void delete(Serializable id)

    Sex save(Sex sex)

}