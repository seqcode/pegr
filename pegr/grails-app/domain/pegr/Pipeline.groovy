package pegr

class Pipeline {
    String name
    String pipelineVersion
    String note
    
    static constraints = {
        name unique: "pipelineVersion"
        pipelineVersion nullable: true, blank: true
        note nullable: true, blank: true
    }
}