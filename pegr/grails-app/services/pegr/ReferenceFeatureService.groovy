package pegr

import grails.gorm.services.Service

@Service(ReferenceFeature)
interface ReferenceFeatureService {

    ReferenceFeature get(Serializable id)

    List<ReferenceFeature> list(Map args)

    Long count()

    void delete(Serializable id)

    ReferenceFeature save(ReferenceFeature referenceFeature)

}