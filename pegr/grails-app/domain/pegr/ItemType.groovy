package pegr

class ItemType {

	String name
	ItemTypeCategory category
	String fields
	
	String toString() {
        "${category} - ${name}"
	}
    
    List getFieldList() {
        def fieldList = fields ? fields.tokenize(",")*.trim() : []
        return fieldList
    }
	
    static constraints = {
		name unique: true
		fields nullable: true, blank: true
    }
    
    static mapping = {
        sort "name"
    }
}
