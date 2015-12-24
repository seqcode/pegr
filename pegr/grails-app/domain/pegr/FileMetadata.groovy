package pegr

class FileMetadata {
	
	String name
	FileType fileType
	SequenceAlignment sequenceAlignment
	int insertionSize
	double standardDeviation
	String md5CheckSum
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name maxSize: 30, unique:'fileType'
		md5CheckSum maxSize: 50
		note nullable: true, blank: true
    }
}
