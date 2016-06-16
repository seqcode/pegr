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
    
    def update(AntibodyCommand cmd) {
        def antibody = Antibody.get(cmd.id)
        if (antibody) {
            antibody.with {
                company = getCompany(cmd.company)
                catalogNumber = utilityService.cleanString(cmd.catalogNumber)
                lotNumber = utilityService.cleanString(cmd.lotNumber)
                clonal = getClonal(cmd.clonal)
                abHost = getAbHost(cmd.abHost)
                igType = getIgType(cmd.igType)
                immunogene = utilityService.cleanString(cmd.immunogene)
                concentration = utilityService.getFloat(cmd.concentration)
            }
            save(antibody)
        }
    }
    
    @Transactional
    def getAntibody(String abCompName, String _catNum, String _lotNum, String abNotes, String abClonal, String host, String ig, String _antigen, String abConc) {
		def company = getCompany(abCompName)
		def abHost = getAbHost(host)
		def clonal = getClonal(abClonal)
		def igType = getIgType(ig)
		def concentration = utilityService.getFloat(abConc)
		def catNum = utilityService.cleanString(_catNum)
        def lotNum = utilityService.cleanString(_lotNum)
        def antigen = utilityService.cleanString(_antigen)

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
    
    
    def getClonal(String _clonalStr) {
	    def clonal = null
        def clonalStr = utilityService.cleanString(_clonalStr)
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
	def getIgType(String _igStr) {
        def igStr = utilityService.cleanString(_igStr)
	    if (igStr == null) {
	        return null
	    }
	    def igType = IgType.findByName(igStr)
	    if (!igType) {
	        igType = new IgType(name: igStr).save( failOnError: true)
	    }
	    return igType
	}
	
    @Transactional
	def getTarget(String _targetStr, String _targetTypeStr, String _nTag, String _cTag) {
        def targetStr = utilityService.cleanString(_targetStr)
        def targetTypeStr = utilityService.cleanString(_targetTypeStr)
        def nTag = utilityService.cleanString(_nTag)
        def cTag = utilityService.cleanString(_cTag)
        
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
	
    
	def getTargetType(String _str) {
        def str = utilityService.cleanString(_str)
        if (!str) {
            return null
        }
	    def type = TargetType.findByName(str)
	    if (!type) {
	        type = new TargetType(name: str).save( failOnError: true)
	    }
	    return type
	}
	
	def getAbHost(String _abHostStr) {
        def abHostStr = utilityService.cleanString(_abHostStr)
	    if (abHostStr == null) {
	        return null
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
	
	def getCompany(String _companyStr) {
        def companyStr = utilityService.cleanString(_companyStr)
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