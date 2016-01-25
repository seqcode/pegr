package pegr

class ProjectSamples {
    Project project
    Sample sample
    
    static constraints = {
        project unique: "sample"
    }
    
    static mapping = {
        version false
    }
}
