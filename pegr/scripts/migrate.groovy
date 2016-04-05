package pegr
// import index
def filename = "files/indices.csv"
def startLine = 1
def endLine = 96
def version = "A"
def basicCheck = false

def indexMigrate = new ImportIndexService()
indexMigrate.migrate(filename, version, DictionaryStatus.Y, startLine, endLine)

// import data
filename = "files/samples.csv"
startLine = 4
endLine = 12000

def dataMigrate = new CsvConvertService()
dataMigrate.migrate(filename, RunStatus.COMPLETED, startLine, endLine, basicCheck)

dataMigrate.getAllBioReplicate()

    