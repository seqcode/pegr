package pegr

def utilityService = new UtilityService()

// update Samples' natural ID
Sample.list().each {sample ->
    def note = utilityService.parseJson(sample.note)
    if (note?.sampleId) {
        sample.naturalId = note.sampleId
        sample.save()
    }
}

// migrate lane info
def qfileUploadService = new QfileUploadService()
final int laneLine = 3

(520..632).each { n ->
    def root = "/Users/dus73/temp/csv"
    def filepath = new File(root, "Queue_lane_${n}.csv").getPath()
    
    qfileUploadService.migrateLane(filepath, laneLine, n) 
    println n
}
