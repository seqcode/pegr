package pegr

import grails.gorm.services.Service

@Service(GrowthMedia)
interface GrowthMediaService {

    GrowthMedia get(Serializable id)

    List<GrowthMedia> list(Map args)

    Long count()

    void delete(Serializable id)

    GrowthMedia save(GrowthMedia growthMedia)

}