package pegr;

class ProjectBags {
    Project project
    ProtocolInstanceBag bag
    
    static constraints = {
        project unique: "bag"
    }
    
    static mapping = {
        version false
    }
}