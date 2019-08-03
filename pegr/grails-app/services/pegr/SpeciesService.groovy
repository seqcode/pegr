package pegr

import grails.gorm.services.Service

@Service(Species)
interface SpeciesService {

    Species get(Serializable id)

    List<Species> list(Map args)

    Long count()

    void delete(Serializable id)

    Species save(Species species)

}