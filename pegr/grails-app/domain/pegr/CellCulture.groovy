package pegr

class CellCulture {
	
	User providerUser
	Lab providerLab
	String biologicalSourceId
	Strain strain
	String note	
    GrowthMedia growthMedia
    CellCultureStatus status
    ProtocolInstanceBag prtclInstBag
    
	String toString() {
		strain
	}
	
	static hasMany = [cellCultureTreatments: CellCultureTreatment]
	
    static constraints = {
        providerUser nullable: true
		providerLab nullable: true
		biologicalSourceId nullable: true, blank: true
		note nullable: true, blank: true
        growthMedia nullable: true
        prtclInstBag nullable: true
    }
}
