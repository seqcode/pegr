package pegr

import grails.gorm.services.Service

@Service(Genome)
interface GenomeService {

    Genome get(Serializable id)

    List<Genome> list(Map args)

    Long count()

    void delete(Serializable id)

    Genome save(Genome genome)

}