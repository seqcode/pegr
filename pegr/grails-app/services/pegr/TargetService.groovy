package pegr

import grails.gorm.services.Service

@Service(Target)
interface TargetService {

    Target get(Serializable id)

    List<Target> list(Map args)

    Long count()

    void delete(Serializable id)

    Target save(Target target)

}