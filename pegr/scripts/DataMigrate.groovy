package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*

def springSecurityService	
def file = new FileReader("files/samples.csv")
def startLine = 305
def endLine = 307
def lineNo = 0

CSVReader reader = new CSVReader(file);
String [] rawData;
while ((rawData = reader.readNext()) != null) {
    ++lineNo
    if (lineNo < startLine) {
        continue
    } else if (lineNo > endLine) {
        break
    }
    try {
        rawData.each{ 
            if(it == "-" || it == "." || it == "?" || it == "Not applicable" || it == "not applicable" || it == "None") 
                it = ""
         }

         def data = getNamedData(rawData)

         def cellProvider = getUser(data.senderNameStr, data.senderEmail, data.senderPhone)
         def cellReceiver = getUser(data.receiverName, data.receiverEmail, null)

         def project = getProject(data.projectName, data.projectUser, data.projectUserEmail)

         def invoice = getInvoice(data.service, data.invoice)

         def target = getTarget(data.target, data.targetType, data.nTag, data.cTag)
        
         def antibody = getAntibody(data.abCompName, data.abCatNum, data.abLotNum, data.abNotes, data.abClonal, data.abAnimal, data.ig, data.antigen, data.ulSampleSent, data.abConc, data.amountUserUg, data.amountUserUl, data.abName, target)

         def strain = getStrain(data.genus, data.species, data.strain, data.parentStrain, data.genotype, data.mutation)
        
         def growthMedia = getGrowthMedia(data.growthMedia, strain?.species)
        
         def cellSource = getCellSource(data.samplePrepUser, growthMedia, data.sampleNotes, strain, cellProvider)
        
         addTreatment(cellSource, data.perturbation1)
         addTreatment(cellSource, data.perturbation2)
        
         
        
         getBioReplicate(data.bioRep)
        
         def seqExp = getSequenceRun(data.runStr, data.laneStr, data.dateStr, data.fcidStr, data.userStr, data.indexIdStr)

    }catch(Exception e) {
        log.error("Error: line ${lineNo}. " + e)
        continue
    }

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
            def names = userStr.split(",")*.trim()
            username = "" 
            names.each{username += it[0]}
            int i = 100
            while(User.findByUsername(username + i)) {
                ++i
            }
            username += i
        }
        
        password = springSecurityService.encodePassword(userStr)
            
        user = new User(username: username, password: password, fullName: userStr, email: emailStr, phone: phoneStr).save(flush:true)
    }
    return user
}

def getProject(String projectName, String projectUser, String projectUserEmail) {
    if (pprojectName == "") {
        return null
    }
    def project = Project.findByName(projectName)
    if (!project) {
        project = new Project(name: projectName).save(flush: true)
    }
    def user = getUser(projectUser, projectUserEmail, null)
    if (user && project) {
        new ProjectUser(project: project, user: user, projectRole: ProjectRole.OWNER).save(flush: true)
    }
    
    return project
}

def getInvoice(String service, String invoiceNum) {
    if (service == "" && invoiceNum == "") {
        return null
    }
    
    def invoice = Invoice.findBySericeIdAndInvoiceNum(service, invoiceNum)
    if (!invoice) {
        def date = getDate(invoiceNum)
        invoice = new Invoice(serviceId: service, invoiceNum: invoiceNum, date: date).save(flush: true)
    } 
    return invoice
}

def getAntibody(String abCompName, String abCatNum, String abLotNum, String abNotes, String abClonal, String abAnimal, String ig, String antigen, String ulSampleSent, String abConc, String amountUseUg, String amountUseUl, String abName) {
    def name = "${abCompName}-${abCatNum}-${abLotNum}-${abClonal}-${abAnimal}-${ig}-${antigen}-${abConc}"
    if(name=="-------") {
        return null
    }
    def antibody = Antibody.findByName(name)
    if (!antibody) {
        def company = getCompany(abCompName)
        def abHost = getAbHost(abAnimal)
        def target = getTarget(targetStr, targetTypeStr)
        def clonal = getClonal(abClonal)
        def igType = getIgType(ig)
        def concentration = getFloat(abConc)
        
        def noteMap = [:]
        if (abNotes != "") {
            noteMap['note'] = abNotes
        }
        if (ulPerSample != "") {
            noteMap['ulPerSample'] = ulPerSample
        }
        if (ugPerChIP != "") {
            noteMap['ugPerChIP'] = ugPerChIP
        }
        if (ulPerChIP != "") {
            noteMap['ulPerChIP'] = ulPerChIP
        }
        
        def notes = JsonOutput.toJson(noteMap)
        
        antibody = new Antibody(company: company, catalogNumber: abCatNum, abHost: abHost, clonal: clonal, concentration: concentration, igType: igType, immunogene: antigen, lotNum: abLotNum, name: name, note: notes).save(flush: true)
        
        return antibody
    }
}

def getStrain(String genus, String speciesStr, String strainStr, String parentStrainStr, String genotypeStr, String mutationStr) {
    if (strainStr == "") {
        return null
    }
    def strain = Strain.findByName(strainStr)
    if (!strain) {
        def species = getSpecies(genus, speciesStr)
        def genotype = getGenotype(genotypeStr, species)
        def parentStrain = Stain.findByName(parentStrainStr)
        strain = new Strain(name: strainStr, species: species, genotype: genotype, parent: parentStrain).save(flush: true)
        def mutation = getMutation(mutationStr)
        if(strain && mutation) {
            new StrainGeneticModifications(strain: strain, geneticModification: mutation).save(flush: true)
        }
    }
    return strain
}

def getMutation(mutationStr) {
    if(mutationStr == "") {
        return null
    }
    def mutation = GeneticModification.findByName(mutationStr)
    if(!mutation) {
        mutation = new GeneticModification(name: mutationStr).save(flush: true)
    }
    return mutation
}

def getSpecies(String genusStr, String speciesStr) {
    if(genusStr=="" && speciesStr == "") {
        return null
    }
    def species = Species.findByName(speciesStr)
    if(!species) {
        species = new Species(name: speciesStr, genusName: genusStr).save(flush: true)
    }
    return species
}

def getGenotype(String genotypeStr, species) {
    if(genotypeStr == "") {
        return null
    }
    def genotype = Genotype.findByName(genotypeStr)
    if(!genotype) {
        genotype = new Genotype(name: genotypeStr, species: species).save(flush: true)
    }
    return genotype
}

def getGrowthMedia(String mediaStr, Species species) {
    if(mediaStr == "") {
        return null
    }
    def media = GrowthMedia.findByName(mediaStr)
    if(!media) {
        media = new GrowthMedia(name: mediaStr, species: species).save(flush: true)
    } else {
        if(media.species != species) {
            media.species = null
        }
    }
    return species
}


def getCellSource( String samplePrepUser, GrowthMedia growthMedia, String sampleNotes, Strain strain, User provider) {
    if (!strain) {
        return null
    }
    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, prepUser: samplePrepUser).save(flush: true)
    return null
}

def addTreatment(CellSource cellSource, String perturbation) {
    if(perturbation == "") {
        return null
    }
    def perturbation = CellSourceTreatment.findByName(perturbation)
    if(!perturbation) {
        perturbation = new CellSourceTreatment(name: perturbation).save(flush: true)
    }
    return mutation
}

def getBioReplicate(String bioRep) {
    
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
    if (clonalStr ilike "%mono%") {
        clonal = MonoPolyClonal.Mono
    } else if (clonalStr ilike "%poly%") {
        clonal = MonoPolyClonal.Poly
    }
    return clonal
}

deg getIgType(String igStr) {
    if (igStr == "") {
        return null
    }
    def igType = IgType.findByName(igStr)
    if (!igType) {
        igType = new IgType(name: igStr).save(flush: true)
    }
    return igType
}

def getTarget(String targetStr, String targetTypeStr, String nTag, String cTag) {
    if ([targetStr, cTag, nTag].every{ it == "" }) {
        return null
    }
    def target = Target.findByNameAndCTermTagAndNTermTag(targetStr, cTag, nTag)
    if (!target) {
        target = new Target(name: targetStr, cTermTag: cTag, nTermTag: nTag).save(flush: true)
    }
    return target
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
        abHost = new AbHost(name: abHostName).save(flush: true)
    }
    return abHost
}

def getCompany(String companyStr) {
    if (company == "") {
        return null
    }    
    def company = Company.findByName(companyStr)
    if(!company) {
        company = new Company(name: companyStr).save(flush: true)
    }
    return company
}

def getSequenceRun(String runStr, String laneStr, String dateStr, String fcidStr, String userStr, String indexIdStr) {
    int runNum
    def platform
    def seqId
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
         run.dateCreated = getDate(dateString)

         // fcId
         run.fcId = fcidStr

         // user
         def user = findUser(userStr) 

         run.save(flush: true)
    }
    
    def seqExp = SequencingExperiment(sequenceRun: run, seqId: seqId)    
    return seqExp
}

def getDate(String dateString) {
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
    return date
}

def findUser(String userStr) {
    def user = User.findByFullnameIlike("%${userStr}%") ?: User.findByUsername(userStr)
    return user
}

def getNamedData(String[] data) {
    [laneStr: data[0],         
     // data[1]
     // data[2]
     indexIdStr: data[3],
     senderNameStr: data[4],
     senderEmail: data[5],
     senderPhone: data[6],
     receiverName: data[7],
     receiverEmail: data[8],
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
     bioRep: data[41],
     // data[42],
     // data[43],
     // data[44],
     sampleNotes: data[45],
     nTag: data[46],
     target: data[47],
     cTag: data[48],     
     chromAmount: data[49],
     cellNum: data[50],
     quantityReceived: data[51],
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
     cellNum: data[69],
     // data[70],
     // data[71],
     chipQubit: data[72],
     // : data[73],
     // : data[74],
     resin: data[75],
     pool: data[76],
     volToPool1: data[77],
     poolDate: data[78],
     PCRCycle: data[79],
     quibitReading: data[80],
     quibitDilution: data[81],
     concentration: data[82],
     poolDilution: data[83],
     volToPool2: data[84],
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