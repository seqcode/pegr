package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 302
def endLine = 302

def dataMigrate = new DataMigrate()
dataMigrate.migrate(filename, startLine, endLine)