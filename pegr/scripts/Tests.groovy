import groovy.json.*
    
includeTargets << grailsScript("_GrailsInit")

target(tests: "Simple tests") {
    s = 'A,, C  ,"Venters, Bryan"'
    String[] data = s.split(",")*.trim()
    println "1.${data[0]};"
    println "2.${data[1]};"
    println "3.${data[2]};"
    println "4.${data[3]};"

    def emailStr = "dus3@psu.edu"
    def at = emailStr.indexOf('@')
    if (at != -1) {
        println emailStr[0..<at]
    }

    date = "091212"
    println "date: " + Date.parse("yyMMdd", date)
    
    // println Float.parseFloat("")
    
    def s = JsonOutput.toJson([name: 'John Doe', age: 42])

    println s
    
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText(s)
    println object.name
}

setDefaultTarget(tests)
