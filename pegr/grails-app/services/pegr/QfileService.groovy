package pegr
import grails.transaction.*
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*
import groovy.time.*

class QfileException extends RuntimeException {
    String message
}
    
@Transactional
class QfileService {
    def cellSourceService
    def sampleService
    def antibodyService
    def utilityService
    final int timeout = 1000 * 60 * 2; 
	
    def checkFile(String filepath, int startLine, int endLine) {
        def lineNo = 0  
        def file = new FileReader(filepath)
		CSVReader reader = new CSVReader(file)
		String [] rawdata
		def warnings = []
        def newProjectCount = 0
        def MAX_NEW_PROJECT_COUNT = 10
        def projectNames = []
        
		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (endLine > 0 && lineNo > endLine) {
		        break
		    }
            cleanRawData(rawdata)
            def data = getNamedData(rawdata)
            def projectName = getProjectName(data.projectName, data.service, data.invoice)
            if (!projectNames.contains(projectName)) {
                projectNames.push(projectName)
            
                def project = Project.findByName(projectName)
                if (!project) {
                    newProjectCount++
                }
            }
        }
        if (newProjectCount > MAX_NEW_PROJECT_COUNT) {
            warnings.push("More than ${MAX_NEW_PROJECT_COUNT} new projects will be created!")
        }
        return warnings
    }
    
    def convertXlsxToCsv(File folder, String filename, String sampleSheetName, String laneSheetName) {
        def csvNames = [:]
        // convert xsxl to csv
        [sampleSheetName, laneSheetName].each { sheet ->
            csvNames[sheet] = new File(folder, sheet + ".csv").getPath()
            def command = "xlsx2csv -n ${sheet} ${filename} ${csvNames[sheet]}"
            utilityService.executeCommand(command, timeout)
        }
        return csvNames
    } 
    
    @NotTransactional
    def migrateXlsx(String sampleSheet, String laneSheet, RunStatus runStatus, int startLine, int endLine, int laneLine, boolean basicCheck) {
        getDefaultUser()     
        
        // migrate the samples
        def results = migrateSamples(sampleSheet, runStatus, startLine, endLine, basicCheck)
        def messages = results.messages
        def laneRunNum = results.laneRunNum
        
        // migrate the lane
        def laneMessages = migrateLane(laneSheet, laneLine, laneRunNum)
        messages.addAll(laneMessages)

        return messages
    }
    
    @NotTransactional
	def migrateSamples(String filename, RunStatus runStatus, int startLine, int endLine, boolean basicCheck){
		def lineNo = 0  
        def file = new FileReader(filename)
		CSVReader reader = new CSVReader(file);
		String [] rawdata;
		def messages = []
        def laneRunNum = 0
        
		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (endLine > 0 && lineNo > endLine) {
		        break
		    }
            try {
                laneRunNum = Math.max(laneRunNum, migrateOneSampleRow(rawdata, runStatus, basicCheck))
		 	} catch(QfileException e) {
                messages.push("Error: Line ${lineNo}. ${e.message}")
		        continue
		    } catch(Exception e) {
		        log.error "Error: line ${lineNo}. " + e
                messages.push("Error: Line ${lineNo}.")
		        continue
		    }   
		}
        new File(filename).delete()
        return [messages: messages, laneRunNum: laneRunNum]
	}
    
    def migrateLane(String filename, int laneLine, Integer laneRunNum) {
        def lineNo = 0    
        def file = new FileReader(filename)
		CSVReader reader = new CSVReader(file);
        String [] rawdata;
        def messages = []
        while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < laneLine) {
		        continue
		    } else {
                try {
                    migrateOneLaneRow(rawdata, laneRunNum)
                    new File(filename).delete()
                } catch(Exception e) {
                    log.error "Error: Lane Info." + e
                    messages.push("Error: Lane Info.")
                }   
		        break
		    }
		}
        return messages
    }
    
    def cleanRawData(String[] rawdata) {
        rawdata.eachWithIndex{ d, idx -> 
            def td = d.trim()
            if(td == "" || td == "-" || td == "." || td == "?" || td == "Not applicable" || td == "not applicable" || td == "None") {
                rawdata[idx] = null
            }else {
                rawdata[idx] = td
            }
        }
    }
	
    def migrateOneSampleRow(String[] rawdata, RunStatus runStatus, boolean basicCheck) {

        cleanRawData(rawdata)
        def data = getNamedData(rawdata)
        
        // stop if basiceCheck is true and this row does not have a run number
        if (basicCheck && (!data.runStr || data.runStr == "Run #")) {
            throw new QfileException(message: "Run number is missing!")
        }
        
        def runUser = getUser(data.userStr)

        def results = getSequenceRun(runStatus, data.runStr, data.laneStr, data.dateStr, data.fcidStr, data.indexIdStr, runUser)

        def cellProvider = getUser(data.senderNameStr, data.senderEmail, data.senderPhone)
        def dataTo = getUser(data.dataToUser, data.dataToEmail, null)

        def project = getProject(data.projectName, data.projectUser, data.projectUserEmail, data.service, data.invoice)

        def invoice = getInvoice(data.service, data.invoice)

        def target 
        try {
            target = antibodyService.getTarget(data.target, data.targetType, data.nTag, data.cTag)
        } catch(AntibodyException e) {
            throw new QfileException(message: e.message)
        }

        def antibody = getAntibody(data.abCompName, data.abCatNum, data.abLotNum, data.abNotes, data.abClonal, data.abAnimal, data.ig, data.antigen, data.abConc)

        def species = cellSourceService.getSpecies(data.genus, data.species)

        def strainAndTissue = getStrainTissue(species, data.strain, data.parentStrain, data.genotype, data.mutation)
        def strain = strainAndTissue.strain
        def tissue = strainAndTissue.tissue

        def growthMedia = sampleService.getGrowthMedia(data.growthMedia, species)

        def inventory = getInventory(data.dateReceived, data.receivingUser, data.inOrExternal, data.inventoryNotes)
        def prepUser = getUser(data.samplePrepUser)
        def cellSource = getCellSource(prepUser, strain, cellProvider, inventory, tissue)
        
        def assay = getAssay(data.assay)

        def prtcl = getProtocolInstanceSummary(data.chipUser, data.chipDate, data.protocolVersion, data.resin, data.PCRCycle, assay)
        
        def abnoteMap = [:]
        if (data.ulSampleSent) {
            abnoteMap['Volume Sent (ul)'] = data.ulSampleSent
        }
        if (data.amountUseUg) {
            abnoteMap['Usage Per ChIP (ug)'] = data.amountUseUg
        }
        if (data.amountUseUl) {
            abnoteMap['Usage Per ChIP (ul)'] = data.amountUseUl
        }

        def abNotes = JsonOutput.toJson(abnoteMap)
        
        def sample = getSample(cellSource, antibody, target, data.cellNum, data.chromAmount, data.volume, data.requestedTagNum, data.sampleNotes, data.sampleId, data.bioRep1SampleId, data.bioRep2SampleId, invoice, dataTo, data.dateStr, prtcl, results.seqId, abNotes, assay, growthMedia, data.projectName)

        sampleService.addTreatment(sample, data.perturbation1)
        sampleService.addTreatment(sample, data.perturbation2)
        
        addIndexToSample(sample, data.indexStr, data.indexIdStr, basicCheck)
        sampleService.addSampleToProject(project, sample)

        getPool(sample, results.sequenceRun, data.pool, data.volToPool, data.poolDate, data.quibitReading, data.quibitDilution, data.concentration, data.poolDilution)

        def seqExp = getSeqExperiment(sample, results.sequenceRun, data.rd1Start, data.rd1End, data.indexStart, data.indexEnd, data.rd2Start, data.rd2End)
        
        addExperimentToCohort(seqExp, project, data.service, data.invoice)

        def genomeBuilds = [data.genomeBuild1, data.genomeBuild2, data.genomeBuild3]
        saveRequestedGenome(genomeBuilds, sample, species) 
        
        return results?.sequenceRun?.runNum
    }
    
    def migrateOneLaneRow(String[] rawdata, Integer laneRunNum) {
        cleanRawData(rawdata)
        def data = getNamedLaneData(rawdata)
        if(data.runNum) {
            laneRunNum = data.runNum
        }
        if (!laneRunNum) {
            throw new QfileException(message: "Run number not found!")
        }
        def run = SequenceRun.findByRunNum(laneRunNum)
        if (!run) {
            throw new QfileException(message: "Sequence run #Old${data.runNum} is not found!")
        }
        def runStats
        if (run.runStats) {
            runStats = run.runStats
            runStats.properties = data
        } else {
            runStats = new RunStats(data)
        }
        runStats.save(flush: true, failOnError: true)
        if (!run.runStats) {
            run.runStats = runStats
            run.save()
        }
    }
    
    def addExperimentToCohort(SequencingExperiment seqExp, Project project, String service, String invoice) {
        if (!project || !seqExp.sequenceRun) {
            return
        }

        def cohort = SequencingCohort.findByProjectAndRun(project, seqExp.sequenceRun)
        if (!cohort) {
            def cohortNameDefault = "${seqExp.sequenceRun.id}_${service}-${invoice}"
            def cohortName = cohortNameDefault
            def cohortWithName = SequencingCohort.findByName(cohortName)
            def count = 1
            while (cohortWithName) {
                cohortName = cohortNameDefault + "_" + count
                cohortWithName = SequencingCohort.findByName(cohortName)
                count++
            }
            cohort = new SequencingCohort(project: project, run: seqExp.sequenceRun, name: cohortName)
            cohort.save()
        }
        seqExp.cohort = cohort
        seqExp.save()
    }
    
    def addIndexToSample(Sample sample, String indexStr, String indexIdStr, boolean basicCheck) { 
        try {
            sampleService.splitAndAddIndexToSample(sample, indexStr)
        } catch (SampleException e) {
            throw new QfileException(message: e.message)
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
	
    def getProjectName(String projectStr, String service, String invoice) {
        def projectName
        
        if (invoice && invoice[0].toUpperCase() in ["S", "P", "X"]) {
            projectName = service
        } else if (projectStr && projectStr != "") {
            projectName = projectStr
        } else {
            projectName = "${service}-${invoice}"
        }
        return projectName
    }
    
	def getProject(String projectStr, String projectUser, String projectUserEmail, String service, String invoice) {
        def projectName = getProjectName(projectStr, service, invoice)
	    def project = Project.findByName(projectName)
	    if (!project) {
	        project = new Project(name: projectName, description: projectStr).save( failOnError: true)
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
	
	def getAntibody(String abCompName, String abCatNum, String abLotNum, String abNotes, String abClonal, String abAnimal, String ig, String antigen, String abConc) {
		def company = antibodyService.getCompany(abCompName)
		def abHost = antibodyService.getAbHost(abAnimal)
		def clonal = getClonal(abClonal)
		def igType = antibodyService.getIgType(ig)
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
	            protocol = new Protocol(name: assay.name, protocolVersion: v, assay: assay).save( failOnError: true)
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
	
    def getCellSource(User prepUser, Strain strain, User provider, Inventory inventory, Tissue tissue) {
	    if (!strain) {
	        return null
	    }
	    def cellSource = new CellSource(providerUser: provider, strain: strain, prepUser: prepUser, inventory: inventory, tissue: tissue).save( failOnError: true)
	    return cellSource
	}
    
	def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, String sampleId, String bioRep1SampleId, String bioRep2SampleId, Invoice invoice, User dataTo, String dateStr, ProtocolInstanceSummary prtcl, String seqId, String abNotes, Assay assay, GrowthMedia growthMedia, String projectName) {
	    def note = [:]
	    if (sampleNotes) {
	        note['note'] = sampleNotes
	    }
	    if (bioRep1SampleId) {
	        note['bioRep1'] = bioRep1SampleId
	    }
	    if (bioRep2SampleId) {
	        note['bioRep2'] = bioRep2SampleId
	    }
        if (projectName) {
            if (note['note']) {
                note['note'] += ". " + projectName
            } else {
                note['note'] = projectName
            }            
        }
	    def date = getDate(dateStr)
        def source = "PughLab"
        if (invoice?.invoiceNum && invoice.invoiceNum.size() >= 4 && invoice.invoiceNum[1..3] ==~ /\d+/ ) {
            switch (invoice.invoiceNum[0].toLowerCase()) {
                case "p":
                    source = "Peconic"
                    break
                case "x":
                    source = "CustomerX"
                    break
                case "s":
                    source = "CustomerS"
                    break
            }
        }
        
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: getFloat(requestedTagNum), chromosomeAmount: getFloat(chromAmount), cellNumber: getFloat(cellNum), volume: getFloat(volume), note: JsonOutput.toJson(note), status: SampleStatus.COMPLETED, date: date, sendDataTo: dataTo, invoice: invoice, prtclInstSummary: prtcl, sourceId: seqId, source: source, antibodyNotes: abNotes, assay: assay, growthMedia: growthMedia, naturalId: sampleId).save( failOnError: true)
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
		            def sets = ReplicateSamples.findAllBySample(sample)*.set  
                    def set = sets.find {it.type == ReplicateType.BIOLOGICAL}
		            if (!set) {
		                set = new ReplicateSet(type: ReplicateType.BIOLOGICAL).save( failOnError: true)
		                new ReplicateSamples(set: set, sample: sample).save( failOnError: true)
		            }
		            if (set && sample2) {
		                new ReplicateSamples(set: set, sample: sample2).save()
		            }
		            if (set && sample1) {
		                new ReplicateSamples(set: set, sample: sample1).save()
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
        return Sample.findByNaturalId(sampleId)	    
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
	        
	def getSeqExperiment(Sample sample, SequenceRun seqRun, String rd1Start, String rd1End, String indexStart, String indexEnd, String rd2Start, String rd2End) {
        def readType = ReadType.findByShortName("SR")
		def map = [:]
		if(rd1Start || rd1End) {
			map['rd1'] = [rd1Start, rd1End]
		}
		if(indexStart || indexEnd) {
            def indexStartList = indexStart.split(",")
            def indexEndList = indexEnd.split(",")
            if (indexStartList.size() == 1) {
                map['index'] = [indexStart, indexEnd]
            } else {
                map['index1'] = [indexStartList[0], indexStartList[1]]
                map['index2'] = [indexEndList[0], indexEndList[1]]
            }
		}
		if(rd2Start || rd2End) {
			map['rd2'] = [rd2Start, rd2End]
            readType = ReadType.findByShortName("PE")
		}
	    def readPositions = JsonOutput.toJson(map)
	    def seqExp = new SequencingExperiment(sample: sample, sequenceRun: seqRun, readPositions: readPositions, readType: readType).save( failOnError: true)
	    return seqExp
	}
	        
	def saveRequestedGenome(List genomeBuilds, Sample sample, Species species) {
        genomeBuilds.removeAll {it == null}
        genomeBuilds.each { genomeBuild ->
            getGenome(genomeBuild, species)
        }
	    sample.requestedGenomes = genomeBuilds.join(",")
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
	
	    if (Sample.findBySourceId(seqId)) {
            throw new QfileException(message: "SeqId ${seqId} already exists!")
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
    
    def exportRun(Long runId) {
        def run = SequenceRun.get(runId)
        
        def laneExports = []
        laneExports << [
            libraryPoolArchiveId: run.runStats?.libraryPoolArchiveId,          //A       
            libraryVolume: run.runStats?.libraryVolume,                //B
            libraryStock: run.runStats?.libraryStock,                 //C
            libraryStdDev: run.runStats?.libraryStdDev,               //D
            pctLibraryStdDev: run.runStats?.pctLibraryStdDev,             //E
            qPcrDateStr: "",                    //F
            technicianName: "",                  //G
            emptyH: "", //H
            cycles: run.runStats?.cycles,                      //I
            srOrPe: run.runStats?.srOrPe,                       //J
            seqCtrl: run.runStats?.seqCtrl,                     //K
            pcrCycles: run.runStats?.pcrCycles,                   //L
            qubitConc: run.runStats?.qubitConc,                   //M
            qPcrConc: run.runStats?.qPcrConc,                   //N
            libraryLoadedPm: run.runStats?.libraryLoadedPm,             //O
            phiXLoaded: run.runStats?.phiXLoaded,                   //P
            libraryLoadedFmol: run.runStats?.libraryLoadedFmol,           //Q
            notes: run.runStats?.notes,                      //R
            runNum: run.runNum,                         //S
            emptyT: "",                     //T
            emptyU: "",                    //U
            emptyV: "",                   //V
            emptyW: "",                      //W
            emptyX: "",                    //X
            emptyY: "",                 //Y
            emptyZ: "",          //Z
            clusterNum: run.runStats?.clusterNum,                  //AA
            readPf: run.runStats?.readPf,                      //AB
            pctPf: run.runStats?.pctPf,                       //AC
            pctQ30: run.runStats?.pctQ30,                      //AD
            qidx: run.runStats?.qidx,                        //AE
            totalReads: run.runStats?.totalReads,                  //AF
            unmatchedIndices: run.runStats?.unmatchedIndices,           //AG
            pctUnmatchedIndices: run.runStats?.pctUnmatchedIndices,       //AH
            pctAlignedToPhiX: run.runStats?.pctAlignedToPhiX        //AI
        ]
        
        def sampleExports = []
        run.experiments.each { it ->
            def sample = it.sample
            def project = ProjectSamples.findBySample(sample).project
            def projectUser = ProjectUser.findByProject(project).user
            
            def abNotes = utilityService.parseJson(sample.antibodyNotes)
            
            def perturbations = SampleTreatments.findAllBySample(sample)
            def perturbation1 = perturbations.size() > 0 ? perturbations[0].treatment.name : ""
            def perturbation2 = perturbations.size() > 1 ? perturbations[1].treatment.name : ""
            
            def note = utilityService.parseJson(sample.note)
            
            def genomes = sample.requestedGenomes ? sample.requestedGenomes.split(',') : []
            
            def prtclNote = utilityService.parseJson(sample.prtclInstSummary.note)
            
            def sampleInRun = SampleInRun.findBySample(sample)
            def poolNote = utilityService.parseJson(sampleInRun.params)
            
            def seqExperiment = SequencingExperiment.findBySampleAndSequenceRun(sample, run)
            def readPositions = utilityService.parseJson(seqExperiment.readPositions)
            
            def platform = ""
            if (run.platform?.name == "SOLiD") {
                platform = "S"
            } else if (run.platform?.name == "Illumina GA") {
                platform = "G"
            }
            
            sampleExports << [
                laneStr: run.lane,      //A       
                emptyB: "",
                emptyC:"",
                indexIdStr: (sample.sourceId && sample.sourceId.size() > 1) ? sample.sourceId[-2..-1] : "",     //D
                senderNameStr: sample.cellSource?.providerUser?.fullName, //E
                senderEmail: sample.cellSource?.providerUser?.email,     //F
                senderPhone: sample.cellSource?.providerUser?.phone,     //G
                dataToUser: sample.sendDataTo?.fullName,    //H
                dataToEmail: sample.sendDataTo?.email,     //I
                projectName: project?.name,     //J
                projectUser: projectUser?.fullName,    //K
                projectUserEmail: projectUser?.email,  //L   
                service: sample.invoice?.serviceId,        //M
                invoice: sample.invoice?.invoiceNum,     //N
                abCompName: sample.antibody?.company?.name,     //O
                abCatNum: sample.antibody?.catalogNumber,      //P
                abLotNum: sample.antibody?.lotNumber,     //Q
                abNotes: sample.antibody?.note,       //R
                abClonal: sample.antibody?.clonal,       //S
                abAnimal: sample.antibody?.abHost,      //T
                ig: sample.antibody?.igType,             //U
                antigen: sample.antibody?.immunogene,        //V
                ulSampleSent: (abNotes && abNotes.containsKey("Volume Sent (ul)")) ? abNotes["Volume Sent (ul)"] : "",   //W
                abConc: sample.antibody?.concentration,         //X 
                amountUseUg: (abNotes && abNotes.containsKey("Usage Per ChIP (ug)")) ? abNotes["Usage Per ChIP (ug)"] : "",    //Y
                amountUseUl: (abNotes && abNotes.containsKey("Usage Per ChIP (ul)")) ? abNotes["Usage Per ChIP (ul)"] : "",   //Z
                emptyAA: "",              //AA
                emptyAB: "",              //AB
                emptyAC: "",      //AC
                samplePrepUser: sample.cellSource?.prepUser?.fullName, //AD
                genus: sample.cellSource?.strain?.species?.genusName,         //AE
                species: sample.cellSource?.strain?.species?.name,       //AF
                strain: sample.cellSource?.strain?.name,         //AG
                parentStrain: sample.cellSource?.strain?.parent?.name,   //AH        
                genotype: sample.cellSource?.strain?.genotype,       //AI
                mutation: sample.cellSource?.strain?.geneticModification,       //AJ    
                growthMedia: sample.growthMedia?.name,    //AK    
                perturbation1: perturbation1,   //AL
                perturbation2: perturbation2,  //AM
                targetType: sample.target?.targetType?.name, // changed  //AN
                emptyAO: "",            //AO
                emptyAP: "",           //AP
                sampleId: sample.naturalId,       //AQ
                bioRep1SampleId: (note && note.containsKey("bioRep1")) ? note.bioRep1: "",            //AR
                bioRep2SampleId: (note && note.containsKey("bioRep2")) ? note.bioRep2: "",             //AS
                sampleNotes:  (note && note.containsKey("note")) ? note.note: "",      //AT
                nTag: sample.target?.nTermTag,          //AU    
                target: sample.target?.name,         //AV
                cTag: sample.target?.cTermTag,           //AW
                chromAmount: sample.chromosomeAmount,    //AX
                cellNum: sample.cellNumber,        //AY    
                volume: sample.volume,                    //AZ
                assay: sample.assay?.name,                     //BA
                genomeBuild1: genomes.size() > 0 ? genomes[0] : "", //BB    
                genomeBuild2: genomes.size() > 1 ? genomes[1] : "",   //BC
                genomeBuild3: genomes.size() > 2 ? genomes[2] : "",   //BD      
                emptyBE: "",
                dateReceived: sample.cellSource?.inventory?.dateReceived?.format('yyMMdd'),   //BF
                receivingUser: sample.cellSource?.inventory?.receivingUser?.fullName,   //BG    
                inOrExternal: sample.cellSource?.inventory?.sourceType?.name()?.take(1),          //BH
                emptyBI: "",
                inventoryNotes: sample.cellSource?.inventory?.notes,      //BJ
                chipUser: sample.prtclInstSummary?.user?.fullName,             //BK
                emptyBL: "",              //BL
                chipDate: sample.prtclInstSummary?.startTime?.format('yyMMdd'),               //BM
                emptyBN: "",
                protocolVersion: sample.prtclInstSummary?.protocol?.protocolVersion,       //BO
                techRep: "",                 //BP
                requestedTagNum: sample.requestedTagNumber,         //BQ
                emptyBR: "",
                emptyBS: "",
                emptyBT: "",
                emptyBU: "",
                emptyBV: "",
                emptyBW: "",
                resin: (prtclNote && prtclNote.containsKey("Resin")) ? prtclNote.Resin : "",          //BX
                pool: sampleInRun?.pool,           //BY
                volToPool: sampleInRun?.volumeToPool,     //BZ
                poolDate: sampleInRun?.poolDate?.format('yyMMdd'),       //CA
                PCRCycle: (prtclNote && prtclNote.containsKey("PCR Cycle")) ? prtclNote["PCR Cycle"] : "",        //CB
                quibitReading: (poolNote && poolNote.containsKey("quibitReading")) ? poolNote["quibitReading"] : "",  //CC
                quibitDilution: (poolNote && poolNote.containsKey("quibitDilution")) ? poolNote["quibitDilution"] : "", //CD
                concentration: (poolNote && poolNote.containsKey("concentration")) ? poolNote["concentration"] : "",  //CE
                poolDilution: "",   //CF
                emptyCG: "",
                seqRepNum: "",      //CH
                seqRepId: "",       //CI
                rd1Start: (readPositions && readPositions.containsKey("rd1")) ? readPositions.rd1[0] : "",       //CJ
                rd1End: (readPositions && readPositions.containsKey("rd1")) ? readPositions.rd1[1] : "",         //CK
                indexStart: (readPositions && readPositions.containsKey("index1")) ? readPositions.index1.join(",") : "",     //CL
                indexEnd: (readPositions && readPositions.containsKey("index2")) ? readPositions.index2.join(",") : "",        //CM
                rd2Start: (readPositions && readPositions.containsKey("rd2")) ? readPositions.rd2[0] : "",       //CN
                rd2End: (readPositions && readPositions.containsKey("rd2")) ? readPositions.rd2[1] : "",         //CO
                emptyCP: "",              //CP
                runStr: platform + run.runNum,         //CQ
                emptyCR: "",
                emptyCS: "",
                emptyCT: "",
                emptyCU: "",
                userStr: run.user?.fullName,        //CV
                dateStr: sample.date?.format('yyMMdd'),       //CW
                fcidStr: run.fcId,       //CX
                emptyCY: "",
                emptyCZ: "",
                emptyDA: "",
                emptyDB: "",
                emptyDC: "",
                emptyDD: "",
                emptyDE: "",
                emptyDF: "", 
                emptyDG: "",
                emptyDH: "",
                emptyDI: "",
                indexStr: sample.sequenceIndicesIdString    //DJ
            ]
        }
        return [sampleExports: sampleExports, laneExports: laneExports]
        
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
    
    def getNamedLaneData(String[] data) {
	    [libraryPoolArchiveId: data[0],         //A       
	     libraryVolume: getFloat(data[1]),                //B
         libraryStock: getFloat(data[2]),                 //C
         libraryStdDev: getFloat(data[3]),                //D
         pctLibraryStdDev: getFloat(data[4]),             //E
         qPcrDateStr: getDate(data[5]),                      //F
         technicianName: getUser(data[6]),                   //G
         // instrument: data[7],//H
         cycles: data[8],                       //I
         srOrPe: data[9],                       //J
         seqCtrl: data[10],                     //K
         pcrCycles: getInteger(data[11]),                   //L
         qubitConc: getFloat(data[12]),                   //M
         qPcrConc: getFloat(data[13]),                    //N
         libraryLoadedPm: getFloat(data[14]),             //O
         phiXLoaded: getFloat(data[15]),                   //P
         libraryLoadedFmol: getFloat(data[16]),           //Q
         notes: data[17],                       //R
         runNum: getInteger(data[18]),                         //S
         // data[19],//T
         // positiondata[20],//U
         //data[21],//V
         //data[22],                      //W
         //data[23],//X
         //data[24],//Y
         //data[25],//Z
         clusterNum: getFloat(data[26]),                  //AA
         readPf: getFloat(data[27]),                      //AB
         pctPf: getFloat(data[28]),                       //AC
         pctQ30: getFloat(data[29]),                      //AD
         qidx: getFloat(data[30]),                        //AE
         totalReads: getFloat(data[31]),                  //AF
         unmatchedIndices: getFloat(data[32]),            //AG
         pctUnmatchedIndices: getFloat(data[33]),         //AH
         pctAlignedToPhiX: getFloat(data[34]),            //AI
         //data[35],//AJ
         ]
    }
}
