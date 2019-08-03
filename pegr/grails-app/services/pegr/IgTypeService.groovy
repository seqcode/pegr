package pegr

import grails.gorm.services.Service

@Service(IgType)
interface IgTypeService {

    IgType get(Serializable id)

    List<IgType> list(Map args)

    Long count()

    void delete(Serializable id)

    IgType save(IgType igType)

}