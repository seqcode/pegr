package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Genome
import groovy.sql.Sql

class GenomeAdminController {

    GenomeService genomeService
    def utilityService
    def dataSource

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
    
    def mergeGenomes(String fromGenomeNamesStr, String toGenomeName) {
        try {
            def fromGenomeNames = fromGenomeNamesStr.split(",").toList()
            
            def toGenome = Genome.findByName(toGenomeName)
            if (!toGenome) {
                throw new UtilityException(message: "Genome ${toGenomeName} does not exist!")
            }
            
            def sql = new Sql(dataSource)
            
            fromGenomeNames.each { it ->
                def fromGenomeName = it.trim()
                def fromGenome = Genome.findByName(fromGenomeName)
                if(!fromGenome) {
                    throw new UtilityException(message: "Genome ${fromGenomeName} does not exist!")
                }

                try {
                    utilityService.updateForeignKeyInDb('chromosome', 'genome', fromGenome.id, toGenome.id, sql)
                    utilityService.updateForeignKeyInDb('sequence_alignment', 'genome', fromGenome.id, toGenome.id, sql)
                    fromGenome.delete()
                } catch(Exception e) {
                    log.error e
                    throw new UtilityException(message: "Error merging genome ${fromGenomeName}!")
                }
            }
            
            flash.message = "Genomes have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'Genome.csv'
        def lines = Genome.findAll().collect { [
            it.id, 
            it.name?'"'+it.name+'"':"", 
            it.species?'"'+it.species+'"':"", 
            it.url?it.url:"", 
            it.status?it.status:"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Species, URL, Status\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}