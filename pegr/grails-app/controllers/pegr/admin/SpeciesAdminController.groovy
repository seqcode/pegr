package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Species

class SpeciesAdminController {

    SpeciesService speciesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Species.hasProperty("name")) {
            def c = Species.createCriteria()
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
            respond items, model:[speciesCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Species.list(params), model:[speciesCount: Species.count()]
        }
    }

    def show(Long id) {
        respond speciesService.get(id)
    }

    def create() {
        respond new Species(params)
    }

    def save(Species species) {
        if (species == null) {
            notFound()
            return
        }

        try {
            speciesService.save(species)
        } catch (ValidationException e) {
            respond species.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'species.label', default: 'Species'), species.id])
                redirect action: "show", id: species.id
            }
            '*' { respond species, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond speciesService.get(id)
    }

    def update(Species species) {
        if (species == null) {
            notFound()
            return
        }

        try {
            speciesService.save(species)
        } catch (ValidationException e) {
            respond species.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'species.label', default: 'Species'), species.id])
                redirect action: "show", id: species.id
            }
            '*'{ respond species, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        speciesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'species.label', default: 'Species'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'species.label', default: 'Species'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
