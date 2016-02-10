package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*
import groovy.time.*

class DataMigrate {
	DataMigrate() {}
	
	def migrate(String filename, int startLine, int endLine){
        def timeStart = new Date()
		def file = new FileReader(filename)
		def lineNo = 0    
		
		CSVReader reader = new CSVReader(file);
		String [] rawdata;
		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (lineNo > endLine) {
		        break
		    }
		    try {
                String[] cells = new String[rawdata.size()]
                rawdata.eachWithIndex{ d, idx -> 
                    def td = d.trim()
                    if(td == "-" || td == "." || td == "?" || td == "Not applicable" || td == "not applicable" || td == "None") {
                        cells[idx] = ""
                    }else {
                        cells[idx] = td
                    }
                 }
		        def data = getNamedData(cells)
				
				def results = getSequenceRun(data.runStr, data.laneStr, data.dateStr, data.fcidStr, data.indexIdStr)
		
                getDefaultUser()
                
		        def cellProvider = getUser(data.senderNameStr, data.senderEmail, data.senderPhone)
		        def dataTo = getUser(data.dataToUser, data.dataToEmail, null)
		
		        def project = getProject(data.projectName, data.projectUser, data.projectUserEmail)
		
		        def invoice = getInvoice(data.service, data.invoice)
                
		        def target = getTarget(data.target, data.targetType, data.nTag, data.cTag)
		
		        def antibody = getAntibody(data.abCompName, data.abCatNum, data.abLotNum, data.abNotes, data.abClonal, data.abAnimal, data.ig, data.antigen, data.ulSampleSent, data.abConc, data.amountUseUg, data.amountUseUl, data.abName)
		
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
                
		        addSampleToProject(project, sample)
		        
		        getPool(sample, results.sequenceRun, data.pool, data.volToPool, data.poolDate, data.quibitReading, data.quibitDilution, data.concentration, data.poolDilution)
		        
		        def seqExp = getSeqExperiment(sample, results.sequenceRun, results.seqId, data.rd1Start, data.rd1End, data.indexStart, data.indexEnd, data.rd2Start, data.rd2End)
		        
		        [data.genomeBuild1, data.genomeBuild2, data.genomeBuild3].each{ getSeqAlign(it, seqExp, species) }
		
		    }catch(Exception e) {
		        println "Error: line ${lineNo}. " + e
		        continue
		    }
		}
		
		Sample.list().each{ getBioReplicate(it) }
        
        def timeStop = new Date()
        TimeDuration duration = TimeCategory.minus(timeStop, timeStart)
        println "Time: " + duration
	}
	
	def addSampleToProject(Project project, Sample sample) {
	    if(project && sample) {
	        new ProjectSamples(project: project, sample: sample).save(failOnError: true)
	    }
	}
	
    def getUser(String userStr) {
        return getUser(userStr, null, null)
    }
	def getUser(String userStr, String emailStr, String phoneStr) {
	    if(userStr == "") {
	        return null
	    }    
	    def user = findUser(userStr)
	    
	    if (!user) {
	        def username = null
	        
	        if (emailStr != null && emailStr != 'bfp2@psu.edu') {
	            def at = emailStr.indexOf('@')
	            if (at != -1) {
	                username = emailStr[0..<at]
	            }
	        }
	        if (username == null) {
	            def names = userStr.split(",|\\.")*.trim()
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
            if (phoneStr) {
                phoneStr =  phoneStr.replaceAll("\\p{P}","");
            }
	        user = new User(username: username, password: password, fullName: userStr, email: emailStr, phone: phoneStr).save(failOnError: true)
        } else {
            if (user.email == null && emailStr != null && emailStr != 'bfp2@psu.edu') {
                user.email = emailStr
                def at = emailStr.indexOf('@')
	            if (at != -1) {
	                user.username = emailStr[0..<at]
	            }
                user.save(failOnError: true)
            }
        }
	    return user
	}
	
	def getProject(String projectName, String projectUser, String projectUserEmail) {
	    if (projectName == "") {
	        return null
	    }
	    def project = Project.findByName(projectName)
	    if (!project) {
	        project = new Project(name: projectName).save(failOnError: true)
	    }
	    def user = getUser(projectUser, projectUserEmail, null)
	    if (user && project) {
            if ( !ProjectUser.findByProjectAndUser(project, user)) {
                new ProjectUser(project: project, user: user, projectRole: ProjectRole.OWNER).save(failOnError: true)
            }
	    }
	    
	    return project
	}
	
	def getInvoice(String service, String invoiceNum) {
	    if (service == "" && invoiceNum == "") {
	        return null
	    }
		
		def invoice = null
		if (service == "") {
			invoice = Invoice.where{
				serviceId == null && invoiceNum == invoiceNum
			}.get(max:1)
		} else if(invoiceNum == "") {
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
	        invoice = new Invoice(serviceId: service, invoiceNum: invoiceNum, date: date).save(failOnError: true)
	    } 
	    return invoice
	}
	
	def getAntibody(String abCompName, String abCatNum, String abLotNum, String abNotes, String abClonal, String abAnimal, String ig, String antigen, String ulSent, String abConc, String ugPerChIP, String ulPerChIP, String abName) {
	    def name = "${abCompName}-${abCatNum}-${abLotNum}-${abClonal}-${abAnimal}-${ig}-${antigen}-${abConc}"
	    if(name=="-------") {
	        return null
	    }
	    def antibody = Antibody.findByName(name)
	    if (!antibody) {
	        def company = getCompany(abCompName)
	        def abHost = getAbHost(abAnimal)
	        def clonal = getClonal(abClonal)
	        def igType = getIgType(ig)
	        def concentration = getFloat(abConc)
	        
	        def noteMap = [:]
	        if (abNotes != "") {
	            noteMap['note'] = abNotes
	        }
	        if (ulSent != "") {
	            noteMap['ulSent'] = ulSent
	        }
	        if (ugPerChIP != "") {
	            noteMap['ugPerChIP'] = ugPerChIP
	        }
	        if (ulPerChIP != "") {
	            noteMap['ulPerChIP'] = ulPerChIP
	        }
	        
	        def notes = JsonOutput.toJson(noteMap)
	        
	        antibody = new Antibody(company: company, catalogNumber: abCatNum, abHost: abHost, clonal: clonal, concentration: concentration, igType: igType, immunogene: antigen, lotNum: abLotNum, name: name, note: notes).save(failOnError: true)
	        
	        return antibody
	    }
	}
    
    def getStrainTissueByName(String name, Species species) {
        def strain = null
        def tissue = null
        if (name && name != "") {
            if (name == "InVitro" || name == "Liver" || name.contains("tissue") || name.contains("cells") || name.contains("leukemia")) {
                tissue = Tissue.findByName(name)
                if (!tissue) {
                    tissue = new Tissue(name: name).save(failOnError: true)
                }
            } else{
                strain = Strain.findByName(name) 
                if (!strain) {
                    strain = new Strain(name: name, species: species).save(faliOnError: true)    
                }
            }
        }
        return [strain: strain, tissue: tissue]
    }
	
	def getStrainTissue(Species species, String strainStr, String parentStrainStr, String genotypeStr, String mutationStr) {
        if (mutationStr == "") {
            mutationStr = null
        }
        def backResult = getStrainTissueByName(strainStr, species)
        def backStrain = backResult.strain
        def tissue = backResult.tissue
        
        def parentResult = getStrainTissueByName(parentStrainStr, species)        
        def parentStrain = parentResult.strain
        if (!tissue) {
            tissue = parentResult.tissue
        }
        
	    def strain = Strain.findByBackgroundStrainAndGenotypeAndGeneticModification(backStrain, genotypeStr, mutationStr)
	    
        if (!strain) {
            if (parentStrain == backStrain) {
                parentStrain = null
            }
	        strain = new Strain(name: null, species: species, genotype: genotypeStr, 
				backgroundStrain: Strain.get(backStrain.id), parent: Strain.get(parentStrain.id), geneticModification: mutationStr).save(failOnError: true)
	    }
	    return [strain: strain, tissue: tissue]
	}
	
	def getSpecies(String genusStr, String speciesStr) {
	    if(genusStr=="" && speciesStr == "") {
	        return null
	    }
		if (genusStr == "") {
			genusStr = "Unknown"
		}
		if (speciesStr == "") {
			speciesStr = "Unknown"
		}
	    def species = Species.findByNameAndGenusName(speciesStr, genusStr)
	    if(!species) {
	        species = new Species(name: speciesStr, genusName: genusStr).save(failOnError: true)
	    }
	    return species
	}
	
	def getGrowthMedia(String mediaStr, Species species) {
	    if(mediaStr == "") {
	        return null
	    }
	    def media = GrowthMedia.findByName(mediaStr)
	    if(!media) {
	        media = new GrowthMedia(name: mediaStr, species: species).save(failOnError: true)
	    } else {
	        if(media.species != species) {
	            media.species = null
				media.save(failOnError: true)
	        }
	    }
	    return media
	}
	
	
	def getCellSource( String samplePrepUser, GrowthMedia growthMedia, Strain strain, User provider, Inventory inventory, Tissue tissue) {
	    if (!strain) {
	        return null
	    }
		def prepUser = getUser(samplePrepUser)
	    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, prepUser: prepUser, inventory: inventory, tissue: tissue).save(failOnError: true)
	    return cellSource
	}
	
	def addTreatment(CellSource cellSource, String perturbStr) {
	    if(perturbStr == "") {
	        return null
	    }
	    def perturbation = CellSourceTreatment.findByName(perturbStr)
	    if(!perturbation) {
	        perturbation = new CellSourceTreatment(name: perturbStr).save(failOnError: true)
	    }
	    if(perturbation && cellSource) {
			new CellSourceTreatments(cellSource: cellSource, treatment: perturbation).save(failOnError: true)
		} 
	}
    
    def getProtocolInstanceSummary(String chipUser, String chipDate, String protocolVersion, String resin, String PCRCycle, Assay assay){
        def protocol = null
        if (protocolVersion == "") {
            protocolVersion = null
        }
        
        if (assay) {
	        protocol = Protocol.findByNameAndProtocolVersion(assay.name, protocolVersion)  
	        if (!protocol) {
	            protocol = new Protocol(name: assay.name, protocolVersion: protocolVersion).save(failOnError: true)
	        }
	    }
	    // get note
	    def note = [:]
	    if (resin != "") {
	        note['resin'] = resin
	    }
	    if (PCRCycle != "") {
	        note['PCRCycle'] = PCRCycle
	    }
	    
	    def date = getDate(chipDate)
	    def prtcl = new ProtocolInstanceSummary(protocol: protocol, user: getUser(chipUser), startTime: date, endTime: date, note: JsonOutput.toJson(note)).save(failOnError: true)
        return prtcl
    }
	
	def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, String sampleId, String bioRep1SampleId, String bioRep2SampleId, Invoice invoice, User dataTo, String dateStr, ProtocolInstanceSummary prtcl) {
	    def note = [:]
	    if (sampleNotes != "") {
	        note['note'] = sampleNotes
	    }
	    if (bioRep1SampleId != "") {
	        note['bioRep1'] = bioRep1SampleId
	    }
	    if (bioRep2SampleId != "") {
	        note['bioRep2'] = bioRep2SampleId
	    }
	    def date = getDate(dateStr)
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: getFloat(requestedTagNum), chromosomeAmount: getFloat(chromAmount), cellNumber: getFloat(cellNum), volume: getFloat(volume), note: JsonOutput.toJson(note), status: SampleStatus.COMPLETED, date: date, sendDataTo: dataTo, invoice: invoice, prtclInstSummary: prtcl).save(failOnError: true)
	    return sample
	}
	
	def getBioReplicate(Sample sample) {    
	    if(sample) {
	        def jsonSlurper = new JsonSlurper()
			try {
				def note = jsonSlurper.parseText(sample.note)
	
		        def sample2 = getSampleFromSampleId(note.bioRep2)
		        def sample1 = getSampleFromSampleId(note.bioRep1)
		        if (sample2 != null || sample1 != null) {
		            def set = BiologicalReplicateSamples.findBySample(sample)?.set  
		            if (!set) {
		                set = new BiologicalReplicateSet().save(failOnError: true)
		                new BiologicalReplicateSamples(set: set, sample: sample).save(failOnError: true)
		            }
		            if (set && sample2) {
		                new BiologicalReplicateSamples(set: set, sample: sample2).save(failOnError: true)
		            }
		            if (set && sample1) {
		                new BiologicalReplicateSamples(set: set, sample: sample1).save(failOnError: true)
		            }
		        }
		        sample.note = note.note
		        sample.save(failOnError: true)
			}catch(Exception e) {
				return
		    }
	    }
	}
	
	def getSampleFromSampleId(String sampleId) {
	    if (sampleId == null || sampleId == "") {
	        return null
	    }
	    def samples = Sample.where{note == '%"sampleId":"${sampleId}"%'}.list()
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
	    
	    def inventory = new Inventory(dateReceived: date, receivingUser:user, sourceType: source, notes: inventoryNotes).save(failOnError: true)
	    return inventory
	}
	             
	def getAssay(String assayStr) {
	    if(assayStr == "") {
	        return null
	    }
	    
	    def assay= Assay.findByName(assayStr)
	    if(!assay) {
	        assay = new Assay(name: assayStr).save(failOnError: true)
	    }
	    return assay
	}
	             
	def getPool(Sample sample, SequenceRun run, String pool, String volToPool, String poolDateStr, String quibitReading, String quibitDilution, String concentration, String poolDilution) {
	    if (sample && run) {
	        def poolDate = getDate(poolDateStr)
	        
	        def map = [:]
	        if (concentration != ""){
	            map['concentration'] = concentration
	        }
	        if (quibitReading != "") {
	            map['quibitReading'] = quibitReading
	        }
	        if (quibitDilution != "") {
	            map['quibitDilution'] = quibitDilution
	        }
	        if (concentration != "") {
	            map['concentration'] = concentration
	        }
	        
	        def params = JsonOutput.toJson(map)
	        def vol = getFloat(volToPool)
	        new SampleInRun(poolDate: poolDate, sample: sample, run: run, pool: pool, params: params, volumeToPool: vol).save(failOnError: true)
	    }
	}
	        
	def getSeqExperiment(Sample sample, SequenceRun seqRun, String seqId, String rd1Start, String rd1End, String indexStart, String indexEnd, String rd2Start, String rd2End) {
		def map = [:]
		if(rd1Start != "" || rd1End != "") {
			map['rd1'] = [rd1Start, rd1End]
		}
		if(indexStart != "" || indexEnd != "") {
			map['index'] = [indexStart, indexEnd]
		}
		if(rd2Start != "" || rd2End != "") {
			map['rd2'] = [rd2Start, rd2End]
		}
	    def readPositions = JsonOutput.toJson(map)
	    def seqExp = new SequencingExperiment(seqId: seqId, sample: sample, sequenceRun: seqRun, readPositions: readPositions).save(failOnError: true)
	    return seqExp
	}
	        
	def getSeqAlign(String genomeBuild, SequencingExperiment seqExp, Species species) {
	    def genome = getGenome(genomeBuild, species)
	    if (seqExp && genome) {
	        new SequenceAlignment(sequencingExperiment: seqExp, genome: genome).save(failOnError: true)
	    }
	}
	
	def getGenome(String genomeStr, Species species) {
	    if (genomeStr == "") {
	        return null
	    }
	    def genome = Genome.findByName(genomeStr)
	    if (!genome) {
	        genome = new Genome(name: genomeStr, species: species).save(failOnError: true)
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
	    if (clonalStr.contains("mono")) {
	        clonal = MonoPolyClonal.Mono
	    } else if (clonalStr.contains("poly")) {
	        clonal = MonoPolyClonal.Poly
	    }
	    return clonal
	}
	
	def getIgType(String igStr) {
	    if (igStr == "") {
	        return null
	    }
	    def igType = IgType.findByName(igStr)
	    if (!igType) {
	        igType = new IgType(name: igStr).save(failOnError: true)
	    }
	    return igType
	}
	
	def getTarget(String targetStr, String targetTypeStr, String nTag, String cTag) {
	    if ([targetStr, cTag, nTag].every{ it == "" }) {
	        return null
	    }
        if (targetStr == "") {
            targetStr = null
        }
        if (nTag == "") {
            nTag = null
        }
        if (cTag == "") {
            cTag = null
        }

	    def target = Target.findByNameAndCTermTagAndNTermTag(targetStr, cTag, nTag)
	    if (!target) {
	        def type = getTargetType(targetTypeStr)
	        target = new Target(name: targetStr, cTermTag: cTag, nTermTag: nTag, targetType: type).save(failOnError: true)
	    }
	    return target
	}
	
	def getTargetType(String str) {
	    if (str == "") {
	        return null
	    }
	    def type = TargetType.findByName(str)
	    if (!type) {
	        type = new TargetType(name: str).save(failOnError: true)
	    }
	    return type
	}
	
	def getAbHost(String abHostName) {
	    if (abHostName == "") {
	        return null
	    }
	    def abHost = AbHost.createCriteria().get{
	        eq("name", abHostName, [ignoreCase: true])
	        maxResults(1)
	    }
	    if(!abHost) {
	        abHost = new AbHost(name: abHostName).save(failOnError: true)
	    }
	    return abHost
	}
	
	def getCompany(String companyStr) {
	    if (companyStr == "") {
	        return null
	    }    
	    def company = Company.findByName(companyStr)
	    if(!company) {
	        company = new Company(name: companyStr).save(failOnError: true)
	    }
	    return company
	}
	
	def getSequenceRun(String runStr, String laneStr, String dateStr, String fcidStr, String indexIdStr) {
	    int runNum
	    def platform
	    def seqId
        if (runStr == "") {
            runStr = "00"
        } else if (runStr.length() == 1) {
            runStr = "0" + runStr
        }
        
        if (indexIdStr == ""){
            indexIdStr = "00" + indexIdStr
        } else if (indexIdStr.length() == 1) {
            indexIdStr = "0" + indexIdStr
        }
        
	    if (runStr.take(1) == "S") {
	         runNum = runStr.substring(1).toInteger()
	         platform = SequencingPlatform.findByName("SOLiD")
	         seqId = runStr + laneStr + indexIdStr
	    } else if (runStr.take(1) == "G") {
	         runNum = runStr.substring(1).toInteger()
	         platform = SequencingPlatform.findByName("Illumina GA")
	        seqId = runStr + laneStr + indexIdStr
	    } else {
	         runNum = runStr.toInteger() 
	         if (runNum < 500){
	             platform = SequencingPlatform.findByName("HiSeq 2000")
	             seqId = runStr + laneStr + indexIdStr
	         } else {
	             platform = SequencingPlatform.findByName("NextSeq 500")
	             seqId = runStr + indexIdStr
	         }
	    }
	
	    if (SequencingExperiment.findBySeqId(seqId)) {
	        throw new Exception("SeqId ${seqId} already exists!")
	    }
	
	    def run = SequenceRun.findByPlatformAndRunNum(platform, runNum)
	    if (!run) {                     
	         run = new SequenceRun(runNum: runNum, platform: platform)
	
	         // lane
	         run.lane = laneStr.toInteger()
	
	         // date
	         run.date = getDate(dateStr)
	
	         // fcId
	         run.fcId = fcidStr
	
	         run = run.save(failOnError: true)
	    }
	    
	    [sequenceRun: run, seqId: seqId]
	
	}
	
	def getDate(String dateStr) {
	    def date = null
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
	    return date
	}
	
	def findUser(String userStr) {
	    def user = User.findByFullNameIlike("%${userStr}%") ?: User.findByUsername(userStr)
	    return user
	}
    
    def getDefaultUser() {
        def user = User.findByUsername("bfp2")
        if (!user) {
            def admin = User.get(1)	        
	        def password = admin.password
            new User(username: "bfp2", email: "bfp2@psu.edu", fullName: "Pugh, B. Franklin", password: password).save(failOnError:true) 
        }
    }
	
	def getNamedData(String[] data) {
	    [laneStr: data[0],         
	     // data[1]
	     // data[2]
	     indexIdStr: data[3],
	     senderNameStr: data[4],
	     senderEmail: data[5],
	     senderPhone: data[6],
	     dataToUser: data[7],
	     dataToEmail: data[8],
	     projectName: data[9],
	     projectUser: data[10],
	     projectUserEmail: data[11],     
	     service: data[12],
	     invoice: data[13],
	     abCompName: data[14],
	     abCatNum: data[15],
	     abLotNum: data[16],
	     abNotes: data[17],
	     abClonal: data[18],
	     abAnimal: data[19],
	     ig: data[20],
	     antigen: data[21],
	     ulSampleSent: data[22],
	     abConc: data[23],
	     amountUseUg: data[24],
	     amountUseUl: data[25],
	     // data[26],
	     // data[27],
	     abName: data[28],
	     samplePrepUser: data[29],
	     genus: data[30],
	     species: data[31],
	     strain: data[32],
	     parentStrain: data[33],
	     genotype: data[34],
	     mutation: data[35],
	     growthMedia: data[36],
	     perturbation1: data[37],
	     perturbation2: data[38],
	     targetType: data[39], // changed
	     // data[40],
	     //bioRep: data[41],
	     sampleId: data[42],
	     bioRep1SampleId: data[43],
	     bioRep2SampleId: data[44],
	     sampleNotes: data[45],
	     nTag: data[46],
	     target: data[47],
	     cTag: data[48],     
	     chromAmount: data[49],
	     cellNum: data[50],
	     volume: data[51],
	     assay: data[52],
	     genomeBuild1: data[53],
	     genomeBuild2: data[54],
	     genomeBuild3: data[55],
	     // data[56],
	     dateReceived: data[57],
	     receivingUser: data[58],
	     inOrExternal: data[59],
	     // data[60],
	     inventoryNotes: data[61],
	     chipUser: data[62],
	     // data[63],
	     chipDate: data[64],
	     // data[65],
	     protocolVersion: data[66],
	     techRep: data[67],
	     requestedTagNum: data[68],
	     // cellNum: data[69],
	     // data[70],
	     // data[71],
	     // chipQubit: data[72],
	     // : data[73],
	     // : data[74],
	     resin: data[75],
	     pool: data[76],
	     volToPool: data[77],
	     poolDate: data[78],
	     PCRCycle: data[79],
	     quibitReading: data[80],
	     quibitDilution: data[81],
	     concentration: data[82],
	     poolDilution: data[83],
	     // data[84],
	     seqRepNum: data[85],
	     seqRepId: data[86],
	     rd1Start: data[87],
	     rd1End: data[88],
	     indexStart: data[89],
	     indexEnd: data[90],
	     rd2Start: data[91],
	     rd2End: data[92],
	     // data[93],
	     runStr: data[94],
	     // data[95],
	     // data[96],
	     // data9[7],
	     // data[98],
	     userStr: data[99],
	     dateStr: data[100],
	     fcidStr: data[101],
	    ]
	}
}