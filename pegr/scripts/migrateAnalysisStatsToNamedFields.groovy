package pegr
import groovy.json.*

def utilityService = new UtilityService()

def statsService = new AlignmentStatsService(utilityService: utilityService)

def jsonSlurper = new JsonSlurper()

Analysis.list().each { analysis ->
    List statistics = jsonSlurper.parseText(analysis.statistics)
    List datasets = jsonSlurper.parseText(analysis.datasets)
    statsService.saveDatasets(analysis.alignment, analysis.alignment.sequencingExperiment, analysis.category, datasets, statistics)
}
