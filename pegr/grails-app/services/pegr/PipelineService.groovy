package pegr

import grails.gorm.services.Service

@Service(Pipeline)
interface PipelineService {

    Pipeline get(Serializable id)

    List<Pipeline> list(Map args)

    Long count()

    void delete(Serializable id)

    Pipeline save(Pipeline pipeline)

}