package pegr

class ItemTypeCategory {
    String name
    ItemTypeSuperCategory superCategory
    
    String toString() {
        name
    }
    
    static constraints = {
        name unique: true
    }
}