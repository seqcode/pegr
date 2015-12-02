package pegr

class FileMetadata {
	
	String name
	FileType fileType
	Sample sample
	DataProcessing dataProcessing
	int insertionSize
	double standardDeviation
	String md5CheckSum
	String note
	
    static constraints = {
    }
}
