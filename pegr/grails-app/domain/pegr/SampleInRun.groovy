package pegr

class SampleInRun {

    Date poolDate
	Sample sample
    SequenceRun run
	String pool
    String params
	Float volumeToPool
	
    static constraints = {
        poolDate nullable: true
        sample unique: 'run'
        pool nullable: true, blank: true
        params nullable: true, blank: true
        volumeToPool nullable: true
    }
}
