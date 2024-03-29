package pegr

class CellSource {
	
	User providerUser
	Lab providerLab
	String biologicalSourceId
	Strain strain
	String note	
    String age
    Sex sex
    Tissue tissue
    Histology histology
    User prepUser
	Inventory inventory
    Item item
    DictionaryStatus status
    
	String toString() {
		strain
	}
	
    static constraints = {
		inventory nullable: true
        providerUser nullable: true
		providerLab nullable: true
		biologicalSourceId nullable: true, blank: true
		note nullable: true, blank: true
        age nullable: true, blank: true
        sex nullable: true
        tissue nullable: true
        histology nullable: true
        prepUser nullable: true
        item nullable: true
        status nullable: true
    }
}
