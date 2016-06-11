package pegr

class ReplicateSet {

    ReplicateType type
    Project project
    
    static constraints = {
        project nullable: true
    }
}
