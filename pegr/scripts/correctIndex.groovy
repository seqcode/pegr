package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader

def sampleService = new SampleService()
def filename = "/Users/dus73/csvFiles/Index_Curation_Run164-215.csv"

def startLine = 2
def endLine = 318

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
    def sampleId = rawdata[0]
    def sourceId = rawdata[1]
    def indexStr = rawdata[2]
    def sample  = Sample.get(sampleId)
    if (!sample) {
        log.error "Sample ${sampleId} not found!"
    } else if (sample.sourceId != sourceId) {
        log.error "Source Id ${sourceId} does not match sample ID ${sampleId}"
    } else {
        SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id=?", [sample.id])
        def index = SequenceIndex.findBySequence(indexStr)
        if (!index) {
            index = new SequenceIndex(indexId: 0, sequence: indexStr, indexVersion: "UNKNOWN").save(failOnError: true)
            log.error "new index ${index.sequence}"
        }
        new SampleSequenceIndices(sample: sample, index: index, setId: 1, indexInSet: 1).save(failOnError: true)
    }
}
