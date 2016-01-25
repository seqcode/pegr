package pegr

class BiologicalReplicateSet {

    Project project
    
    static constraints = {
        project nullable: true
    }
}
