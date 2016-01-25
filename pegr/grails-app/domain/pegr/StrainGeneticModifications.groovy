package pegr

class StrainGeneticModifications {

    Strain strain
    GeneticModification geneticModification
    
    static constraints = {
        strain unique: 'geneticModification'
    }
    
    static mapping = {
        version: false
    }
}
