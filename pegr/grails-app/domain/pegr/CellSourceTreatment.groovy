package pegr

class CellSourceTreatment {
	String name
	String compound
	String quantity
	String duration
	
	static hasMany = [cellSources: CellSource]
	static belongsTo = CellSource
	
    static constraints = {
    	name unique: true, size: 2..30
		compound size: 2..30
		quantity maxSize: 20, nullable: true, blank: true
		duration maxSize: 20, nullable: true, blank: true
	}
}
