package pegr
import groovy.json.*

def steps = [] 
def analysis = Analysis.where {alignment.id == 1114}.list(sort: "id")
analysis.each{
    steps.push([it.stepId, it.category.substring(7)])
} 

def stepsStr = JsonOutput.toJson(steps)
println stepsStr