package pegr
import grails.transaction.Transactional

class AntibodyException extends RuntimeException {
    String message
    Antibody antibody
}

class AntibodyService {
    def itemService
    
    @Transactional
    def delete(Long id) {
        def antibody = Antibody.get(id)
        if(!antibody) {
            throw new AntibodyException(message: "Antibody not found!")
        }
        def sample = Sample.findByAntibody(antibody)
       
        if (sample) {
            throw new AntibodyException(message: "This traced sample cannot be deleted because it has been used!")
        }

        try {
            def item = antibody.item
            antibody.delete(flush: true)
            item.delete(flush: true)
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new AntibodyException(message: "Antibody cannot be deleted!")
        }
    }
    
    @Transactional
    def save(Antibody antibody) {        
        if (!antibody.save(flush: true)) {
            throw new AntibodyException(message: "Invalid inputs!")
        } 
    }
    
    @Transactional
    def save(Item item, Antibody antibody){
        itemService.save(item)            
        if(antibody){
            antibody.item = item 
            if (!antibody.save(flush: true)) {
                throw new ItemException(message: "Invalid inputs for antibody!")
            }
        }
    }
}