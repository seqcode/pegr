package pegr
import pegr.DataMigrate

def filename = "files/samples.csv"
def startLine = 4981
def endLine = 4991

def dataMigrate = new DataMigrate()
dataMigrate.migrate(filename, startLine, endLine)