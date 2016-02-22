package pegr

class GrowthMedia {

    String name
    Species species
    DictionaryStatus status
    
    String toString() {
        name
    }
    
    static constraints = {
        name unique: true
        species nullable: true
        status nullable: true
    }
}
