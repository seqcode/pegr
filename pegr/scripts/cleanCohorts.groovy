package pegr
import groovy.sql.Sql
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*

/*
def minRun = 550
def maxRun = 637

// import the images
def reportImages = [:]
def filename = "/Users/dus73/csvFiles/attached_images.csv"

def startLine = 1
def endLine = 111

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
    reportImages[rawdata[0]]= [sonication: rawdata[1]+".png", gel: rawdata[2]+".png"]
}

def dataSource = grailsApplication.mainContext.getBean('dataSource')
def sql = new Sql(dataSource)
(minRun..maxRun).each { runNum ->
    def pegrRun = SequenceRun.findByRunNum(runNum)
    def cmd = "select RUN_NAME, UNIQ_ID, SUMMARY_REPORT from pughlab.PughLabSequencingRunSampleInfo where run_name = ?"
    def sampleRows = sql.rows(cmd, [runNum.toString()])
    def reports = sampleRows.groupBy({ sample -> sample.SUMMARY_REPORT})
    reports.each { reportName, samples ->
        def cohort = null
        samples.each { sample ->
            def sourceId = sample.UNIQ_ID[-5..-1]
            def pegrSample = Sample.findBySourceId(sourceId)
            if (pegrSample) {
                def pegrExp = SequencingExperiment.findBySample(pegrSample)
                if (pegrExp) {
                    if (cohort) {
                        if (pegrExp.cohort != cohort) {
                            if (pegrExp.cohort) {
                                def oldProjectSample =  ProjectSamples.findByProjectAndSample(pegrExp.cohort.project, pegrSample)
                                if (oldProjectSample) {
                                    oldProjectSample.delete(failOnError: true)
                                }
                            }
                            new ProjectSamples(project: cohort.project, sample: pegrSample).save(failOnError: true)
                            pegrExp.cohort = cohort
                            pegrExp.save(failOnError: true)
                        } 
                    } else {
                        // parse reportName
                        def reportNameParts = reportName.tokenize("_-")
                        def username = reportNameParts[1]
                        def dateStr = reportNameParts[2]
                        // check the cohort
                        if (pegrExp.cohort) {
                            cohort = pegrExp.cohort
                        } else {                            
                            def project = Project.findByName(reportName)
                            if (!project) {
                                def date 
                                try {
                                    date = new Date().parse("yyMMdd", dateStr)
                                } catch(Exception e) {

                                }
                                if (!date) {
                                    date = pegrRun.date ?: new Date()
                                }
                                def user = User.findByUsername(username)
                                println dateStr
                                println date
                                project = new Project(name: reportName, dateCreated: date, lastUpdated: date)
                                project.save(failOnError: true, flush: true)
                                project.dateCreated = date
                                project.lastUpdated = date
                                project.save()
                                if (user) {
                                    new ProjectUser(project: project, user: user, projectRole: ProjectRole.OWNER).save(failOnError: true)
                                }
                                new ProjectSamples(project: project, sample: pegrSample).save(failOnError: true)
                            }
                            cohort = SequencingCohort.findByProjectAndRun(project, pegrRun)
                            if (!cohort) {
                                cohort = new SequencingCohort(project: project, run: pegrRun)
                                cohort.save(failOnError: true)
                            }                            
                            pegrExp.cohort = cohort
                            pegrExp.save(failOnError: true)
                        }
                        // update cohort images
                        if (reportImages.containsKey(reportName)) {
                            cohort.images = JsonOutput.toJson([gel: [reportImages[reportName].gel], sonication: [reportImages[reportName].sonication]])
                            cohort.save(failOnError: true)
                        }
                        cohort = pegrExp.cohort
                    }
                }
            }
        }
    }
}
*/
    
// new script
def jsonSlurper = new JsonSlurper()
Sample.list().each { sample ->
    if (sample.invoice) {
        def service = sample.invoice.serviceId
        def invoice = sample.invoice.invoiceNum
        
        // project
        def projectName            
        if (invoice && invoice[0].toUpperCase() in ["S", "P", "X"]) {
            projectName = service
        } else {
            projectName = "${service}-${invoice}"
        }        
        
        def projectSample = ProjectSamples.findBySample(sample)
        if (projectSample) {
            if (projectSample.project.name == "Yeast Encode 3.0" || projectSample.project.name.take(3) == "Y3E") {
                projectName = "Yeast Encode 3.0"
            } 
            if (projectSample.project.name != projectName) {
                def note = jsonSlurper.parseText(sample.note)
                if (note.note) {
                    note.note += ". " + projectSample.project.name
                } else {
                    note.note = projectSample.project.name
                }
                sample.note = JsonOutput.toJson(note)
                sample.save(flush: true, failOnError: true)
                def project = Project.findByName(projectName)
                if (project) {
                    projectSample.project = project
                    projectSample.save(flush: true, failOnError: true)
                } else {
                    projectSample.project.description = projectSample.project.name
                    projectSample.project.name = projectName
                    projectSample.project.save(flush: true, failOnError: true)
                }
            } 
        } else {
            def project = Project.findByName(projectName)
            if (!project) {
                project = new Project(name: projectName)  
                project.save(flush: true, failOnError: true)
            } 
            new ProjectSamples(project: project, sample: sample).save(flush: true, failOnError: true)
        }
        
        // cohort
        def project = ProjectSamples.findBySample(sample)?.project
        def exp = SequencingExperiment.findBySample(sample)
        if (exp) {
            def cohortName = "${exp.sequenceRun.id}_${service}-${invoice}"
            if (exp.cohort) {
                if (exp.cohort.name != cohortName) {
                    def cohort = SequencingCohort.findByNameAndProjectAndRun(cohortName, project, exp.sequenceRun)
                    if (cohort) {
                        exp.cohort = cohort
                        exp.save(flush: true, failOnError: true)
                    } else {
                        exp.cohort.name = cohortName
                        exp.cohort.project = project
                        exp.cohort.run = exp.sequenceRun
                        exp.cohort.save(flush: true, failOnError: true)
                    }
                }
            } else {
                def cohort = SequencingCohort.findByNameAndProjectAndRun(cohortName, project, exp.sequenceRun)
                if (!cohort) {
                    cohort = new SequencingCohort(project: project, run: exp.sequenceRun, name: cohortName)
                    cohort.save(flush: true, failOnError: true)
                }
                exp.cohort = cohort
                exp.save(flush: true, failOnError: true)
            }

        }
    }
}

SequencingCohort.list().each {cohort ->
    if (cohort.experiments.size() == 0) {
        try {
            cohort.delete()     
        } catch (Exception e) {
            println "Cohort ${cohort.id} cannot be deleted!"
        }
    }
}

Project.list().each {project ->
    def updateName = false
    if (project.samples.size() == 0) {
        if (!ProjectBags.findByProject(project)) {
            try {
                ProjectUser.executeUpdate("delete from ProjectUser where project.id=:projectId", [projectId: project.id])
                project.delete()
            } catch (Exception e) {
                println "Project ${project.id} cannot be deleted!"
            }
        }
    } else {
        // get the project's date
        def date
        def nameParts = project.name.split("-")
        if (nameParts.size() == 2) {
            if (nameParts[1] == "null") {
                updateName = true
            }
            try {
                date = new Date().parse("yyMMdd", nameParts[1])
            } catch(Exception e) {

            }
        }
        if (!date) {
            date = project.samples[0].date
            if (date && updateName) {
                nameParts[1] = date.format("yyMMdd")
                project.name = nameParts.join("-")
                project.cohorts.each { cohort ->
                    def chnParts = cohort.name.split("-_")
                    if (chnParts && chnParts.size() == 3 && chnParts[2] == "null") {
                        chnPatts[2] = nameParts[1]
                        cohort.name = "${chnParts[0]}_${chnParts[1]}-${chnParts[2]}"
                    }
                }
            }
        }
        if (date) {
            project.dateCreated = date
            project.save()
        }
    }
}

SummaryReport.list().each {report ->
    if (!report.cohort) {
        try {
            ReportAlignments.executeUpdate("delete from ReportAlignments where report.id =:reportId", [reportId: report.id])
            report.delete()
        } catch (Exception e) {
            println "Report ${report.id} cannot be deleted!"
        }
    }
}
