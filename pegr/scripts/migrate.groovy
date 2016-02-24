package pegr

def filename = "files/samples.csv"
def startLine = 4
def endLine = 12000

def dataMigrate = new CsvConvertService()
dataMigrate.migrate(filename, RunStatus.COMPLETED, startLine, endLine)

dataMigrate.getAllBioReplicate()

    