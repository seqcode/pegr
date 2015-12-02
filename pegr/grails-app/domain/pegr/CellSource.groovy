package pegr

class CellSource {
	
	User providerUser
	Organization provierLab
	String biologicalSourceId
	Strain strain
	Date collectionDate
	Sex sex
	int age
	Tissue tissue
	Protocol protocol
	GrowthMedia growthMedia
	Treatment treatment
	Histology histology
	String note
		
    static constraints = {
    }
}
