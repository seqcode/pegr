package pegr;
import groovy.json.*

def utilityService = new UtilityService()
def alignmentStatsService = new AlignmentStatsService()
Analysis.findAllByCategory("output_meme").each { analysis ->
    def datasets = utilityService.parseJson(analysis.datasets)
    def motifCount = alignmentStatsService.getMotifCountFromMeme(datasets)
    analysis.statistics = JsonOutput.toJson([["motifCount":motifCount]])
    analysis.save(failOnError: true)
}

