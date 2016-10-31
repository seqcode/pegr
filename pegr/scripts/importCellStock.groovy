package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader

def filename = "/Users/dus73/temp/pegr_cohort_strains.csv"
def lineNo = 0    
def startLine = 2
def endLine = 47
def file = new FileReader(filename)
CSVReader reader = new CSVReader(file);
String [] rawdata;

while ((rawdata = reader.readNext()) != null) {
    ++lineNo
    if (lineNo < startLine) {
        continue
    } else if (endLine > 0 && lineNo > endLine) {
        break
    }
    try {
        def data = getNamedData(rawdata)
        def species = Species.findByGenusNameAndName(data.genus, data.species)
        def parent = Strain.findBySpeciesAndNameAndGenotypeAndGeneticModification(species, data.parent, null, null)
        if (!parent) {
            parent = new Strain(species: species, name: data.parent)
            parent.save(failOnError: true)
        }
        def strain = Strain.findBySpeciesAndNameAndParentAndGenotypeAndGeneticModification(species, data.strain, parent, null, null)
        if (!strain) {
            strain = new Strain(name: data.strain, 
                                species: species, 
                                parent: parent).save( failOnError: true)
        }
        def cellSource = new CellSource(strain: strain, status: DictionaryStatus.Y).save()
    }catch(Exception e) {
        println "Error: line ${lineNo}. " + e
        continue
    }   
}


def getNamedData(String[] data) {
    [genus: data[0],         
     species: data[1],
     parent: data[2],
     strain: data[3]
    ]
}