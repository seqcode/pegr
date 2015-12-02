package pegr

class CellSource {
	
	User providerUser
	Lab provierLab
	String biologicalSourceId
	Strain strain
	Date collectionDate
	Sex sex
	String age
	Tissue tissue
	Protocol protocol
	GrowthMedia growthMedia
	Treatment treatment
	Histology histology
	String note
		
    static constraints = {
		biologicalSourceId maxSize: 50, nullable: true
		sex nullable: true
		age nullable: true, maxSize: 30
		tissue nullable: true
		histology nullable: true
		note nullable: true, maxSize: 200
		
    }
}
