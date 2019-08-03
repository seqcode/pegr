package pegr

import grails.gorm.services.Service

@Service(Aligner)
interface AlignerService {

    Aligner get(Serializable id)

    List<Aligner> list(Map args)

    Long count()

    void delete(Serializable id)

    Aligner save(Aligner aligner)

}