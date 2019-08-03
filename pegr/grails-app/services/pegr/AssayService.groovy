package pegr

import grails.gorm.services.Service

@Service(Assay)
interface AssayService {

    Assay get(Serializable id)

    List<Assay> list(Map args)

    Long count()

    void delete(Serializable id)

    Assay save(Assay assay)

}