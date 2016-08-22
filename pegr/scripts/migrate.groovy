package pegr
import groovy.json.*
    
// import index
def filename = "files/SequenceIndex.csv"
def startLine = 1
def endLine = 40
def version = "B"
def basicCheck = false   

/*
def indexMigrate = new ImportIndexService()
indexMigrate.migrate(filename, version, DictionaryStatus.Y, startLine, endLine)
*/

// import data
/*
filename = "files/Peconic_Database.csv"
startLine = 4
endLine = 531//12000

def dataMigrate = new CsvConvertService()
//dataMigrate.migrate(filename, RunStatus.COMPLETED, startLine, endLine, basicCheck)

dataMigrate.getAllBioReplicate()
*/

/*
// migrate antibody notes
def jsonSlurper = new JsonSlurper()
Sample.list().each{ sample ->
    if (sample.antibody?.note && sample.antibody.note[0] == "{") {
        try {
            def note
            try {
                note = jsonSlurper.parseText(sample.antibody.note)
            } catch (Exception e){
            }
            if (note){
                def abnoteMap = [:]
                if (note.containsKey('Volume Sent (ul)')) {
                    abnoteMap['Volume Sent (ul)'] = note['Volume Sent (ul)']
                }
                if (note.containsKey('Usage Per ChIP (ug)')) {
                    abnoteMap['Usage Per ChIP (ug)'] = note['Usage Per ChIP (ug)']
                }
                if (note.containsKey('Usage Per ChIP (ul)')) {
                    abnoteMap['Usage Per ChIP (ul)'] = note['Usage Per ChIP (ul)']
                }
                sample.antibodyNotes = JsonOutput.toJson(abnoteMap)
                sample.save()
            }
        }catch(Exception e) {
            println "Sample ${sample.id} is not migrated!"
        }
    }
}
Antibody.list().each { antibody ->
    if (antibody?.note && antibody.note[0] == "{" ){
       try {
       	   def note
           try {
               note = jsonSlurper.parseText(antibody.note)
           } catch (Exception e) {
           }
           if (note) {
               if (note.containsKey("Note")) {
                   antibody.note = note["Note"]
               } else {
                   antibody.note = null
               }
               antibody.save()
           }
        }catch(Exception e) {
           println "Antibody ${antibody.id} is not cleaned!"
        }
    }
}
*/

def peStepIds = Analysis.where {alignment.id == 1}.collect {it.stepId}.unique()
def srStepIds = Analysis.where {alignment.id == 74}.collect {it.stepId}.unique()
def s = [PE: peStepIds, SR: srStepIds]
new Chores(name: "PipelineSteps", value: JsonOutput.toJson(s)).save()

