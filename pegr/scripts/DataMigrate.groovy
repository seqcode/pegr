package pegr

def file = new File("files/samplesTest.csv")
def lineNo = 0
def line
file.withReader { reader ->
     while ((line = reader.readLine())!=null) {
         ++lineNo
         if (lineNo < 5) {
             continue
         } else if (lineNo > 7) {
             break
         }
         try {
             String[] rawData = line.split(",")*.trim()
             def data = getNamedData(rawData)
             def run = getSequenceRun(data.runStr, data.laneStr, data.dateStr, data.fcidStr, data.userStr)


                    // def cellSource
                    // def sample 
                    // new SequencingExperiment(sample: sample, 
                     //                         sequenceRun: run,
                    //                         seqId: seqId).save(flush: true)

         }catch(Exception e) {
             println "Error: line ${lineNo}. " + e
             continue
         }
     }
}

def getUser(String username, String fullName, String emailStr, String phoneStr) {
    
}

def getSequenceRun(String runStr, String laneStr, String dateStr, String fcidStr, String userStr) {
    def runNum
    def platform    
    if (runStr.take(1) == "S") {
         runNum = runStr.substring(1).toInteger()
         platform = SequencingPlatform.findByName("SOLiD")
     } else if (runStr.take(1) == "G") {
         runNum = runStr.substring(1).toInteger()
         platform = SequencingPlatform.findByName("Illumina GA")
     } else {
         runNum = runStr.toInteger() 
         if (runNum < 500){
             platform = SequencingPlatform.findByName("HiSeq 2000")
         } else {
             platform = SequencingPlatform.findByName("NextSeq 500")
         }
     }
    
    def exp = SequencingExperiment.findBySeqId(seqId)    
     def run = SequenceRun.findByPlatformAndRunNum(platform, runNum)
     if (!run) {                     
         run = new SequenceRun(runNum: runNum, platform: platform)
         // lane
         if (laneStr.trim()) {
             run.lane = laneStr.toInteger()
         }
         // date
         if (dateStr.size() == 5) {
             dateStr = "0" + dateStr
         }
         
         def date = Date.parse("yyMMdd", dateStr)
         run.dateCreated = date
         
         // fcId
         run.fcId = fcidStr
         
         // user
         def user = User.findByUsername(userStr) {
             
         }   
         
         if (run.save(flush: true)) {
             println runStr
         }
     }
     return run
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
     targetType: data[39],
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