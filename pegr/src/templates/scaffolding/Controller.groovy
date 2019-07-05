<%=packageName ? "package ${packageName}\n\n" : ''%>

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ${className}Controller {
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (params.merge == "Merge") {
            redirect(action:"mergeSearch", params: [str: str])
        }
        if (str && ${className}.hasProperty("name")) {
            def c = ${className}.createCriteria()
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
            respond items, model:[${propertyName}Count: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ${className}.list(params), model:[${propertyName}Count: ${className}.count()]
        }
    }

    def show(${className} ${propertyName}) {
        respond ${propertyName}
    }

    def create() {
        respond new ${className}(params)
    }

    @Transactional
    def save(${className} ${propertyName}) {
        withForm {
            if (${propertyName} == null) {
                notFound()
                return
            }

            if (${propertyName}.hasErrors()) {
                respond ${propertyName}.errors, view:'create'
                return
            }

            ${propertyName}.save flush:true

            flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
            redirect(controller: '${className}Admin', action: 'index') 
        }
    }

    def edit(${className} ${propertyName}) {
        respond ${propertyName}
    }

    @Transactional
    def update(${className} ${propertyName}) {
        withForm {
            if (${propertyName} == null) {
                notFound()
                return
            }

            if (${propertyName}.hasErrors()) {
                respond ${propertyName}.errors, view:'edit'
                return
            }

            ${propertyName}.save flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: '${className}.label', default: '${className}'), ${propertyName}.id])
                    redirect(id: ${propertyName}.id, controller: '${className}Admin', action: 'show')
                }
                '*'{ respond ${propertyName}, [status: OK] }
            }
        }
    }

    @Transactional
    def delete(${className} ${propertyName}) {
        withForm {
            if (${propertyName} == null) {
                notFound()
                return
            }

            ${propertyName}.delete flush:true

            flash.message = message(code: 'default.deleted.message', args: [message(code: '${className}.label', default: '${className}'), ${propertyName}.id])
            redirect(controller: '${className}Admin', action: 'index')
        }
    }
    
    def cancelMerge() {
        if (session.checked${className}) {
            session.checked${className} = null
        }        
        redirect(action: "index")
    }
    
    def clearChecked${className}Ajax(){
        if (session.checked${className}) {
            session.checked${className} = null
        }
        render true
    }

    def addChecked${className}Ajax(Long id) {
        if (!session.checked${className}) {
            session.checked${className} = []
        }
        if (!(id in session.checked${className})) {
            session.checked${className} << id
        }
        render session.checked${className}.size()
    }

    def removeChecked${className}Ajax(Long id) {
        if (id in session.checked${className}) {
            session.checked${className}.remove(id)
        }
        render session.checked${className}.size()
    }

    def showChecked(){
        def list = []
        if (session.checked${className}) {
            session.checked${className}.each {
                def ${className} = ${className}.get(it)
                if (${className}) {
                    list.push(${className}.get(it))
                }
            }
        }
        if (list.size() == 0) {
            flash.message = "No ${className}s to merge!"
            redirect(action: "index")
        }
        
        [list: list]
    }
    
    
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
