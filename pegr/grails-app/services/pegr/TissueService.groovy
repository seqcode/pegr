package pegr

import grails.gorm.services.Service

@Service(Tissue)
interface TissueService {

    Tissue get(Serializable id)

    List<Tissue> list(Map args)

    Long count()

    void delete(Serializable id)

    Tissue save(Tissue tissue)

}