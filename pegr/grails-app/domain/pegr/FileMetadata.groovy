package pegr

class FileMetadata {
	
	String name
	FileType fileType
	SequenceAlignment sequenceAlignment
	Integer insertionSize
	Float standardDeviation
	String md5CheckSum
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique:'fileType'
		note nullable: true, blank: true
    }
}
