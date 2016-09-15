def history = "4a17bb24f2f84e0f"
def alignment = SequenceAlignment.findByHistoryId(history)
def alignmentService = new AlignmentStatsService()
def utilityService = new UtilityService()
alignmentService.utilityService = utilityService
Analysis.findAllByAlignment(alignment).each {
    if (it.note && it.note.size()> 100) {
        it.note = it.note[0..100]
        it.save()
    }
        
    def statistics = utilityService.parseJson(it.statistics)
    def datasets = utilityService.parseJson(it.datasets)
        
    alignmentService.processAnalysisNote(it, statistics, datasets)
}
