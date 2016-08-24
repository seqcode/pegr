package pegr

class BioinformaticsController {
    def index() {
        def genomes = Genome.list(params)
        [genomes: genomes]
    }
    
    def features() {
        render "Comming soon..."
    }
}