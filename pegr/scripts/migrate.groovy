package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 9451
def endLine = 9600

def dataMigrate = new DataMigrate()
dataMigrate.migrate(filename, startLine, endLine)