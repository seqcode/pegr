package pegr

import grails.gorm.services.Service

@Service(CellSourceTreatment)
interface CellSourceTreatmentService {

    CellSourceTreatment get(Serializable id)

    List<CellSourceTreatment> list(Map args)

    Long count()

    void delete(Serializable id)

    CellSourceTreatment save(CellSourceTreatment cellSourceTreatment)

}