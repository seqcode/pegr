package pegr.admin
import pegr.AdminCategory;
import pegr.Target

class TargetAdminController {
    def utilityService
    def antibodyService
    static scaffold = Target
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
    
    def mergeSearch(String str) {
        def c = Target.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort,
                order: params.order,
                offset: params.offset
            ]
        def targets = c.list(listParams) {
            or {
                ilike "name", "%${str}%"
                ilike "cTermTag", "%${str}%"
                ilike "nTermTag", "%${str}%"
            }
        }

        def checkedCount = 0;
        if (session.checkedTarget) {
            checkedCount = session.checkedTarget.size()
        }
        [totalCount: targets.totalCount, targets: targets, str: str, checkedCount: checkedCount]
    }
    
    def merge(String targetName, String targetType, String nTermTag, String cTermTag) {
        if (session.checkedTarget) {
            try {
                def mergeToTarget = antibodyService.getTarget(targetName, targetType, nTermTag, cTermTag)
                def toId = mergeToTarget.id
                session.checkedTarget.each { fromId ->
                    if (fromId != toId) {
                        utilityService.mergeRowsInDb("target", fromId, toId)
                    }
                }
                session.checkedTarget = null
                flash.message = "Success!"
                redirect(action: "index")
            } catch (Exception e) {
                flash.message = e.message 
                redirect(action: "showChecked")
            }
        } else {
            flash.message = "No Targets selected to merge from!"
            redirect(action: "index")
        }
    }
}
