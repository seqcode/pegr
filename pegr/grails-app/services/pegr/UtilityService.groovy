package pegr
import grails.transaction.Transactional
import groovy.json.*
import groovy.sql.Sql
import org.springframework.web.multipart.MultipartHttpServletRequest 
import org.apache.commons.lang.RandomStringUtils
    
class UtilityException extends RuntimeException {
    String message
}

/**
* Shared services
*/
class UtilityService {
    def dataSource
    def grailsApplication
   /** 
    * Helper method for Select2. It converts a collection of strings to a collection 
    * of maps with both id and text to be the string.
    * @param strings a collection of strings
    * @return a collection of maps with both "id" and "text" to be the string.
    */
    def stringToSelect2Data(def strings) {
        return strings.findAll {it && it.trim() != ""}.collect {s -> [id: s, text: s]}
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
    * @param s string
    * @return float
    */    
    def getFloat(String s) {
	    def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
	    }
	    return f
	}
    
   /**
    * Convert String to Long.
    * @param s string
    * @return long
    */        
    def getLong(String s) {
        def n
        try {
            n = Long.parseLong(s)
        } catch (Exception e){
        }
        return n
    }
    
   /**
    * Parse String to a set of numbers.
    * @param s string
    * @return a set of IDs
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
    
   /**
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
    
   /**
    * Parse a json string. 
    * @param s the string to parse
    * @return pared json. Returns null if parsing failed.
    */
    def parseJson(String s) {
        def jsonSlurper = new JsonSlurper()
        def json
        try {
            json = jsonSlurper.parseText(s)
        } catch(Exception e) {   
        }
        return json
    }
    
   /**
    * Given a list of keys, query the corresponding values from a Json string.
    * @param jsonStr the Json string to query from
    * @param keys the list of given keys
    * @return a map with given keys and queried values
    */
    def queryJson(String jsonStr, List keys) {
        def json = parseJson(jsonStr)
        def result = [:]
        keys.each { key ->
            result[key] = null
        }
        if (json) {
            if (json instanceof List) {
                json.each { jsonMap ->
                    queryJson(jsonMap, keys, result)
                }
            } else {
                queryJson(json, keys, result)
            }            
        }       
        return result
    }
    
   /** 
    * Given a key, query its value from a Json string.
    * @param jsonStr the Json string
    * @param key the given key
    * @return the value of the given key
    */
    def queryJson(String jsonStr, String key) {
        def keys = [key]
        def results = queryJson(jsonStr, keys)
        if (results && results.containsKey(key)) {
            return results[key]
        } else {
            return null
        }
    }
    
   /**
    * Given a list of keys, query the corresponding values from a map.
    * @param jsonMap the map to query from
    * @param keys a list of given keys
    * @param result the container for the result
    */
    def queryJson(Map jsonMap, List keys, Map result) {
        keys.each { key ->
            if (jsonMap.containsKey(key)) {
                result[key] = jsonMap[key]
            }                        
        }
    }
    
   /**
    * Execute command and return the consumed string of output. If
    * the process does not finish within given time, kill the process.
    * If the process's exist value is not zero (including the timeout
    * situation), throw an exception.
    * @param command a string of command to be executed
    * @param timeout the max time in milli second allowed to execute.
    * If exceeded, an error will be thorwn.
    * @return a consumed string of output 
    */
    def executeCommand(String command, Long timeout) {
        def output = new StringBuilder()
        def err = new StringBuilder()
        def proc = command.execute()
        proc.consumeProcessOutput(output, err)
        proc.waitForOrKill(timeout)
        
        if (proc.exitValue()){
            // error handling
            log.error "Error executing the command: ${command}. Code ${proc.exitValue()}: ${err}."
            throw new UtilityException(message: "Error executing the command!")
        }        
        return output.toString()
    }
    
   /**
    * Get the root folder to hold files
    * @return the root folder to hold files
    */
    def getFilesRoot() {
        def filesrootStr = grailsApplication.config.filesroot
        File filesroot = new File(filesrootStr);
        if (!filesroot.exists()) {
            filesroot.mkdirs();
        }
        return filesroot
    }
    
   /**
    * Merge rows in MySQL database
    * @param tableName the name of the table where rows will be merged
    * @param fromId the ID to merge from
    * @param toId the ID to merge to
    */
    @Transactional
    def mergeRowsInDb(String tableName, Long fromId, Long toId) {
        if (!tableName || tableName == "") {
            throw new UtilityException(message: "Table not selected!")
        }
        if (!fromId) {
            throw new UtilityException(message: "From ID is missing!")
        }
        if (!toId) {
            throw new UtilityException(message: "To ID is missing!")
        }                  
        
        def sql = new Sql(dataSource)
        try {    
            // check if both the merge from and merge to exist
            def cmd = "select 1 from " + tableName + " where id = ?"
            [fromId, toId].each { id ->
                def objs = sql.rows(cmd, [id])
                if (!objs || objs.size() == 0) {
                    throw new UtilityException(message: "The ${tableName} with ID#${id} is not found!")
                }
            } 
            
            // fetch the tables that have a foreign key to the requested table
            cmd = "select kcu.table_name, kcu.column_name from information_schema.referential_constraints rc inner join information_schema.key_column_usage kcu on rc.constraint_name = kcu.constraint_name and rc.constraint_schema = kcu.constraint_schema where kcu.constraint_schema = 'pegr' AND kcu.REFERENCED_TABLE_NAME = ?"
            def affectedTables = sql.rows(cmd, [tableName]) 

            affectedTables.each { table ->
                // check unique constraints
                cmd = "select 1 from information_schema.key_column_usage kcu inner join information_schema.TABLE_CONSTRAINTS tc on kcu.constraint_name = tc.constraint_name and kcu.table_name = tc.table_name and kcu.table_schema = tc.table_schema where kcu.constraint_schema = 'pegr' and kcu.table_name = ? and kcu.column_name= ? and tc.constraint_type='UNIQUE' and kcu.constraint_schema = 'pegr'"
                def constraints = sql.rows(cmd, [table.table_name, table.column_name])
                if (constraints && constraints.size() > 0 ) {
                    // if there is UNIQUE constraint that prevent changing reference key, throw an error.
                    throw new UtilityException(message:"Database UNIQUE contraint!")
                } else {
                    // change reference key from the fromId to the toId   
                    cmd = "update " + table.table_name + " set " + table.column_name + "= ? where " + table.column_name + " = ?"
                    sql.execute(cmd, [toId, fromId]) 
                }             
            }
            // delete the merge-from row
            cmd = "delete from " + tableName + " where id = ?"
            sql.execute(cmd, [fromId])
            sql.close()
        } catch(UtilityException e) {
            sql.close()
            throw e
        } catch (Exception e) {
            sql.close()
            log.error e
            throw new UtilityException(message: "Error merging ${tableName} from ID#${fromId} to ID#${toId}!")
        }
    }
    
    @Transactional
    def updateForeignKeyInDb(String table, String field, Long fromId, Long toId, Sql sql) {
        def cmd = "update " + table + " set " + field +"_id = ? where " + field + "_id = ?"
        sql.execute(cmd, [toId, fromId])
    }
    
    @Transactional
    def updateLinksInDb(String linkTable, String fieldConstraint, String fieldToMerge, Long fromId, Long toId, Sql sql) {
        def toDelete = []

        sql.eachRow("select id, " + fieldConstraint + "_id from " + linkTable + " where " + fieldToMerge + "_id = ?", [fromId]) { row ->
            def newLink = sql.rows("select 1 from " + linkTable + " where " + fieldConstraint + "_id = ? and " + fieldToMerge + "_id = ?", [row[1],  toId])
            if (!newLink || newLink.size() == 0) {
                sql.execute("update " + linkTable + " set " + fieldToMerge + "_id = ? where id = ?", [toId, row[0]])
            } else {
                toDelete.push(row[0])
            }
        }
 
        toDelete.each {id -> 
            sql.execute("delete from " + linkTable + " where id = ?", [id] )
        }
    }
    
    def upload(MultipartHttpServletRequest mpr, String fileField, List allowedFileTypes, String folderName, Long maxByte) {
        def mpf = mpr.getFile(fileField);
        String fileType = mpf.getContentType();
        def filepath
        if(!mpf?.empty && mpf.size < maxByte && fileType in allowedFileTypes) {  
            def filesroot = getFilesRoot()
            def folder = new File(filesroot, folderName); 
            if (!folder.exists()) { 
                folder.mkdirs(); 
            } 
            def filename =  mpf.getOriginalFilename();
            File fileDest =  new File(folder, filename)
            if (fileDest.exists()) {
                throw new UtilityException(message: "File already exists! You may change the file name and try again.")
            }
            mpf.transferTo(fileDest)
            filepath = folderName + File.separator + filename
        } else {
            throw new UtilityException(message: "Error! Please check the file's format and size.")
        }
        return filepath
    }

    def getRandomString(int length) {
        String charset = (('A'..'Z') + ('a'..'z') + ('0'..'9')).join()
        return RandomStringUtils.random(length, charset.toCharArray())
    }
}
