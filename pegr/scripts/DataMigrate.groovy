import pegr.*

includeTargets << grailsScript('_GrailsBootstrap')

target(dataMigrate: "Migrate data from excel sheet to mysql database") {
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
             def data = line.split(",")
             def run = getSequenceRun(data[94], data[0], data[100])
             
             
             
         }
    }

}

setDefaultTarget(dataMigrate)


def getSequenceRun(String run, String lane, string date) {
    def runNum
    def platform    
    if (run.take(1) == "S") {
         runNum = run.substring(1).toInteger()
         platform = SequencingPlatform.findByName("SOLiD")
     } else if (run.take(1) == "G") {
         runNum = run.substring(1).toInteger()
         platform = SequencingPlatform.findByName("Illumina GA")
     } else {
         runNum = run.toInteger() 
         if (runNum < 500){
             platform = SequencingPlatform.findByName("HiSeq 2000")
         } else {
             platform = SequencingPlatform.findByName("NextSeq 500")
         }
     }
     def run = SequenceRun.findByPlatformAndRunNum(platform, runNum)
     if (!run) {                     
         run = new SequenceRun(runNum: runNum, platform: platform)
         if (lane.trim()) {
             run.lane = lane.toInteger()
         }
         date
         fcId
         pool
         
         run.save(flush: true)
     }
     return run
}