package pegr

class SequenceIndexController {
    def index() {
        def allIndices = SequenceIndex.findAllByStatus(DictionaryStatus.Y).groupBy({ it -> it.indexVersion })
        [allIndices: allIndices]
    }
    
}