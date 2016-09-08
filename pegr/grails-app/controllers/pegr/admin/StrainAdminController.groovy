package pegr.admin
import pegr.AdminCategory
import pegr.Strain
import pegr.StrainException

class StrainAdminController {

	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
    
    def strainService
    
    def index(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        [strainList: Strain.list(params), strainCount: Strain.count()]
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
    
}
