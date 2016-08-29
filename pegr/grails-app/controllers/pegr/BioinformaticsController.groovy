package pegr

class BioinformaticsController {
    def index() {
        if (!params.sort) {
            params.sort = "species"
            params.order = "asc"
        }
        def genomes = Genome.list(params)
        [genomes: genomes]
    }
    
    def features() {
        render "Comming soon..."
    }
}