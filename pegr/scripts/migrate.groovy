package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 4
def endLine = 12000

def dataMigrate = new csvConvertService()
dataMigrate.migrate(filename, Runstatus.COMPLETED, startLine, endLine)

dataMigrate.getAllBioReplicate()

dataMigrate.updateStrainName() 
    