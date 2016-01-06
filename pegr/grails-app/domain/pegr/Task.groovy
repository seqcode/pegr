package pegr

class Task {
	enum Status {
		End, Queue, Cancel, Running
	}
	
	User user
	String jobId
	ComputingInfrastructure computingInfrastructure
	Status status
	Date startTime
	Date endTime
	String scriptFilePath
	String inputFilePaths
	String outputFilePaths
	CorePipeline corePipeLine
	
    static constraints = {
		inputFilePaths nullable: true
		outputFilePaths nullable: true
		corePipeLine nullable: true
		endTime nullable: true
    }
}
