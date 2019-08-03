package pegr

import grails.gorm.services.Service

@Service(TargetType)
interface TargetTypeService {

    TargetType get(Serializable id)

    List<TargetType> list(Map args)

    Long count()

    void delete(Serializable id)

    TargetType save(TargetType targetType)

}