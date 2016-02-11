package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 4
def endLine = 12000

def dataMigrate = new DataMigrate()
dataMigrate.migrate(filename, startLine, endLine)
