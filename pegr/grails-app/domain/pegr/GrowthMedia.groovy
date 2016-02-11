package pegr

class GrowthMedia {

    String name
    Species species
    
    String toString() {
        name
    }
    
    static constraints = {
        name unique: true
        species nullable: true
    }
}
