package pegr
import grails.transaction.Transactional
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*
import groovy.time.*

class CsvConvertException extends RuntimeException {
    String message
}
    
class CsvConvertService {
    
    CsvConvertService() {}
	
	def migrate(String filename, RunStatus runStatus, int startLine, int endLine, boolean basicCheck){
		
        def timeStart = new Date()
		def lineNo = 0    
        def messages = []
        
        def file = new FileReader(filename)
		CSVReader reader = new CSVReader(file);
		String [] rawdata;
		
		getDefaultUser()
		
		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (endLine > 0 && lineNo > endLine) {
		        break
		    }
            try {
                migrateOneRow(rawdata, runStatus, basicCheck)
		 	}catch(Exception e) {
		        log.error "Error: line ${lineNo}. " + e
                messages.push("Error: Line ${lineNo} ${e.message}")
		        continue
		    }   
		}
        
        def timeStop = new Date()
        TimeDuration duration = TimeCategory.minus(timeStop, timeStart)
        println "Time: " + duration
        return messages
	}
	
    void migrateOneRow(String[] rawdata, RunStatus runStatus, boolean basicCheck) {

        String[] cells = new String[rawdata.size()]
        rawdata.eachWithIndex{ d, idx -> 
            def td = d.trim()
            if(td == "" || td == "-" || td == "." || td == "?" || td == "Not applicable" || td == "not applicable" || td == "None") {
                cells[idx] = null
            }else {
                cells[idx] = td
            }
         }
        def data = getNamedData(cells)
        
        // stop if basiceCheck is true and this row does not have a run number
        if (basicCheck && (!data.runStr || data.runStr == "Run #")) {
            throw new CsvConvertException(message: "Run number is missing!")
        }
        
        def runUser = getUser(data.userStr)

        def results = getSequenceRun(runStatus, data.runStr, data.laneStr, data.dateStr, data.fcidStr, data.indexIdStr, runUser)

        def cellProvider = getUser(data.senderNameStr, data.senderEmail, data.senderPhone)
        def dataTo = getUser(data.dataToUser, data.dataToEmail, null)

        def project = getProject(data.projectName, data.projectUser, data.projectUserEmail)

        def invoice = getInvoice(data.service, data.invoice)

        def target = getTarget(data.target, data.targetType, data.nTag, data.cTag)

        def antibody = getAntibody(data.abCompName, data.abCatNum, data.abLotNum, data.abNotes, data.abClonal, data.abAnimal, data.ig, data.antigen, data.ulSampleSent, data.abConc, data.amountUseUg, data.amountUseUl)

        def species = getSpecies(data.genus, data.species)

        def strainAndTissue = getStrainTissue(species, data.strain, data.parentStrain, data.genotype, data.mutation)
        def strain = strainAndTissue.strain
        def tissue = strainAndTissue.tissue

        def growthMedia = getGrowthMedia(data.growthMedia, species)

        def inventory = getInventory(data.dateReceived, data.receivingUser, data.inOrExternal, data.inventoryNotes)
        def cellSource = getCellSource(data.samplePrepUser, growthMedia, strain, cellProvider, inventory, tissue)

        addTreatment(cellSource, data.perturbation1)
        addTreatment(cellSource, data.perturbation2)

        def assay = getAssay(data.assay)

        def prtcl = getProtocolInstanceSummary(data.chipUser, data.chipDate, data.protocolVersion, data.resin, data.PCRCycle, assay)

        def sample = getSample(cellSource, antibody, target, data.cellNum, data.chromAmount, data.volume, data.requestedTagNum, data.sampleNotes, data.sampleId, data.bioRep1SampleId, data.bioRep2SampleId, invoice, dataTo, data.dateStr, prtcl)

        addIndexToSample(sample, data.indexStr, data.indexIdStr, basicCheck)
        addSampleToProject(project, sample)

        getPool(sample, results.sequenceRun, data.pool, data.volToPool, data.poolDate, data.quibitReading, data.quibitDilution, data.concentration, data.poolDilution)

        def seqExp = getSeqExperiment(sample, results.sequenceRun, results.seqId, data.rd1Start, data.rd1End, data.indexStart, data.indexEnd, data.rd2Start, data.rd2End)

        [data.genomeBuild1, data.genomeBuild2, data.genomeBuild3].each{ getSeqAlign(it, seqExp, species) }

    }
    
    def addIndexToSample(Sample sample, String indexStr, String indexIdStr, boolean basicCheck) { 
        if (indexStr == null || indexStr == "unk"){
            return
        }
        def indexId = getInteger(indexIdStr)
        def index
        if (basicCheck) {
            index = SequenceIndex.findByIndexIdAndSequenceAndStatus(indexId, indexStr, DictionaryStatus.Y)
            if (!index) {
                throw new CsvConvertException(message: "Index ID and String don't match!")
            }
        } else {
            index = SequenceIndex.findByIndexIdAndSequence(indexId, indexStr)
            if (!index) {
                index = new SequenceIndex(indexId: indexId, sequence: indexStr, indexVersion: "UNKNOWN").save(failOnError: true)
            }
        }
        if (index && sample) {
            new SampleSequenceIndices(sample: sample, index: index).save(failOnError: true)
        } 
    }
    
	def addSampleToProject(Project project, Sample sample) {
	    if(project && sample) {
	        new ProjectSamples(project: project, sample: sample).save( failOnError: true)
	    }
	}
	
    def getUser(String userStr) {
        return getUser(userStr, null, null)
    }
    
	def getUser(String userStrRaw, String emailStr, String phoneStr) {
	    if(userStrRaw == null) {
	        return null
	    }    
		if (phoneStr) {
			phoneStr =  phoneStr.replaceAll("\\p{P}", "");
            phoneStr =  phoneStr.replaceAll(/[ +]/, "");
		}
		def user = null
		def fullname = null
        def username = null
		
		// try to get username from email
		if (emailStr && emailStr != 'bfp2@psu.edu') {
			def at = emailStr.indexOf('@')
			if (at > 0) {
				username = emailStr[0..<at]
			}
		}
		// try to get fullname
		if(userStrRaw.contains(",")) {
			fullname = userStrRaw.split(",")*.trim()*.toLowerCase()*.capitalize().join(', ')
        }else if(userStrRaw.contains(" ")) {
            def s = userStrRaw.split()*.toLowerCase()*.capitalize()
            fullname = [s[-1], s[0]].join(', ')
        }else {        
			if (!username) {
				username = userStrRaw
			}
		}
		user = User.findByUsername(username)
		if (!user && fullname && fullname != "") {
			user = User.findByFullName(fullname)
		}
	    if (!user) {	        
			// try to get username from fullname
	        if (username == null) {
	            def names = fullname.split(",|\\.")*.trim()
	            username = "" 
	            names.each{username += it[0].toLowerCase()}
	            int i = 100
	            while(User.findByUsername(username + i)) {
	                ++i
	            }
	            username += i
	        }
	        
	        def admin = User.get(1)	        
	        def password = admin.password

	        user = new User(username: username, password: password, fullName: fullname, email: emailStr, phone: phoneStr).save( failOnError: true)
        } else {
			def flag = 0
            if (user.email == null && emailStr && emailStr != 'bfp2@psu.edu') {
                user.email = emailStr
				flag = 1
				
            }
			if (user.fullName == null && fullname) {
				user.fullName = fullname
				flag = 1
			}
			if (user.phone == null && phoneStr) {
				user.phone = phoneStr
				flag = 1
			}
			if (username && user.username != username ) {
				user.username = username
				flag = 1
			}
			if (flag == 1) {
				user.save(failOnError: true)	
			}
        }
	    return user
	}
	
	def getProject(String projectName, String projectUser, String projectUserEmail) {
	    if (projectName == null) {
	        return null
	    }
	    def project = Project.findByName(projectName)
	    if (!project) {
	        project = new Project(name: projectName).save( failOnError: true)
	    }
	    def user = getUser(projectUser, projectUserEmail, null)
	    if (user && project) {
            if ( !ProjectUser.findByProjectAndUser(project, user)) {
                new ProjectUser(project: project, user: user, projectRole: ProjectRole.OWNER).save( failOnError: true)
            }
	    }
	    
	    return project
	}
	
	def getInvoice(String service, String invoiceNum) {
	    if (service == null && invoiceNum == null) {
	        return null
	    }
		
		def invoice = null
		if (service == null) {
			invoice = Invoice.where{
				serviceId == null && invoiceNum == invoiceNum
			}.get(max:1)
		} else if(invoiceNum == null) {
			invoice = Invoice.where{
				serviceId == service && invoiceNum == null
			}.get(max:1)
		} else {
			invoice = Invoice.where{
				serviceId == service && invoiceNum == invoiceNum
			}.get(max:1)
		}
	    if (!invoice) {
	        def date = getDate(invoiceNum)
	        invoice = new Invoice(serviceId: service, invoiceNum: invoiceNum, date: date).save( failOnError: true)
	    } 
	    return invoice
	}
	
	def getAntibody(String abCompName, String abCatNum, String abLotNum, String abNotes, String abClonal, String abAnimal, String ig, String antigen, String ulSent, String abConc, String ugPerChIP, String ulPerChIP) {
		def company = getCompany(abCompName)
		def abHost = getAbHost(abAnimal)
		def clonal = getClonal(abClonal)
		def igType = getIgType(ig)
		def concentration = getFloat(abConc)
        
        if (antigen == "No ab" || antigen == "NoAb") {
            antigen = null
        }
		
		def catNum = abCatNum
		if (abCatNum && abCatNum.contains(".")) {
			try { 
				catNum = new BigDecimal(abCatNum).toPlainString()
			} catch(Exception e) {
			}
		} 
        if(catNum) {
            catNum = catNum.replaceAll("_", "-")
            catNum = catNum.replaceAll("[^0-9A-Za-z -]", "")
        }
        
        def noteMap = [:]
        if (abNotes) {
            noteMap['Note'] = abNotes
        }
        if (ulSent) {
            noteMap['Volume Sent (ul)'] = ulSent
        }
        if (ugPerChIP) {
            noteMap['Usage Per ChIP (ug)'] = ugPerChIP
        }
        if (ulPerChIP) {
            noteMap['Usage Per ChIP (ul)'] = ulPerChIP
        }

        def notes = JsonOutput.toJson(noteMap)
		
	    def antibodies = Antibody.findAllByCompanyAndCatalogNumberAndAbHostAndClonalAndIgTypeAndImmunogeneAndLotNumberAndNote(company, catNum, abHost, clonal, igType, antigen, abLotNum, notes)
        
        float err = 0.000001
        def antibody = antibodies.find {
            it.concentration > concentration - err && it.concentration < concentration + err
        }
	    if (!antibody) {	        
	        antibody = new Antibody(company: company, catalogNumber: catNum, abHost: abHost, clonal: clonal, concentration: concentration, igType: igType, immunogene: antigen, lotNumber: abLotNum, note: notes).save( failOnError: true)
	    }	        
		return antibody
	}
    
    def getTissue(String name) {
        def tissue = null
        if (name) {
            if (name == "InVitro" || name == "Liver" || name.contains("tissue") || name.contains("cells") || name.contains("leukemia")) {
                tissue = Tissue.findByName(name)
                if (!tissue) {
                    tissue = new Tissue(name: name).save( failOnError: true)
                }
            } 
        }
        return tissue
    }
	
	def getStrainTissue(Species species, String strainStr, String parentStrainStr, String genotypeStr, String mutationStr) {     
        // get the parent strain and tissue
        def parentStrain = null
        def parentTissue = getTissue(parentStrainStr)
        if (parentStrainStr && !parentTissue) {
            parentStrain = Strain.findByName(parentStrainStr)
            if (!parentStrain) {
                parentStrain = new Strain(name: parentStrainStr, species: species).save(failOnError: true) 
            }
        }
        
        // get strain and tissue
        def strain = null
        def tissue = getTissue(strainStr)
        if (!tissue) {
            tissue = parentTissue
			if (strainStr || parentStrain || genotypeStr || mutationStr) {
	            strain = Strain.findByNameAndParentAndGenotypeAndGeneticModification(strainStr, parentStrain, genotypeStr, mutationStr)
	            if (!strain) {
	                strain = new Strain(name: strainStr, 
	                                    species: species, 
	                                    genotype: genotypeStr, 
	                                    parent: parentStrain, 
	                                    geneticModification: mutationStr).save( failOnError: true)
	            }
			}
        }
        
        return [strain: strain, tissue: tissue]	    
	}
	
	def getSpecies(String genusStr, String speciesStr) {
	    if(genusStr == null && speciesStr == null) {
	        return null
	    }
		if (genusStr == null) {
			genusStr = "Unknown"
		}
		if (speciesStr == null) {
			speciesStr = "Unknown"
		}
	    def species = Species.findByNameAndGenusName(speciesStr, genusStr)
	    if(!species) {
	        species = new Species(name: speciesStr, genusName: genusStr).save( failOnError: true)
	    }
	    return species
	}
	
	def getGrowthMedia(String mediaStr, Species species) {
	    if(mediaStr == null) {
	        return null
	    }
	    def media = GrowthMedia.findByName(mediaStr)
	    if(!media) {
	        media = new GrowthMedia(name: mediaStr, species: species).save( failOnError: true)
	    } else {
	        if(media.species != species) {
	            media.species = null
				media.save( failOnError: true)
	        }
	    }
	    return media
	}
	
	
	def getCellSource( String samplePrepUser, GrowthMedia growthMedia, Strain strain, User provider, Inventory inventory, Tissue tissue) {
	    if (!strain) {
	        return null
	    }
		def prepUser = getUser(samplePrepUser)
	    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, prepUser: prepUser, inventory: inventory, tissue: tissue).save( failOnError: true)
	    return cellSource
	}
	
	def addTreatment(CellSource cellSource, String perturbStr) {
	    if(perturbStr == null) {
	        return null
	    }
	    def perturbation = CellSourceTreatment.findByName(perturbStr)
	    if(!perturbation) {
	        perturbation = new CellSourceTreatment(name: perturbStr).save( failOnError: true)
	    }
	    if(perturbation && cellSource) {
            if (!CellSourceTreatments.findByCellSourceAndTreatment(cellSource, perturbation)) {
                new CellSourceTreatments(cellSource: cellSource, treatment: perturbation).save( failOnError: true)
            }			
		} 
	}
    
    def getProtocolInstanceSummary(String chipUser, String chipDate, String v, String resin, String PCRCycle, Assay assay){
        def protocol = null
        // clean protocol version 
        if (v) {
            def first = v.indexOf(".")
            if(first == -1) {
                v = v + ".0"
            } else if (first == 0) {
                v = "0" + v
            } 
            first = v.indexOf(".")
            def v2 = v.substring(first+1)
            def second = v2.indexOf(".")
            if (second == -1) {
                v = v + ".0"
            }else if (second == v2.length() - 1) {
                v = v + "0"
            }
            if (v.length() > 10) {
                v = v[0..9]
            }
        }
        if (assay) {
	        protocol = Protocol.findByNameAndProtocolVersion(assay.name, v)  
	        if (!protocol) {
	            protocol = new Protocol(name: assay.name, protocolVersion: v).save( failOnError: true)
	        }
	    }
	    // get note
	    def note = [:]
	    if (resin) {
	        note['Resin'] = resin
	    }
	    if (PCRCycle) {
	        note['PCR Cycle'] = PCRCycle
	    }
	    
	    def date = getDate(chipDate)
	    def prtcl = new ProtocolInstanceSummary(protocol: protocol, user: getUser(chipUser), startTime: date, endTime: date, note: JsonOutput.toJson(note)).save( failOnError: true)
        return prtcl
    }
	
	def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, String sampleId, String bioRep1SampleId, String bioRep2SampleId, Invoice invoice, User dataTo, String dateStr, ProtocolInstanceSummary prtcl) {
	    def note = [:]
	    if (sampleNotes) {
	        note['note'] = sampleNotes
	    }
        if (sampleId) {
            note['sampleId'] = sampleId
        }
	    if (bioRep1SampleId) {
	        note['bioRep1'] = bioRep1SampleId
	    }
	    if (bioRep2SampleId) {
	        note['bioRep2'] = bioRep2SampleId
	    }
	    def date = getDate(dateStr)
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: getFloat(requestedTagNum), chromosomeAmount: getFloat(chromAmount), cellNumber: getFloat(cellNum), volume: getFloat(volume), note: JsonOutput.toJson(note), status: SampleStatus.COMPLETED, date: date, sendDataTo: dataTo, invoice: invoice, prtclInstSummary: prtcl).save( failOnError: true)
	    return sample
	}
	
    def getAllBioReplicate() {		
        Sample.list().each {
            getBioReplicate(it)
        }
    }
    
	def getBioReplicate(Sample sample) {    
	    if(sample) {
	        def jsonSlurper = new JsonSlurper()
			try {
				def note = jsonSlurper.parseText(sample.note)
	
		        def sample2 = getSampleFromSampleId(note.bioRep2)
		        def sample1 = getSampleFromSampleId(note.bioRep1)
		        if (sample2 || sample1) {
		            def set = BiologicalReplicateSamples.findBySample(sample)?.set  
		            if (!set) {
		                set = new BiologicalReplicateSet().save( failOnError: true)
		                new BiologicalReplicateSamples(set: set, sample: sample).save( failOnError: true)
		            }
		            if (set && sample2) {
		                new BiologicalReplicateSamples(set: set, sample: sample2).save()
		            }
		            if (set && sample1) {
		                new BiologicalReplicateSamples(set: set, sample: sample1).save()
		            }
		        }
		        sample.note = note.note
		        sample.save( failOnError: true)
			}catch(Exception e) {
				return
		    }
	    }
	}
	
	def getSampleFromSampleId(String sampleId) {
	    if (sampleId == null) {
	        return null
	    }
		def s = '%sampleId":"' + sampleId + "%"
	    def samples = Sample.findAllByNoteIlike(s)
	    if (samples.size() > 1){
	        return null
	    } else {
	        return samples[0]
	    }
	    
	}
	
	def getInventory(String dateReceived, String receivingUser, String inOrExternal, String inventoryNotes) {
   
        def source = null
        if (inOrExternal == "I") {
            source = SourceType.INTERNAL
        }else if (inOrExternal == "E") {
            source = SourceType.EXTERNAL
        }
        
	    def date = getDate(dateReceived)
	    def user = getUser(receivingUser)
	    
	    def inventory = new Inventory(dateReceived: date, receivingUser:user, sourceType: source, notes: inventoryNotes).save( failOnError: true)
	    return inventory
	}
	             
	def getAssay(String assayStr) {
	    if(assayStr == null) {
	        return null
	    }
	    
	    def assay= Assay.findByName(assayStr)
	    if(!assay) {
	        assay = new Assay(name: assayStr).save( failOnError: true)
	    }
	    return assay
	}
	             
	def getPool(Sample sample, SequenceRun run, String pool, String volToPool, String poolDateStr, String quibitReading, String quibitDilution, String concentration, String poolDilution) {
	    if (sample && run) {
	        def poolDate = getDate(poolDateStr)
	        
	        def map = [:]
	        if (concentration){
	            map['concentration'] = concentration
	        }
	        if (quibitReading) {
	            map['quibitReading'] = quibitReading
	        }
	        if (quibitDilution) {
	            map['quibitDilution'] = quibitDilution
	        }
	        if (concentration) {
	            map['concentration'] = concentration
	        }
	        
	        def params = JsonOutput.toJson(map)
	        def vol = getFloat(volToPool)
	        new SampleInRun(poolDate: poolDate, sample: sample, run: run, pool: pool, params: params, volumeToPool: vol).save( failOnError: true)
	    }
	}
	        
	def getSeqExperiment(Sample sample, SequenceRun seqRun, String seqId, String rd1Start, String rd1End, String indexStart, String indexEnd, String rd2Start, String rd2End) {
		def map = [:]
		if(rd1Start || rd1End) {
			map['rd1'] = [rd1Start, rd1End]
		}
		if(indexStart || indexEnd) {
			map['index'] = [indexStart, indexEnd]
		}
		if(rd2Start || rd2End) {
			map['rd2'] = [rd2Start, rd2End]
		}
	    def readPositions = JsonOutput.toJson(map)
	    def seqExp = new SequencingExperiment(seqId: seqId, sample: sample, sequenceRun: seqRun, readPositions: readPositions).save( failOnError: true)
	    return seqExp
	}
	        
	def getSeqAlign(String genomeBuild, SequencingExperiment seqExp, Species species) {
	    def genome = getGenome(genomeBuild, species)
	    if (seqExp && genome) {
	        new SequenceAlignment(sequencingExperiment: seqExp, genome: genome).save( failOnError: true)
	    }
	}
	
	def getGenome(String genomeStr, Species species) {
	    if (genomeStr == null) {
	        return null
	    }
	    def genome = Genome.findByName(genomeStr)
	    if (!genome) {
	        genome = new Genome(name: genomeStr, species: species).save( failOnError: true)
	    }
	    return genome
	}
	
	def getFloat(String s) {
	    def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
	        f = 0
	    }
	    return f
	}
	
	def getClonal(String clonalStr) {
	    def clonal = null
        if (clonalStr) {
            if (clonalStr.contains("mono")) {
                clonal = MonoPolyClonal.Mono
            } else if (clonalStr.contains("poly")) {
                clonal = MonoPolyClonal.Poly
            }
        }
	    return clonal
	}
	
	def getIgType(String igStr) {
	    if (igStr == null) {
	        return null
	    }
	    def igType = IgType.findByName(igStr)
	    if (!igType) {
	        igType = new IgType(name: igStr).save( failOnError: true)
	    }
	    return igType
	}
	
	def getTarget(String targetStr, String targetTypeStr, String nTag, String cTag) {
	    if ([targetStr, cTag, nTag].every{ it == null }) {
	        return null
	    }
        if(targetStr) {
            targetStr = targetStr.replaceAll("_", "-")
            targetStr = targetStr.replaceAll("\\?", "")
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
	        return null
	    }
	    def type = TargetType.findByName(str)
	    if (!type) {
	        type = new TargetType(name: str).save( failOnError: true)
	    }
	    return type
	}
	
	def getAbHost(String abHostName) {
	    if (abHostName == null) {
	        return null
	    }
	    def abHost = AbHost.createCriteria().get{
	        eq("name", abHostName, [ignoreCase: true])
	        maxResults(1)
	    }
	    if(!abHost) {
	        abHost = new AbHost(name: abHostName).save( failOnError: true)
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
    
    def getInteger(String s) {
        def i = 0
        try {
            i = s.toInteger()
        }catch(Exception e) {
            
        }
        return i
    }
	
	def getSequenceRun(RunStatus runStatus, String runStr, String laneStr, String dateStr, String fcidStr, String indexIdStr, User user) {
	    int runNum
	    def platform
	    def seqId
        if (runStr == null) {
            runStr = "00"
        } else if (runStr.length() == 1) {
            runStr = "0" + runStr
        }
        if (laneStr == null || laneStr == "unk") {
            laneStr = "0"
        }
        
        if (indexIdStr == null){
            indexIdStr = "00" 
        } else if (indexIdStr.length() == 1) {
            indexIdStr = "0" + indexIdStr
        }
        
	    if (runStr.take(1) == "S") {
	         runNum = getInteger(runStr.substring(1))
	         platform = SequencingPlatform.findByName("SOLiD")
	         seqId = runStr + laneStr + indexIdStr
	    } else if (runStr.take(1) == "G") {
	         runNum = getInteger(runStr.substring(1))
	         platform = SequencingPlatform.findByName("Illumina GA")
	        seqId = runStr + laneStr + indexIdStr
	    } else {
	         runNum = getInteger(runStr) 
	         if (runNum < 500){
	             platform = SequencingPlatform.findByName("HiSeq 2000")
	             seqId = runStr + laneStr + indexIdStr
	         } else {
	             platform = SequencingPlatform.findByName("NextSeq 500")
	             seqId = runStr + indexIdStr
	         }
	    }
	
	    if (SequencingExperiment.findBySeqId(seqId)) {
            throw new CsvConvertException(message: "SeqId ${seqId} already exists!")
	    }
	
	    def run = SequenceRun.findByPlatformAndRunNum(platform, runNum)
	    if (!run) {                     
	         run = new SequenceRun(runNum: runNum, platform: platform, status: runStatus, user: user)
	
	         // lane
	         run.lane = getInteger(laneStr)
	
	         // date
	         run.date = getDate(dateStr)
	
	         // fcId
	         run.fcId = fcidStr
	
	         run = run.save( failOnError: true)
	    }
	    
	    [sequenceRun: run, seqId: seqId]
	
	}
	
	def getDate(String dateStr) {
	    def date = null
        if (dateStr) {
            if (dateStr.size() == 5) {
                 dateStr = "0" + dateStr
             }         
            if (dateStr.size() == 6) {
                try {
                    date = Date.parse("yyMMdd", dateStr)
                }catch(Exception) {
                    date = null
                }
            }
            if (dateStr.size() == 10) {
                try {
                    date = Date.parse("yyyy.MM.dd", dateStr)
                }catch(Exception) {
                    date = null
                }
            }
        }
	    return date
	}
    
    def getDefaultUser() {
		def admin = User.get(1)
		def password = admin.password
		
        def user = User.findByUsername("bfp2")
        if (!user) {    
            new User(username: "bfp2", email: "bfp2@psu.edu", fullName: "Pugh, Frank", password: password).save( failOnError:true) 
        }
		
		if (!User.findByUsername("npf5017")) {
			new User(username: "npf5017", email: "npf5017@psu.edu", fullName: "Farrell, Nina", password: password, phone: "8148638594").save( failOnError:true) 
		}
		
    }
	
	def getNamedData(String[] data) {
	    [laneStr: data[0],         //A       
	     // data[1]                //B
	     // data[2]                //C
         indexIdStr: data[3],      //D
	     senderNameStr: data[4],   //E
	     senderEmail: data[5],     //F
	     senderPhone: data[6],     //G
	     dataToUser: data[7],      //H
	     dataToEmail: data[8],     //I
	     projectName: data[9],     //J
	     projectUser: data[10],    //K
	     projectUserEmail: data[11],  //L   
	     service: data[12],        //M
	     invoice: data[13],        //N
	     abCompName: data[14],     //O
	     abCatNum: data[15],       //P
	     abLotNum: data[16],       //Q
	     abNotes: data[17],        //R
	     abClonal: data[18],       //S
	     abAnimal: data[19],       //T
	     ig: data[20],             //U
	     antigen: data[21],        //V
	     ulSampleSent: data[22],   //W
	     abConc: data[23],         //X 
	     amountUseUg: data[24],    //Y
	     amountUseUl: data[25],    //Z
	     // data[26],              //AA
	     // data[27],              //AB
	     // abName: data[28],      //AC
	     samplePrepUser: data[29], //AD
	     genus: data[30],          //AE
	     species: data[31],        //AF
	     strain: data[32],         //AG
	     parentStrain: data[33],   //AH        
	     genotype: data[34],       //AI
	     mutation: data[35],       //AJ    
	     growthMedia: data[36],    //AK    
	     perturbation1: data[37],  //AL
	     perturbation2: data[38],  //AM
	     targetType: data[39], // changed  //AN
	     // data[40],              //AO
	     //bioRep: data[41],       //AP
	     sampleId: data[42],       //AQ
	     bioRep1SampleId: data[43],//AR
	     bioRep2SampleId: data[44],//AS
	     sampleNotes: data[45],    //AT
	     nTag: data[46],           //AU    
	     target: data[47],         //AV
	     cTag: data[48],           //AW
	     chromAmount: data[49],    //AX
	     cellNum: data[50],        //AY    
	     volume: data[51],         //AZ
	     assay: data[52],          //BA
	     genomeBuild1: data[53],   //BB    
	     genomeBuild2: data[54],   //BC
	     genomeBuild3: data[55],   //BD    
	     // data[56],              //BE    
	     dateReceived: data[57],   //BF
	     receivingUser: data[58],  //BG    
	     inOrExternal: data[59],   //BH
	     // data[60],              //BI
	     inventoryNotes: data[61], //BJ
	     chipUser: data[62],       //BK
	     // data[63],              //BL
	     chipDate: data[64],       //BM
	     // data[65],              //BN
	     protocolVersion: data[66],//BO
	     techRep: data[67],        //BP
	     requestedTagNum: data[68],//BQ
	     // cellNum: data[69],     //BR
	     // data[70],              //BS
	     // data[71],              //BT
	     // chipQubit: data[72],   //BU
	     // : data[73],            //BV
	     // : data[74],            //BW
	     resin: data[75],          //BX
	     pool: data[76],           //BY
	     volToPool: data[77],      //BZ
	     poolDate: data[78],       //CA
	     PCRCycle: data[79],       //CB
	     quibitReading: data[80],  //CC
	     quibitDilution: data[81], //CD
	     concentration: data[82],  //CE
	     poolDilution: data[83],   //CF
	     // data[84],              //CG
	     seqRepNum: data[85],      //CH
	     seqRepId: data[86],       //CI
	     rd1Start: data[87],       //CJ
	     rd1End: data[88],         //CK
	     indexStart: data[89],     //CL
	     indexEnd: data[90],       //CM
	     rd2Start: data[91],       //CN
	     rd2End: data[92],         //CO
	     // data[93],              //CP
	     runStr: data[94],         //CQ
	     // data[95],              //CR
	     // data[96],              //CS
	     // data9[7],              //CT
	     // data[98],              //CU
	     userStr: data[99],        //CV
	     dateStr: data[100],       //CW
	     fcidStr: data[101],       //CX
         indexStr: data[113]       //DJ
	    ]
	}
}