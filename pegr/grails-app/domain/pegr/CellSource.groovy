package pegr

class CellSource {
	
	User providerUser
	Lab providerLab
	String biologicalSourceId
	Strain strain
	String note	
    GrowthMedia growthMedia
    String age
    Sex sex
    Tissue tissue
    Histology histology
    
	String toString() {
		strain
	}
	
    static constraints = {
        providerUser nullable: true
		providerLab nullable: true
		biologicalSourceId nullable: true, blank: true
		note nullable: true, blank: true
        growthMedia nullable: true
        age nullable: true
        sex nullable: true
        tissue nullable: true
        histology nullable: true
    }
}
