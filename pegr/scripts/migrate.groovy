package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 305
def endLine = 305

def dataMigrate = new DataMigrate()
dataMigrate.migrate(filename, startLine, endLine)