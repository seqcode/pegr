package pegr

class CellCultureTreatment {
	String name
	String compound
	String quantity
	String duration
	
	String toString() {
		String s = name + " " + compound
		if (quantity) {
			 s += " " + quantity 
		}
		if (duration) {
			 s += " " + duration
		}
		return s
	}
	
	static hasMany = [cellCultures: CellCulture]
	static belongsTo = CellCulture
	
    static constraints = {
    	name unique: true
		quantity nullable: true, blank: true
		duration nullable: true, blank: true
	}
}
