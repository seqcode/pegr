package pegr

class CellSource {
	
	User providerUser
	Lab providerLab
	User chromatinUser
	String biologicalSourceId
	Strain strain
	Date collectionDate
	Sex sex
	String age
	Tissue tissue
	Histology histology
	String note
		
	static hasMany = [treatments: Treatment]
	
    static constraints = {
		biologicalSourceId maxSize: 50, nullable: true
		sex nullable: true
		age nullable: true, maxSize: 30
		tissue nullable: true
		histology nullable: true
		note nullable: true, maxSize: 200
		
    }
}
