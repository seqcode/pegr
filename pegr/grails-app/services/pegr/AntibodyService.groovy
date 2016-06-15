package pegr
import grails.transaction.Transactional

class AntibodyException extends RuntimeException {
    String message
    Antibody antibody
}

class AntibodyService {
    def itemService
    def utilityService
    
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
    
    @Transactional
    def save(Item item, AntibodyCommand cmd){
        def antibody = getAntibody(cmd.company, cmd.catalogNumber, cmd.lotNumber, null, cmd.clonal, cmd.abHostId, cmd.igTypeId, cmd.immunogene, cmd.concentration)
        save(item, antibody)
        return antibody
    }
    
    def update(Long antibodyId, AntibodyCommand cmd) {
        def antibody = Antibody.get(antibodyId)
        if (antibody) {
            antibody.with {
                company = getCompany(cmd.company)
                catalogNumber = cmd.catalogNumber
                lotNumber = cmd.lotNumber
                clonal = getClonal(cmd.clonal)
                abHost = getAbHost(cmd.abHostId)
                igType = getIgType(cmd.igTypeId)
                immunogene = cmd.immunogene
                concentration = utilityService.getFloat(cmd.concentration)
            }
            save(antibody)
        }
    }
    
    @Transactional
    def getAntibody(String abCompName, String abCatNum, String abLotNum, String abNotes, String abClonal, String abAnimal, String ig, String antigen, String abConc) {
		def company = getCompany(abCompName)
		def abHost = getAbHost(abAnimal)
		def clonal = getClonal(abClonal)
		def igType = getIgType(ig)
		def concentration = utilityService.getFloat(abConc)
		def catNum = abCatNum

	    def antibodies = Antibody.findAllByCompanyAndCatalogNumberAndAbHostAndClonalAndIgTypeAndImmunogeneAndLotNumberAndNote(company, catNum, abHost, clonal, igType, antigen, abLotNum, abNotes)
        
        float err = 0.000001
        def antibody = antibodies.find {
            it.concentration > concentration - err && it.concentration < concentration + err
        }
	    if (!antibody) {	        
	        antibody = new Antibody(company: company, catalogNumber: catNum, abHost: abHost, clonal: clonal, concentration: concentration, igType: igType, immunogene: antigen, lotNumber: abLotNum, note: abNotes).save( failOnError: true)
	    }	        
		return antibody
	}
    
    
    def getClonal(String clonalStr) {
	    def clonal = null
        if (clonalStr) {
            if (clonalStr.toLowerCase().contains("mono")) {
                clonal = MonoPolyClonal.Mono
            } else if (clonalStr.toLowerCase().contains("poly")) {
                clonal = MonoPolyClonal.Poly
            }
        }
	    return clonal
	}
    
    @Transactional
	def getIgType(String igStr) {
	    if (igStr == null) {
	        return null
	    }
        if (igStr.isInteger()) {
            return IgType.get(igStr.toInteger())
        }
	    def igType = IgType.findByName(igStr)
	    if (!igType) {
	        igType = new IgType(name: igStr).save( failOnError: true)
	    }
	    return igType
	}
	
    @Transactional
	def getTarget(String targetStr, String targetTypeStr, String nTag, String cTag) {
	    if ([targetStr, cTag, nTag].every{ it == null }) {
	        return null
	    }
        if(targetStr) {
            targetStr = targetStr.trim()
        }
        
	    def target = Target.findByNameAndCTermTagAndNTermTag(targetStr, cTag, nTag)
	    if (!target) {
	        def type = getTargetType(targetTypeStr)
	        target = new Target(name: targetStr, cTermTag: cTag, nTermTag: nTag, targetType: type).save( failOnError: true)
	    }
	    return target
	}
	
    
	def getTargetType(String str) {
	    if (str == null) {
	        return TargetType.findByName("unknown")
	    }
        if (str.isInteger()) {
            return TargetType.get(str.toInteger())
        }
	    def type = TargetType.findByName(str)
	    if (!type) {
	        type = new TargetType(name: str).save( failOnError: true)
	    }
	    return type
	}
	
	def getAbHost(String abHostStr) {
	    if (abHostStr == null) {
	        return null
	    }
        if (abHostStr.isInteger()) {
            return AbHost.get(abHostStr.toInteger())
        }
	    def abHost = AbHost.createCriteria().get{
	        eq("name", abHostStr, [ignoreCase: true])
	        maxResults(1)
	    }
	    if(!abHost) {
	        abHost = new AbHost(name: abHostStr).save( failOnError: true)
	    }
	    return abHost
	}
	
	def getCompany(String companyStr) {
	    if (companyStr == null) {
	        return null
	    }    
	    def company = Company.findByName(companyStr)
	    if(!company) {
	        company = new Company(name: companyStr).save( failOnError: true)
	    }
	    return company
	}
}