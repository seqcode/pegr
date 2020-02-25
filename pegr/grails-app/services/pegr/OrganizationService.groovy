package pegr

import grails.gorm.services.Service

@Service(Organization)
interface OrganizationService {

    Organization get(Serializable id)

    List<Organization> list(Map args)

    Long count()

    void delete(Serializable id)

    Organization save(Organization organization)

}