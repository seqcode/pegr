package pegr

def historyIds = Analysis.executeQuery("select distinct historyId from Analysis")
println "historyIds ${historyIds.size()}"
historyIds.each { historyId ->
    def alignmentIds = Analysis.findByHistoryId(historyId).collect{it.alignment.id}.unique()
    if (alignmentIds && alignmentIds.size() > 1) {
        def mergedAlignment
        alignmentIds.each { alignmentId ->
            def alignment = SequenceAlignment.get(alignmentId)
            if (mergedAlignment) {
                alignment.properties.each {prop, val ->
                    if (prop != "id" && val != null && mergedAlignment[prop] == null) {
                        mergedAlignment[prop] = val
                    }
                }
                // merge
                Analysis.findAllByAlignment(alignment).each {
                    it.alignment = mergedAlignment
                    it.save()
                }
                ReportAlignments.executeUpdate("delete from ReportAlignments where alignment.id = ?", [alignment.id])
                println "delete Alignment ${alignment.id}"
                alignment.delete()
            } else {
                mergedAlignment = alignment
            }
        }
        mergedAlignment.save()
    }
}