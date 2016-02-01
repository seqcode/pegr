package pegr.admin
import pegr.AdminCategory
import pegr.Strain
import pegr.Genotype
import pegr.StrainException

class StrainAdminController {

	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
    
    def strainService
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [strainList: Strain.list(params), strainCount: Strain.count()]
    }
    
    def show(Long id) {
        def strain = Strain.get(id)
        [strain: strain, geneticModifications:strain.geneticModifications]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def strain = new Strain(params)
                def geneticModificationIds = params.list('geneticModifications')
                try {
                    strainService.save(strain, geneticModificationIds)
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
        def strain = Strain.get(params.id)
        if (!strain) {
            flash.message = "Strain does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                strain.properties = params
                try {
                    strainService.save(strain, params.list('geneticModifications'))
                    flash.message = "Strain update!"
                    redirect(action: "show", id: strain.id)
                }catch(Exception e) {
                    request.message = e.message
                    render(view: "edit", model: [strain: strain, geneticModifications: params.list('geneticModifications')])
                }
            }
        }else {
            def geneticModifications = strain.geneticModifications
            [strain: strain, geneticModifications:geneticModifications]
        }
    }
    
    
    def delete(Long id) {
        try {
            strainService.delete(id)
            flash.message = "Strain deleted!"
            redirect(action: "index")
        }catch(Exception e) {
            request.message = e.message
            redirect(action: "show", id: id)
        }
    }
    
    def speciesChangedAjax(Long speciesId) {
        def genotypes = Genotype.where{
                species.id == speciesId
            }.list()
        render g.select(id: 'genotype', name:'genotype.id', from: genotypes, optionKey: 'id', noSelection:[null:''])
    }
}
