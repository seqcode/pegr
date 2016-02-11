import groovy.json.*
    
includeTargets << grailsScript("_GrailsInit")

target(tests: "Simple tests") {
    String s10 = "??? no-tag"
    String s11 =s10.replaceAll("\\?", "")
    println s11
    
    String phone1 = "+1 00"
    String phone2= phone1.replaceAll(/[ +]/, "")
    println phone2
    
    String estr = "9.00E10"
    println new BigDecimal(estr).toPlainString(); 
    
    String str = "1"
    str = "0" + str
    println str
    
    String a = "Messi  Leo"
    def userStr = a.split()*.toLowerCase()*.capitalize()
    def b = [userStr[-1], userStr[0]].join(', ')
    println b
    
    String c = "A"
    println c.toLowerCase()
    
    String protocolVersion = "CT"
    String v = protocolVersion.find(/[\d.]+/)
    if (v) {
        def first = v.indexOf(".")
        if(first == -1) {
            v = v + ".0"
        } else if (first == 0) {
            v = "0" + v
        } 
        first = v.indexOf(".")
        def v2 = v.substring(first+1)
        def second = v2.indexOf(".")
        if (second == -1) {
            v = v + ".0"
        }else if (second == v2.length() - 1) {
            v = v + "0"
        }
        println "v: ${v}"
    }
    
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
