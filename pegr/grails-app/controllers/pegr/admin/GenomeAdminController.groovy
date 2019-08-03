package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Genome

class GenomeAdminController {

    GenomeService genomeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Genome.hasProperty("name")) {
            def c = Genome.createCriteria()
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
            respond items, model:[genomeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Genome.list(params), model:[genomeCount: Genome.count()]
        }
    }

    def show(Long id) {
        respond genomeService.get(id)
    }

    def create() {
        respond new Genome(params)
    }

    def save(Genome genome) {
        if (genome == null) {
            notFound()
            return
        }

        try {
            genomeService.save(genome)
        } catch (ValidationException e) {
            respond genome.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'genome.label', default: 'Genome'), genome.id])
                redirect action: "show", id: genome.id
            }
            '*' { respond genome, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond genomeService.get(id)
    }

    def update(Genome genome) {
        if (genome == null) {
            notFound()
            return
        }

        try {
            genomeService.save(genome)
        } catch (ValidationException e) {
            respond genome.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'genome.label', default: 'Genome'), genome.id])
                redirect action: "show", id: genome.id
            }
            '*'{ respond genome, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        genomeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'genome.label', default: 'Genome'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}