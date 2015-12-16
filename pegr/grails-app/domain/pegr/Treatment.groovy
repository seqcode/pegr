package pegr

class Treatment {
	String name
	String compound
	String unit
	Date time
	
	static hasMany = [cellSources: CellSource]
	static belongsTo = CellSource
	
    static constraints = {
    	name unique: true, size: 2..30
		compound size: 2..30
		unit size: 2..30
	}
}
