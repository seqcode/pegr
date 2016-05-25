package pegr

class UtilityService {
    // helper methods for Select2
    def stringToSelect2Data(def strings) {
        return strings.collect {s -> [id: s, text: s]}
    }
    
    def objectToSelect2Data(def objects) {
        return objects.collect {o -> [id: o.id, text: o.name]}
    }
    
    def arrayToSelect2Data(def arrays) {
        return arrays.collect{a -> [id: a[0], text: a[1]]}
    }
    
    // convert String to numeric numbers
    def getFloat(String s) {
	    def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
	        f = 0
	    }
	    return f
	}
}
