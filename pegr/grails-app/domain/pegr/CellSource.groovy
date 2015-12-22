package pegr

class CellSource {
	
	User providerUser
	Lab providerLab
	String biologicalSourceId
	Strain strain
	Sex sex
	String age
	Tissue tissue
	Histology histology
	String note
		
	static hasMany = [cellSourceTreatments: CellSourceTreatment]
	
    static constraints = {
		biologicalSourceId maxSize: 50, nullable: true, blank: true
		sex nullable: true
		age nullable: true, blank: true, maxSize: 30
		tissue nullable: true
		histology nullable: true
		note nullable: true, blank: true
		providerUser nullable: true
		providerLab nullable: true
    }
}
