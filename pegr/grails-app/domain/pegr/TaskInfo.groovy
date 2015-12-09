package pegr

class TaskInfo {
	enum Status {
		End, Queue, Cancel, Running
	}
	
	int taskId
	int scriptId
	User user
	String jobId
	Cluster cluster
	Status status
	Date startTime
	Date endTime
	int numInputFiles
	int numOutputFiles
	String scriptName
	String inputFileName
	CorePipeline corePipeLine
	
    static constraints = {
		scriptName maxSize: 100
		inputFileName maxSize: 255, nullable: true
		corePipeLine nullable: true
    }
}
