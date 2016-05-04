package pegr
import groovy.json.*
    
// import index
def filename = "files/indices.csv"
def startLine = 1
def endLine = 96
def version = "A"
def basicCheck = false

//def indexMigrate = new ImportIndexService()
//indexMigrate.migrate(filename, version, DictionaryStatus.Y, startLine, endLine)

// import data
/*
filename = "files/Peconic_Database.csv"
startLine = 4
endLine = 531//12000

def dataMigrate = new CsvConvertService()
dataMigrate.migrate(filename, RunStatus.COMPLETED, startLine, endLine, basicCheck)
*/
//dataMigrate.getAllBioReplicate()

// migrate antibody notes

def jsonSlurper = new JsonSlurper()
Sample.list().each{ sample ->
    if (sample.antibody?.note) {
        def note = jsonSlurper.parseText(sample.antibody.note)
        def abnoteMap = [:]
        if (note['Volume Sent (ul)']) {
            abnoteMap['Volume Sent (ul)'] = note['Volume Sent (ul)']
        }
        if (note['Usage Per ChIP (ug)']) {
            abnoteMap['Usage Per ChIP (ug)'] = note['Usage Per ChIP (ug)']
        }
        if (note['Usage Per ChIP (ul)']) {
            abnoteMap['Usage Per ChIP (ul)'] = note['Usage Per ChIP (ul)']
        }
        sample.antibodyNotes = JsonOutput.toJson(abnoteMap)
        sample.save()
        if (note['Note']) {
            sample.antibody.note = note['Note']
            sample.antibody.save()
        }
    }
    
}