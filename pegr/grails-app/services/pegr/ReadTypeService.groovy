package pegr

import grails.gorm.services.Service

@Service(ReadType)
interface ReadTypeService {

    ReadType get(Serializable id)

    List<ReadType> list(Map args)

    Long count()

    void delete(Serializable id)

    ReadType save(ReadType readType)

}