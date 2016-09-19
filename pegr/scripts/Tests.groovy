import groovy.json.*
import org.apache.commons.lang.RandomStringUtils
    
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
    map = [name: 'John Doe', age: [42, 24], sampleId: 123]
    def map2 = [name: 'Anna', age: [42, 24], sampleId: 234]
    def input = [map, map2]
    //map['sampleId'] = 123
    def s = JsonOutput.toJson(input)

    println s
    
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText(s)// '{"Note":"IgG coupled to invitrogen Dynabeads"}')
    // def object = objects[0]
    println object[1]["age"]
    
    
    def monoStr = "monolla"
    if(monoStr.contains("mono")) {
        println monoStr
    }

    String charset = (('A'..'Z') + ('0'..'9') ).join()
    Integer length = 32
    String randomString = RandomStringUtils.random(length, charset.toCharArray())
    println randomString
    
    def results = [run: 200, sample: 10000, genome: "hg19", galaxy_tool_id: "xxx", galaxy_workflow_id: "xxx", statistics_category: "more_diagrams", statistics: [totalReads: 10000, adapterCount: 1000], files: [[url: "xxx", type: "png"], [url: "xxx", type: "png"]]]
    def results_json = JsonOutput.toJson(results)
    println results_json
    def results_parsed = jsonSlurper.parseText(results_json)
    println results_parsed.sample
    println JsonOutput.toJson(results_parsed.files)
    println JsonOutput.toJson(results_parsed.statistics)
    
    s ='{"run":2,"sample":2,"genome":"sacCer3_cegr","toolId":"toolxxx","workflowId":"workflowxxx","toolCategory":"Categoryxxx","parameters":"a=1,b=2","statistics":{"numberOfPeaks":10000,"peakMedian":2000,"peakMean":30000,"peakMedianStd":4000,"peakMeanStd":50000,"medianTagSingletons":6000},"datasets":[{"uri":"xxx/xxx/xxx","type":"xxx"}]}'
    results_parsed = jsonSlurper.parseText(s)
    println results_parsed.run
    
}

setDefaultTarget(tests)
