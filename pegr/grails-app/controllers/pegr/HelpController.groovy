package pegr

class HelpController {
    def index() {
        def allIndices = SequenceIndex.findAllByStatus(DictionaryStatus.Y).groupBy({ it -> it.indexVersion })
        [allIndices: allIndices]
    }
}