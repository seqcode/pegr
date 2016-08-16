package pegr

def walleService = new WalleService()
final String remoteRoot = "/home/nextseq/NSQData_PughLab/"
final String localRoot = "/Users/dus73/temp/runInfo"

(215..209).each {
    def run = SequenceRun.get(it)
    def remotePath = new File(remoteRoot, run.directoryName).getPath()
    // make the local folder to temporarily store run infos and 
    // configs if the folder does not exit.

    File localFolder = new File(localRoot, run.directoryName) 
    walleService.generateRunFilesInFolder(run, remotePath, localFolder)
}