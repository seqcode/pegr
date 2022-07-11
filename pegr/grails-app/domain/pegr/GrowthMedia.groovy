package pegr

class GrowthMedia {

    String name
    DictionaryStatus status
    
    String toString() {
        name
    }
    
    static constraints = {
        name unique: true
        status nullable: true
    }
    
    static mapping = {
        sort "name"
    }
}
