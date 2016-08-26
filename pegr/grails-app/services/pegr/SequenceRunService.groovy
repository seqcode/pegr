package pegr
import grails.transaction.Transactional
import groovy.time.TimeCategory

class SequenceRunException extends RuntimeException {
    String message
}

class DuplicatedSampleException extends RuntimeException {
    
}

class SequenceRunService {
    def springSecurityService
    def walleService
    def utilityService
    
    @Transactional
    void save(SequenceRun run) {
        run.lane = 0
        run.user = springSecurityService.currentUser
        run.status = RunStatus.PREP
        if (run.runStats) {
            if (!run.runStats.save(flush: true)) {
                throw new SequenceRunException(message: "Invalid inputs for stats!")
            }
        }
        if(!run.save(flush: true)) {
            throw new SequenceRunException(message: "Invalid inputs for basic information!")
        }
    }
    
    @Transactional
    List addPool(Long poolItemId, Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }       
        run.poolItem = Item.get(poolItemId)
        run.save()
        def samples = fetchSamplesInPool(poolItemId)
        def positions = run.experiments?.getAt(0)?.readPositions
        def readType = run.experiments?.getAt(0)?.readType
        samples.each {
            addSampleToRun(it, run, positions, readType)
        }
    }
    
    @Transactional
    def addSampleToRun(Sample sample, SequenceRun run, String positions, ReadType readType) {
        if (!sample || !run) {
            return
        }
        if (!SequencingExperiment.findBySampleAndSequenceRun(sample, run)) {
            def cohort
            // find the first project that this sample is attached to
            def projectSample = ProjectSamples.createCriteria().list {
                eq "sample", sample 
                maxResults(1)
                order("id", "asc")
            }
            if (projectSample) {
                // find the cohort
                cohort = SequencingCohort.findByProjectAndRun(projectSample.project, run)
                if (!cohort) {
                    cohort = new SequencingCohort(project: projectSample.project, run: run)
                    cohort.save()
                }
            }        
            def experiment = new SequencingExperiment(sample: sample, sequenceRun: run, readPositions: positions, readType: readType, cohort: cohort) 
            experiment.save(failOnError: true)
        }
    }
    
    @Transactional
    void removePool(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            def experiments = run.experiments 
            experiments.each {
                // remove all the sequencingExperiments from the sequence run
                removeExperiment(it.id)
            }
            // remove the pool from the sequence run
            run.poolItem = null
            run.save()
        } else {
            throw new SequenceRunException(message: "Pool not found!")
        }
    }
    
    List fetchSamplesInPool(Long poolItemId) {
        def pool = Item.get(poolItemId)
        if (!pool) {
            throw new SequenceRunException(message: "Pool not found!")
        }
        return pool.samplesInPool
    }
    
    @Transactional
    def addSamplesById(Long runId, String sampleIds) {
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }  
        def positions = run.experiments?.getAt(0)?.readPositions
        def readType = run.experiments?.getAt(0)?.readType
        if (sampleIds == null || sampleIds == "") {
            throw new SequenceRunException(message: "No sample ID!")
        }
        Set ids 
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new SequenceRunException(message: e.message)
        }
        Set unknownSampleIds = []
        ids.each { id ->
            def sample = Sample.get(id)
            if (!sample) {
                unknownSampleIds << id
            } else {
                addSampleToRun(sample, run, positions, readType)
            }
        }
        return unknownSampleIds
    }
    
    @Transactional
    void removeExperiment(Long experimentId) {
        def experiment = SequencingExperiment.get(experimentId)
        if (experiment) {
            // remove the associated alignments
            SequenceAlignment.executeUpdate("delete SequenceAlignment where sequencingExperiment.id = :experimentId", [experimentId: experimentId])
            // remove the experiment itself
            experiment.delete()
        } else {
            throw new SequenceRunException(message: "Experiment not found!")
        }
    }
    
    @Transactional
    void updateSample(String experimentIdStr, List genomeIds) {
        Long experimentId = Long.parseLong(experimentIdStr) 
        def experiment = SequencingExperiment.get(experimentId) 
        if (!experiment) {
            throw new SequenceRunException(message: "Experiment not found!")
        }

        experiment.sample.requestedGenomes = genomeIds.join(',')
        experiment.sample.save()
    }
    
    @Transactional
    void updateRead(Long runId, String readPositions, String readTypeShortName) {
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence Run not found!")
        }
        def readType = ReadType.findByShortName(readTypeShortName)
        run.experiments.each {
            it.readPositions = readPositions
            it.readType = readType
            it.save()
        }
    }
    
    @Transactional
    void run(Long runId) {        
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new SequenceRunException(message: "Sequence run not found!")
        }        
 
        if (run.status == RunStatus.ANALYZING) {
             throw new SequenceRunException(message: "Sequence run has already been submitted!")
        }
        
        // start the run by creating a job on remote server
        run.status = RunStatus.QUEUE
        run.save()
                
        walleService.addToQueue(runId)
    }
    
    def getCalendarTimeString(Date time) {
        return time.format("yyyyMMdd'T'HHmmss'Z'")
    }
    
    byte[] calendarEventAsBytes(Long runId, Date meetingTime) {        
        def now = new Date()
        def startTime = meetingTime
        def endTime 
        use(TimeCategory) {
            endTime = startTime + 1.hour
        }
        
        def nowStr = getCalendarTimeString(now)
        def startTimeStr = getCalendarTimeString(startTime)
        def endTimeStr = getCalendarTimeString(endTime)
        
        def organizer = "Pugh Lab Sequencing"
        def organizerEmail = "dus73@psu.edu"
        
        def ical = """BEGIN:VCALENDAR
PRODID:-
VERSION:2.0
CALSCALE:GREGORIAN
METHOD:REQUEST
BEGIN:VTIMEZONE
TZID:America/New_York
BEGIN:STANDARD
DTSTART:16010101T020000
TZOFFSETTO:-0500
TZOFFSETFROM:-0400
TZNAME:EST
END:STANDARD
BEGIN:DAYLIGHT
DTSTART:16010101T020000
TZOFFSETTO:-0400
TZOFFSETFROM:-0500
TZNAME:EDT
END:DAYLIGHT
END:VTIMEZONE
BEGIN:VEVENT
ORGANIZER;CN=${organizer}:mailto:${organizerEmail}
DTSTART;TZID="America/New_York":${startTimeStr}
DTEND;TZID="America/New_York":${endTimeStr}
TZOFFSETTO:-0500
TZOFFSETFROM:-0400
DTEND:${endTimeStr}
SUMMARY:Sequence Run ${runId} Meeting
UID:PEGR-SEQUENCING-MEETING-${nowStr}
DTSTAMP:${nowStr}
END:VEVENT
END:VCALENDAR
"""

        return ical.getBytes('UTF-8')
    }
}