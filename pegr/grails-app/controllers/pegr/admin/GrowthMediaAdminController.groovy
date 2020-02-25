package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.GrowthMedia
import pegr.AdminCategory


class GrowthMediaAdminController {

    GrowthMediaService growthMediaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && GrowthMedia.hasProperty("name")) {
            def c = GrowthMedia.createCriteria()
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
            respond items, model:[growthMediaCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond GrowthMedia.list(params), model:[growthMediaCount: GrowthMedia.count()]
        }
    }

    def show(Long id) {
        respond growthMediaService.get(id)
    }

    def create() {
        respond new GrowthMedia(params)
    }

    def save(GrowthMedia growthMedia) {
        if (growthMedia == null) {
            notFound()
            return
        }

        try {
            growthMediaService.save(growthMedia)
        } catch (ValidationException e) {
            respond growthMedia.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'growthMedia.label', default: 'GrowthMedia'), growthMedia.id])
                redirect action: "show", id: growthMedia.id
            }
            '*' { respond growthMedia, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond growthMediaService.get(id)
    }

    def update(GrowthMedia growthMedia) {
        if (growthMedia == null) {
            notFound()
            return
        }

        try {
            growthMediaService.save(growthMedia)
        } catch (ValidationException e) {
            respond growthMedia.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'growthMedia.label', default: 'GrowthMedia'), growthMedia.id])
                redirect action: "show", id: growthMedia.id
            }
            '*'{ respond growthMedia, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        growthMediaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'growthMedia.label', default: 'GrowthMedia'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'growthMedia.label', default: 'GrowthMedia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
