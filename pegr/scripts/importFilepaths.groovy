package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader
def filename = "/Users/dus73/csvFiles/filepaths.csv"

def startLine = 2
def endLine = 114

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
    def runNum = rawdata[0]
    def filepath = rawdata[2]
    def run = SequenceRun.findByRunNum(runNum)
    if (!run) {
        // error handling
        log.error "RunNum ${runNum} not found!"
    } else {
        run.directoryName = filepath
        run.save()
    }
}