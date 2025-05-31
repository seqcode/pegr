package pegr
import pegr.AdminCategory
import pegr.Strain
import pegr.StrainException

class StrainAdminController {

	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
    
    def strainService
    def utilityService
    
    def index(Integer max, String str) {
        if (str) {
            def c = Strain.createCriteria()
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
                    species {
                        or {
                            ilike "name", likeStr
                            ilike "genusName", likeStr
                        }                        
                    }                  
                    parent {
                        ilike "name", likeStr
                    }                    
                    ilike "genotype", likeStr
                    ilike "geneticModification", likeStr
                }
            }
            respond items, model:[strainCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Strain.list(params), model:[strainCount: Strain.count()]
        }
    }
    
    def show(Long id) {
        def strain = Strain.get(id)
        
        [strain: strain]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def strain = new Strain(params)
                try {
                    strainService.save(strain)
                    flash.message = "New strain saved!"
                    redirect(action: "show", id: strain.id)
                } catch(StrainException e) {
                    request.message = e.message
                    render(view: "create", model: [strain: strain])
                }catch(Exception e) {
                    request.message = "Error saving this strain!"
                    log.error "Error: ${e.message}", e
                    render(view: "create", model: [strain: strain])
                }
            }
        }
    }
    
    def edit() {
        def strain = Strain.get(params.long('id'))
        if (!strain) {
            flash.message = "Strain does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                strain.properties = params
                try {
                    strainService.save(strain)
                    flash.message = "Strain update!"
                    redirect(action: "show", id: strain.id)
                }catch(StrainException e) {
                    request.message = e.message
                    render(view: "edit", model: [strain: strain])
                }catch(Exception e) {
                    request.message = e.message
                    log.error "Error: ${e.message}", e
                    render(view: "edit", model: [strain: strain])
                }
            }
        }else {
            [strain: strain]
        }
    }
    
    
    def delete(Long id) {
        try {
            strainService.delete(id)
            flash.message = "Strain deleted!"
            redirect(action: "index")
        }catch(Exception e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        }
    }
    
    def mergeStrains(String fromStrainIdsStr, String toStrainId) {
        try {
            def fromStrainIds = fromStrainIdsStr.split(",").toList()
            
            def toStrain = Strain.get(toStrainId)
            if (!toStrain) {
                throw new UtilityException(message: "Strain ${toStrainId} does not exist!")
            }
            
            fromStrainIds.each { it ->
                def fromStrainId = it.trim()
                def fromStrain = Strain.get(fromStrainId)
                if(!fromStrain) {
                    throw new UtilityException(message: "Strain ${fromStrainId} does not exist!")
                }

                utilityService.mergeRowsInDb('strain', fromStrain.id, toStrain.id)
            }
            
            flash.message = "Strains have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'Strain.csv'
        def lines = Strain.findAll().collect { [
            it.id, 
            it.name?it.name:"",
            it.species?it.species:"",
            it.parent?it.parent:"",
            it.genotype?it.genotype:"",
            it.geneticModification?it.geneticModification:"",
            it.sourceLab?it.sourceLab:"",
            it.status?it.status:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Species, Parent, Genotype, Genetic Modification, Source Lab, Status\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    
}
