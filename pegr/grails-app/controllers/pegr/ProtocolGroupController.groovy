package pegr



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProtocolGroupController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProtocolGroup.list(params), model:[protocolGroupInstanceCount: ProtocolGroup.count()]
    }

    def show(ProtocolGroup protocolGroupInstance) {
        respond protocolGroupInstance
    }

    def create() {
        respond new ProtocolGroup(params)
    }

    @Transactional
    def save(ProtocolGroup protocolGroupInstance) {
        withForm {
            if (protocolGroupInstance == null) {
                notFound()
                return
            }

            if (protocolGroupInstance.hasErrors()) {
                respond protocolGroupInstance.errors, view:'create'
                return
            }

            protocolGroupInstance.save flush:true

            flash.message = message(code: 'default.created.message', args: [message(code: 'protocolGroup.label', default: 'ProtocolGroup'), protocolGroupInstance.id])
            redirect(controller: 'ProtocolGroupAdmin', action: 'index') 
        }
    }

    def edit(ProtocolGroup protocolGroupInstance) {
        respond protocolGroupInstance
    }

    @Transactional
    def update(ProtocolGroup protocolGroupInstance) {
        withForm {
            if (protocolGroupInstance == null) {
                notFound()
                return
            }

            if (protocolGroupInstance.hasErrors()) {
                respond protocolGroupInstance.errors, view:'edit'
                return
            }

            protocolGroupInstance.save flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'ProtocolGroup.label', default: 'ProtocolGroup'), protocolGroupInstance.id])
                    redirect(id: protocolGroupInstance.id, controller: 'ProtocolGroupAdmin', action: 'show')
                }
                '*'{ respond protocolGroupInstance, [status: OK] }
            }
        }
    }

    @Transactional
    def delete(ProtocolGroup protocolGroupInstance) {
        withForm {
            if (protocolGroupInstance == null) {
                notFound()
                return
            }

            protocolGroupInstance.delete flush:true

            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProtocolGroup.label', default: 'ProtocolGroup'), protocolGroupInstance.id])
            redirect(controller: 'ProtocolGroupAdmin', action: 'index')
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'protocolGroup.label', default: 'ProtocolGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
