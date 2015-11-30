package pegr

class CellSource {
	enum Sex {
		MALE, FEMALE, HERMAPHRODITE
	}
	enum Tissue {
		LIVER, BLOOD
	}
	
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
