package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader

def sampleService = new SampleService()
def filename = "/Users/dus73/cellsource.csv"

def startLine = 1
def endLine = 425

def lineNo = 0
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

    def age = rawdata[0] ?: rawdata[0]
    def biological_source_id = rawdata[1] ?: rawdata[1]
    def item = Item.get(rawdata[2])
    def sex = rawdata[3] ?: Sex.get(rawdata[3])
    def tissue = rawdata[4] ?: Tissue.get(rawdata[4])
    def status = rawdata[5] ?: rawdata[5]
    def name = rawdata[6]
    def parent = Strain.get(rawdata[7])
    def species = Species.get(rawdata[8])
    
    def strain = Strain.findByNameAndParentAndSpecies(name, parent, species)
    if (!strain) {
        strain = new Strain(name: name, parent: parent, species: species)
        strain.save(failOnError: true)
    }
    
    def cellSource = new CellSource(age: age, biologicalSourceId: biological_source_id, item: item, sex: sex, tissue: tissue, status: status, strain: strain)
    cellSource.save(failOnError: true)

}
