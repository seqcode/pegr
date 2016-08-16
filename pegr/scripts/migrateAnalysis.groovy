package pegr;

Analysis.list().each { a ->
    a.date = a.alignment.date
    a.save()
    if (!a.alignment.pipeline) {
        a.alignment.pipeline = a.pipeline
        a.alignment.historyId = a.historyId
        a.alignment.workflowId = a.workflowId
        a.alignment.save()
    }
}