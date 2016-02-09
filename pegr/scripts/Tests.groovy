import groovy.json.*
    
includeTargets << grailsScript("_GrailsInit")

target(tests: "Simple tests") {
    String str = "1"
    str = "0" + str
    println str
    
    String c = "A"
    println c.toLowerCase()
    
    String name = "Frank, B.Pugh"
    def names = name.split(",|\\.")
    println names[0] + names[1] + names[2]
    
    def phone = "(814)000-1234"
    phone =  phone.replaceAll("\\p{P}","");
    println phone
    
    s = 'A,, C  ,- '
    String[] rawdata = s.split(",")
    def data = new String[rawdata.size()]
    rawdata.eachWithIndex{ d, idx -> 
        def td = d.trim()
		if(td == "-" || td == "." || td == "?" || td == "Not applicable" || td == "not applicable" || td == "None") {
		    data[idx] = ""
        }else {
            data[idx] = td
        }
     }
    println "1.${data[0]};"
    println "2.${data[1]};"
    println "3.${data[2]};"
    println "4.${data[3]};"

    def emailStr = "dus3@psu.edu"
    def at = emailStr.indexOf('@')
    if (at != -1) {
        println emailStr[0..<at]
    }

    date = "2010.05.10"
    println "date: " + Date.parse("yyyy.MM.dd", date)
    
    // println Float.parseFloat("")
    map = [name: 'John Doe', age: [42, 24]]
    map['sampleId'] = "123"
    def s = JsonOutput.toJson(map)

    println s
    
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText(s)
    println object.name
    
    def monoStr = "monolla"
    if(monoStr.contains("mono")) {
        println monoStr
    }
}

setDefaultTarget(tests)
