package pegr
import groovy.json.*

def utilityService = new UtilityService()
def path = 'localhost:8080/pegr/api/stats?apiKey='
def alignmentId = 1
def alignment = SequenceAlignment.get(alignmentId)

// create new post requests
Analysis.findAllByAlignment(alignment).each { analysis ->
    def statistics = utilityService.parseJson(analysis.statistics)
    def datasets = utilityService.parseJson(analysis.datasets)
    def parameters = utilityService.parseJson(analysis.parameters)
        
    def body = ["run": 1, 
            "sample": 1, 
            "genome": "sacCer3_cegr", 
            "workflowId": "b266c9aed69b2935", 
            "historyId": "58d3202e10", // new
            "toolCategory": analysis.category, 
            "toolId": analysis.tool,
            "workflowStepId": analysis.stepId, 
            "userEmail": "cegr@psu.edu", 
            "statistics": statistics,
            "datasets": datasets,
            "parameters": parameters
           ]

    def data = JsonOutput.toJson(body)
    println "curl -X POST -H 'Content-Type: application/json' -d \'${data}\' ${path}"
}

/*
def newAlignId = 315
def newAlignment = SequenceAlignment.get(newAlignId)
def oldAnalysisList = Analysis.findAllByAlignment(alignment)
def newAnalysisList = Analysis.findAllByAlignment(newAlignment)
oldAnalysisList.each { oldAnalysis ->
    def newAnalysis = newAnalysisList.find { it.stepId == oldAnalysis.stepId }
    if (!newAnalysis) {
        println "${oldAnalysis.stepId} not found!"
    } 
    if(newAnalysis.category != oldAnalysis.category){
        println "${oldAnalysis.stepId} Error Category!"
    }    
    if (!compareJson(oldAnalysis.parameters, newAnalysis.parameters)) {
        println "${oldAnalysis.stepId} Error Parameter"       
    }
    
   if (!compareJsonList(newAnalysis.statistics, oldAnalysis.statistics)) {
       println "${oldAnalysis.stepId} Error Statistics"  
   }
   if (!compareJsonList(newAnalysis.datasets, oldAnalysis.datasets)) {
       println "${oldAnalysis.stepId} Error Datasets"  
   }

}

*/
def compareJson(String a, String b) {
    if (!b) {
        return false
    }
    def utilityService = new UtilityService()
    def ja = utilityService.parseJson(a)
    def jb = utilityService.parseJson(b)
    ja.each { key, value ->
        if (!jb.containsKey(key) || jb[key] != value) {
            return false
        }
    }
    return true
}
       
def compareJsonList(String a, String b) {
    if (!b) {
        return false
    }
    try {
        def utilityService = new UtilityService()
        def ja = utilityService.parseJson(a)
        def jb = utilityService.parseJson(b)
        ja.eachWithIndex { amap, id ->
            amap.each{ key, value ->
                if (jb.size() <= id || !jb[id].containsKey(key) || jb[id][key] != value) {
                    return false
                }
            }
        }
        return true
    } catch (Exception e) {
        return false
    }
}
