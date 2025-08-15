package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class ImportIndexService {
    ImportIndexService() {}
    
    void migrate(String filename, String version, DictionaryStatus status, int startLine, int endLine) {
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
            try {
                def data = getNamedData(rawdata)
                def index = new SequenceIndex(indexId: data.indexId, sequence: data.sequence, status: status).save()
		 	}catch(Exception e) {
		        log.error "Error: line ${lineNo}. " + e
		        continue
		    }   
		}
    }
    
    def getNamedData(String[] data) {
	    [indexId: data[0],         
         sequence: data[1]
        ]
    }
    
}