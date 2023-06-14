package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Definition

class DefinitionAdminController {

    DefinitionService definitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Definition.hasProperty("name")) {
            def c = Definition.createCriteria()
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
            respond items, model:[definitionCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Definition.list(params), model:[definitionCount: Definition.count()]
        }
    }

    def show(Long id) {
        respond definitionService.get(id)
    }

    def create() {
        respond new Definition(params)
    }

    def save(Definition definition) {
        if (definition == null) {
            notFound()
            return
        }

        try {
            definitionService.save(definition)
        } catch (ValidationException e) {
            respond definition.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'definition.label', default: 'Definition'), definition.id])
                redirect action: "show", id: definition.id
            }
            '*' { respond definition, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond definitionService.get(id)
    }

    def update(Definition definition) {
        if (definition == null) {
            notFound()
            return
        }

        try {
            definitionService.save(definition)
        } catch (ValidationException e) {
            respond definition.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'definition.label', default: 'Definition'), definition.id])
                redirect action: "show", id: definition.id
            }
            '*'{ respond definition, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        definitionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'definition.label', default: 'Definition'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'Definition.csv'
        def lines = Definition.findAll().collect { [
            it.id, 
            it.name?it.name:"", 
            it.content?it.content:"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Content\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'definition.label', default: 'Definition'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.OTHER
}
