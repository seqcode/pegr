package pegr

def walleService = new WalleService()
final String remoteRoot = "/gpfs/cyberstar/pughhpc/storage/illumina/illuminaNextSeq/NSQData_PughLab/"
final String localRoot = "/Users/danyingshao/tmp/runInfo"

(215..103).each {
    def run = SequenceRun.get(it)
    def remotePath = new File(remoteRoot, run.directoryName).getPath()
    // make the local folder to temporarily store run infos and 
    // configs if the folder does not exit.

    File localFolder = new File(localRoot, run.directoryName) 
    walleService.generateRunFilesInFolder(run, remotePath, localFolder)
}