package pegr

class Funding {
    String number
    String name
    
    String toString() {
        number + " " + name
    }
    
    static constraints = {
        name unique: "name"
    }
    
}