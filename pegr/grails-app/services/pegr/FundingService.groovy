package pegr

import grails.gorm.services.Service

@Service(Funding)
interface FundingService {

    Funding get(Serializable id)

    List<Funding> list(Map args)

    Long count()

    void delete(Serializable id)

    Funding save(Funding funding)

}