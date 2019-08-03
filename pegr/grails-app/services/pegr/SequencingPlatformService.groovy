package pegr

import grails.gorm.services.Service

@Service(SequencingPlatform)
interface SequencingPlatformService {

    SequencingPlatform get(Serializable id)

    List<SequencingPlatform> list(Map args)

    Long count()

    void delete(Serializable id)

    SequencingPlatform save(SequencingPlatform sequencingPlatform)

}