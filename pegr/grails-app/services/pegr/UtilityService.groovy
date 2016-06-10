package pegr

class UtilityException extends RuntimeException {
    String message
}

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
    
    // convert String to Float
    def getFloat(String s) {
	    def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
	        f = 0
	    }
	    return f
	}
    
    // convert String to Long    
    def getLong(String s) {
        def n
        try {
            n = Long.parseLong(s)
        } catch (Exception e){
            throw new UtilityException(message: "String ${s} cannot be converted to Long!")
        }
        return n
    }
    
    // parse String to a set of numbers
    def parseSetOfNumbers(String s) {
        Set ids = []
        if(s == null || s == "") {
            return ids
        }
        s.split(",")*.trim().each{ spanStr ->
            def span = spanStr.split("-")*.trim()
            if (span.size() == 1) {
                ids += getLong(span[0])
            } else if (span.size() == 2) {
                def x1 = getLong(span[0])
                def x2 = getLong(span[1])
                if (x1 > x2) {
                    def y = x1
                    x1 = x2
                    x2 = y
                }
                (x1..x2).each {
                    ids << it
                }
            } else if(span.size() > 2) {
                throw new UtilityException(message: "Error in parsing the string ${s} to numbers!")
            }
        }
        return ids
    }
}
