package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Target

class TargetAdminController {
    def utilityService
    def antibodyService
    
    TargetService targetService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Target.hasProperty("name")) {
            def c = Target.createCriteria()
            def listParams = [
                    max: max ?: 25,
                    sort: params.sort ?: "id",
                    order: params.order ?: "desc",
                    offset: params.offset
                ]
            def likeStr = "%" + str + "%"
            def items = c.list(listParams) {
                or {
                    ilike "name", likeStr
                }
            }
            respond items, model:[targetCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Target.list(params), model:[targetCount: Target.count()]
        }
    }

    def show(Long id) {
        respond targetService.get(id)
    }

    def create() {
        respond new Target(params)
    }

    def save(Target target) {
        if (target == null) {
            notFound()
            return
        }

        try {
            targetService.save(target)
        } catch (ValidationException e) {
            respond target.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'target.label', default: 'Target'), target.id])
                redirect action: "show", id: target.id
            }
            '*' { respond target, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond targetService.get(id)
    }

    def update(Target target) {
        if (target == null) {
            notFound()
            return
        }

        try {
            targetService.save(target)
        } catch (ValidationException e) {
            respond target.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'target.label', default: 'Target'), target.id])
                redirect action: "show", id: target.id
            }
            '*'{ respond target, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        targetService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'target.label', default: 'Target'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'target.label', default: 'Target'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
    
    /**
     * Given the search string, search for targets with matching name, c-term tag or n-term tag.
     * @param str the search string
     */
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
    
    /**
     * Get target IDs stored from session and query the targets form the IDs.
     */
    def showChecked(){
        def targets = []
        if (session.checkedTarget) {
            session.checkedTarget.each {
                def target = Target.get(it)
                if (target) {
                    targets.push(Target.get(it))
                }
            }
        }
        if (targets.size() == 0) {
            flash.message = "No targets to merge!"
            redirect(action: "index")
        }
        
        [list: targets]
    }
    
    /**
     * Merge the targets stored in session to the target with the given name, type, n-term tag 
     * and c-term tag. If the "merge to" target does not exist yet, a new target will be saved first.
     * Note that this function is called after search in admin/target, and allows for multiple 
     * targets merged at a time. After the merge, all references to the "merge from" targets will be 
     * changed to the "merge to" target and the "merge from" targets will be removed.
     */
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
    
    /**
     * Clear the session and return to the admin/target page
     */
    def cancelMerge() {
        if (session.checkedTarget) {
            session.checkedTarget = null
        }        
        redirect(action: "index")
    }
    
    /**
     * Clear the stored target IDs in session
     */
    def clearCheckedTargetAjax(){
        if (session.checkedTarget) {
            session.checkedTarget = null
        }
        render true
    }

    /**
     * Given a target ID, store it in the session.
     * @param id target ID
     * Return the number of targets stored in the session.
     */
    def addCheckedTargetAjax(Long id) {
        if (!session.checkedTarget) {
            session.checkedTarget = []
        }
        if (!(id in session.checkedTarget)) {
            session.checkedTarget << id
        }
        render session.checkedTarget.size()
    }

    /**
     * Given a target ID, remove it from the session.
     * Return the number of targets stored in the session.
     * @param id target ID
     */
    def removeCheckedTargetAjax(Long id) {
        if (id in session.checkedTarget) {
            session.checkedTarget.remove(id)
        }
        render session.checkedTarget.size()
    }

}
