package pegr

class HelpController {
    def index() {
        
    }
    
    def sequenceIndexHelp() {
        def allIndices = SequenceIndex.findAllByStatus(DictionaryStatus.Y).groupBy({ it -> it.indexVersion })
        [allIndices: allIndices]
    }
    
    def assayHelp() {
        def assays = Assay.findAll().sort { it.name }
        [assays: assays]
    }
    
    def genomesHelp() {
        if (!params.sort) {
            params.sort = "species.genusName"
            params.order = "asc"
        }
        def genomes = Genome.list(params)
        [genomes: genomes]
    }
    
    def featuresHelp() {
        render "Comming soon..."
    }
    
    def bioinformaticsApiHelp() {
        
    }
}