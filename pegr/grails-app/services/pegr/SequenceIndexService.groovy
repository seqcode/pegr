package pegr

import grails.gorm.services.Service

@Service(SequenceIndex)
interface SequenceIndexService {

    SequenceIndex get(Serializable id)

    List<SequenceIndex> list(Map args)

    Long count()

    void delete(Serializable id)

    SequenceIndex save(SequenceIndex sequenceIndex)

}