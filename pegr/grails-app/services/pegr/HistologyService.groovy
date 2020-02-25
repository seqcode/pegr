package pegr

import grails.gorm.services.Service

@Service(Histology)
interface HistologyService {

    Histology get(Serializable id)

    List<Histology> list(Map args)

    Long count()

    void delete(Serializable id)

    Histology save(Histology histology)

}