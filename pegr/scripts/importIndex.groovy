package pegr

def filename = "files/indices.csv"
def startLine = 1
def endLine = 96
def version = "A"

def dataMigrate = new ImportIndexService()
dataMigrate.migrate(filename, version, DictionaryStatus.Y, startLine, endLine)

