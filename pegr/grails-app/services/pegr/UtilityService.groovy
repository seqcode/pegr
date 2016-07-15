package pegr

import groovy.json.*
    
class UtilityException extends RuntimeException {
    String message
}

class UtilityService {
   /** 
    * Helper method for Select2. It converts a collection of strings to a collection 
    * of maps with both id and text to be the string.
    * @param strings a collection of strings
    * @return a collection of maps with both "id" and "text" to be the string.
    */
    def stringToSelect2Data(def strings) {
        return strings.collect {s -> [id: s, text: s]}
    }
    
   /**
    * Helper method for Select2. It converts a collection of objects to a collection
    * of maps with "id" to be the object's ID and "text" to be the object's name.
    * @param objects a collection of objects which must have properties "id" and "name".
    * @return a collection of maps with "id" to be object's ID and "text" to be object's name.  
    */
    def objectToSelect2Data(def objects) {
        return objects.collect {o -> [id: o.id, text: o.name]}
    }
    
   /**
    * Helper method for Select2. It converts a collection of arrays to a collection
    * of maps with "id" to be array's first element, and "text" to be the second.
    * @param arrays a collection of arrays
    * @return a collection of maps
    */
    def arrayToSelect2Data(def arrays) {
        return arrays.collect{a -> [id: a[0], text: a[1]]}
    }
    
   /**
    * Convert String to Float.
    */    
    def getFloat(String s) {
	    def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
	        f = 0
	    }
	    return f
	}
    
   /**
    * Convert String to Long.
    */        
    def getLong(String s) {
        def n
        try {
            n = Long.parseLong(s)
        } catch (Exception e){
            throw new UtilityException(message: "String ${s} cannot be converted to Long!")
        }
        return n
    }
    
   /**
    * Parse String to a set of numbers.
    */     
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
    
   /**
    * Trim input string. If the trimed string is empty, return null.
    * @param orig input string
    * @return trimed string
    */    
    def cleanString(String orig) {
        def s = null
        if (orig) {
            s = orig.trim()
            if (s == "") {
                s = null
            }            
        }
        return s
    }
    
    /*
     * Divide one Long by another Long
     * @param a numerator
     * @param b denominator
     * @return the value of a/b if a and b are not null and b is not 0; otherwise, return null.
     */
    def divide(Long a, Long b) {
        def result
        if (a!= null && b != null && b!=0) {
            result = a / b
        }
        return result
    }
    
    def queryJson(String jsonStr, List keys) {
        def jsonSlurper = new JsonSlurper()
        def jsonMap
        try {
            jsonMap = jsonSlurper.parseText(jsonStr)
        } catch(Exception e) {   
        }
        def result = [:]
        if (jsonMap) {
            keys.each { key ->
                result[key] = jsonMap.containsKey(key) ? jsonMap[key] : null
            }
        } else {
            keys.each { key ->
                result[key] = null
            }
        }        
        return result
    }
}
