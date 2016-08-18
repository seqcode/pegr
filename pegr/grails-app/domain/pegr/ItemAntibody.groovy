package pegr

class ItemAntibody {
    Item item
    Antibody antibody
    
    static mapping = {
        version false
    }
    
    static constraints = {
        item unique: "antibody"
    }
}