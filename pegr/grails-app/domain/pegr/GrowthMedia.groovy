package pegr

class GrowthMedia {

    String name
    Species species
    
    String toString() {
        name
    }
    
    static constraints = {
        name unique: true, matches: '^[0-9A-Za-z -]+$'
        species nullable: true
    }
}
