includeTargets << grailsScript("_GrailsInit")

target(tests: "Simple tests") {
    s = "A, B, C  ,"
    String[] data = s.split(",")*.trim()
    println "1.${data[0]};"
    println "2.${data[1]};"
    println "3.${data[2]};"


    date = "091212"
    println "date: " + Date.parse("yyMMdd", date)
}

setDefaultTarget(tests)
