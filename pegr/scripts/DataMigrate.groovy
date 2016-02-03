package pegr

def file = new File("files/samplesTest.csv")
def lineNo = 0
def line
file.withReader { reader ->
     while ((line = reader.readLine())!=null) {
         ++lineNo
         if (lineNo < 5) {
             continue
         } else if (lineNo > 10) {
             break
         }
         try {
             def data = line.split(",")
             def run = getSequenceRun(data[94], data[0], data[100])
             def indexId = data[3].toInteger()
             def seqId = run.runNum+indexId
             def exp = SequencingExperiment.findBySeqId(seqId)
             if (!exp) {
                // def cellSource
                // def sample 
                // new SequencingExperiment(sample: sample, 
                 //                         sequenceRun: run,
                //                         seqId: seqId).save(flush: true)
             } 
         }catch(Exception e) {
             println "Error: line ${lineNo}"
             continue
         }
     }
}

def getSequenceRun(String runStr, String lane, String dateStr) {
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
     def run = SequenceRun.findByPlatformAndRunNum(platform, runNum)
     if (!run) {                     
         run = new SequenceRun(runNum: runNum, platform: platform)
         // lane
         if (lane.trim()) {
             run.lane = lane.toInteger()
         }
         // date
         if (dateStr.size() == 5) {
             dateStr = "0" + dateStr
         }
         run.dateCreated = Date.parse("yyMMdd", dateStr)
         
         // fcId
         // user
            
         if (run.save(flush: true)) {
             println runStr
         }
     }
     return run
}
