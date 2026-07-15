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
        def sortField = params.sort ?: "id"
        def sortOrder = params.order ?: "desc"
        if (str && Genome.hasProperty("name")) {
            def likeStr = "%" + str + "%"
            def c = Genome.createCriteria()
            def items = c.list(max: max ?: 25, offset: params.offset) {
                or {
                    ilike "name", likeStr
                }
                if (sortField == "species") {
                    // sort by the species' displayed name, keeping genomes with no species
                    createAlias("species", "sp", org.hibernate.criterion.CriteriaSpecification.LEFT_JOIN)
                    order("sp.genusName", sortOrder)
                    order("sp.name", sortOrder)
                } else {
                    order(sortField, sortOrder)
                }
            }
            respond items, model:[genomeCount: items.totalCount, str: str]
        } else {
            def queryParams = new LinkedHashMap(params)
            queryParams.max = Math.min(max ?: 25, 100)
            if (queryParams.sort == "species") {
                // the species association sorts by FK id by default; sort by name instead
                queryParams.sort = "species.genusName"
            }
            respond Genome.list(queryParams), model:[genomeCount: Genome.count()]
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
                    sql.execute("delete from reference_feature where genome_id =:genomeId", [genomeId: fromGenome.id])
                    fromGenome.delete()
                    
                    def samples =  Sample.findAllByRequestedGenomesLike("%${fromGenomeName}%")
                    
                    samples.each { sample ->
                        def flag = false
                        def newGenomes = []
                        def genomes = sample.requestedGenomes.split(",").toList()
                        genomes.each { genome ->
                            if (genome == fromGenomeName) {
                                flag = true
                                newGenomes.push(toGenomeName)
                            } else {
                                newGenomes.push(genome)
                            }
                        }
                        if (flag) {
                            sample.requestedGenomes = newGenomes.join(',')
                            sample.save()
                        }
                    }                    
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