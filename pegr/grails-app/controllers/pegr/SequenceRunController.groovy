package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import groovy.time.TimeCategory
import groovy.json.*
import grails.converters.*
import grails.util.Holders
import pl.touk.excel.export.WebXlsxExporter
import pl.touk.excel.export.XlsxExporter

import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException

class SequenceRunController {
    def springSecurityService
    def sequenceRunService
    def qfileService
    def ngsRepoService
    def reportService
    def utilityService
    def sampleService

    // list incomplete runs
    def index(Integer max, String str, String status){
        def c = SequenceRun.createCriteria()
        def listParams = [
                max: params.max ?: 50,
                sort: params.sort ?: "id",
                order: params.order ?: "desc",
                offset: params.offset ?: 0
            ]
        def runs
        if (status) {
            runs = c.list(listParams) {
                eq "status", (status as RunStatus)
            }
        } else if (str) {
            runs = c.list(listParams) {
                or {
                    if (str.isInteger()) {
                        eq "id", Long.parseLong(str)
                    }

                    ilike "runName", "%${str}%"
                    
                    ilike "fcId", "%${str}%"

                    ilike "directoryName", "%${str}%"
                    user {
                        ilike "username", "%${str}%"
                    }
                    platform {
                        ilike "name", "%${str}%"
                    }
                }
            }
        } else {
            runs = SequenceRun.list(listParams)
        }
        [runs: runs, str: str]
    }

    def show(Long id) {
        def run = SequenceRun.get(id)

        if (run) {
            def cohorts = run.cohorts
            def cohortUserList = []
            def isCohortUser = false
            def read = null
            if (run.experiments.size() > 0) {
                def jsonSlurper = new JsonSlurper()
                read = utilityService.parseJson(run.experiments[0].readPositions)
                if (read) {
                    read.readType = run.experiments[0].readType
                }
            }
            
            def editable = sequenceRunService.checkEditable(run)

            [run: run, read: read, editable: editable]
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }

    def editRead(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            def read = null
            if (run.experiments.getAt(0)?.readPositions) {
                def jsonSlurper = new JsonSlurper()
                read = jsonSlurper.parseText(run.experiments[0].readPositions)
                read.readType = run.experiments.getAt(0)?.readType
            }
            def indexType = read?.containsKey("index") ? "single" : "duo"
            [run: run, read: read, indexType: indexType]
        } else {
            flash.message = "Please add samples first!"
            redirect(action: "show", id: runId)
        }
    }

    def updateRead(Long runId, String indexType, String readType) {
        def readPositions
        def posMap
        if (indexType == "single") {
            posMap = [
                rd1: [params.rd1Start, params.rd1End],
                index: [params.indexStart, params.indexEnd],
                rd2: [params.rd2Start, params.rd2End]]

        } else {
            posMap = [
                rd1: [params.rd1Start, params.rd1End],
                index1: [params.index1Start, params.index1End],
                index2: [params.index2Start, params.index2End]]
        }
        if (readType == "PE") {
            posMap.rd2 = [params.rd2Start, params.rd2End]
        }
        readPositions = JsonOutput.toJson(posMap)
        try {
            sequenceRunService.updateRead(runId, readPositions, readType)
            flash.message = "Success updating the read type and read positions!"
        } catch(SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }

    def create() {

    }

    def save() {
        def run = new SequenceRun(params)
        try {
            sequenceRunService.save(run)
            flash.message = "New sequence run created!"
            redirect(action: "show", params: [id: run.id])
        }catch(SequenceRunException e) {
            request.message = e.message
            render(view: "create", model: [run:run])
        }
    }

    def editInfo(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            [run: run]
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }

    def update(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            try {
                run.properties = params
                if (run.runStats) {
                    run.runStats.properties = params
                } else {
                    run.runStats = new RunStats(params)
                }
                sequenceRunService.save(run)
                redirect(action: "show", id:runId)
            } catch(SequenceRunException e) {
                request.message = e.message
                render(view: "editInfo", model: [run: run])
            }
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }

    def searchPool(Long runId, Long typeId, String barcode) {
        if (request.method == "POST") {
            def poolItem = Item.where {type.id == typeId && barcode == barcode}.find()
            if (poolItem) {
                render(view: "previewPool", model: [runId: runId, poolItem: poolItem])
            } else {
                request.message = "Pool not found!"
                [runId: runId]
            }
        } else {
            [runId: runId]
        }
    }

    def addPool(Long poolItemId, Long runId) {
        try {
            sequenceRunService.addPool(poolItemId, runId)
            flash.message = "Success! All samples have been added to the sequence run."
        } catch (SequenceRunException e) {
            flash.message = e.message
        } catch (Exception e) {
            flash.message = "An unexpected error has occured!"
            log.error e
        }
        redirect(action: "show", params: [id: runId])
    }

    def removePool(Long runId) {
        try {
            sequenceRunService.removePool(runId)
            flash.message = "The master pool has been removed!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }

    def addSamplesById(Long runId, String sampleIds) {
        try {
            def unknownSampleIds = sequenceRunService.addSamplesById(runId, sampleIds)
            if (unknownSampleIds.size() > 0) {
                flash.message = "Unknown Samples ${unknownSampleIds} are not added to the sequence Run!"
            } else {
                flash.message = "Success! All samples have been added to the sequence run."
            }
        } catch(SequenceRunException e) {
            flash.message = e.message
        } catch (Exception e) {
            flash.message = "An unexpected error has occured!"
            log.error e
        }
        redirect(action: "show", params: [id: runId])
    }

    def removeExperiment(Long experimentId, Long runId) {
        try {
            sequenceRunService.removeExperiment(experimentId)
            flash.message = "Sequencing experiment deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }

    def updateSamples(Long runId) {
        def messages = ""
        def expIds = params.list('experimentId')
        expIds.each{
            def genomeIds = params.list("genomes${it}")
            try {
                sequenceRunService.updateSample(it, genomeIds)
            } catch (SequenceRunException e) {
                messages += "<p>e.message</p>"
            }
        }
        if (messages == "") {
            messages = "All genomes added successfully!"
        }
        flash.message = messages
        redirect(action: "show", id: runId)
    }

    def previewRun(Long runId) {
        try {
            def previousRun = ngsRepoService.getPreviousRun()
            def remoteFiles = ngsRepoService.getRemoteFiles()
            def newFolders = ngsRepoService.getNewRunFolders(remoteFiles)
            def queuedRunIds = ngsRepoService.getQueuedRunIds()
            def queuedRuns = []
            queuedRunIds.eachWithIndex { id, n ->
                 def run = SequenceRun.get(Long.parseLong(id))
                queuedRuns << [id: id,
                                 runName: run?.runName,
                                 directoryName: n < newFolders.size() ? newFolders[n] : null]

            }
            def currentRun = [id: runId,
                              runName: SequenceRun.get(runId)?.runName,
                              directoryName: queuedRunIds.size() < newFolders.size() ? newFolders[queuedRunIds.size()] : null]
            def startTime
            use(TimeCategory) {
                def now = new Date()
                startTime = now.clearTime() + 10.hours + 1.week
            }
            [previousRun: previousRun,
             queuedRuns: queuedRuns,
             currentRun: currentRun,
             meetingTime: startTime]
        } catch (NgsRepoException e) {
            flash.message = e.message
            redirect(action: "show", id: runId)
        } catch (Exception e) {
            log.error e
            flash.message = "Error querying the information!"
            redirect(action: "show", id: runId)
        }
    }

    def run(Long runId) {
        try {
            sequenceRunService.run(runId)
            redirect(action: "show", id: runId)
        } catch(SequenceRunException e) {
            flash.message = e.message
            redirect(action: "show", id: runId)
        }
    }

    def upload() {

    }

    def convertXlsx() {
        def filesroot = utilityService.getFilesRoot()
        try {
            def mpf = request.getFile( "file" )
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-5..-1] == ".xlsx") {
                File folder = new File(filesroot, 'QueueFiles');
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File fileDest = new File(folder, filename)
                mpf.transferTo(fileDest)
                
                def filepath = fileDest.getPath()
                def startLine = params.int("startLine")
                if (!startLine) {
                    throw new QfileException(message: "Please enter the start line of the sample sheet!")
                }
                
                def endLine = params.int("endLine")
                if (!endLine) {
                    throw new QfileException(message: "Please enter the end line of the sample sheet!")
                }
                
                def laneLine = params.int("laneLine")
                if (!laneLine) {
                    throw new QfileException(message: "Please enter the line of the lane sheet!")
                }
                
                def csvNames = qfileService.convertXlsxToCsv(folder, fileDest, params.sampleSheet, params.laneSheet)
                
                // check file for potential errors, e.g. unreasonal number of new projects
                def warnings = qfileService.checkFile(csvNames[params.sampleSheet],
                                             startLine,
                                            endLine)
                
                // if warnings found, render confirm page before procedding
                if (warnings.size() > 0) {
                    render(view:"confirmXlsx", model:[filename: filename, sampleSheet: csvNames[params.sampleSheet], laneSheet: csvNames[params.laneSheet], startLine: startLine, endLine: endLine, laneLine: laneLine, warnings: warnings])
                    return
                }
                
                def user = springSecurityService.currentUser
                def basicCheck = true
                def messages = qfileService.migrateXlsx(csvNames[params.sampleSheet],
                                        csvNames[params.laneSheet],
                                          RunStatus.PREP,
                                          startLine,
                                          endLine,
                                          laneLine,
                                          basicCheck
                                         )
                if (messages.size() == 0){
                    flash.messageList = ["The xlsx file has been uploaded!",]
                } else {
                    flash.messageList = messages
                }
            } else {
                flash.messageList = ["Only xlsx files are accepted!"]
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.messageList = ["Error uploading the file! ${e.message}"]
        }

        redirect(action: "index")
    }

    def continueXlsx() {
        try {
            def startLine = params.int("startLine")
            def endLine = params.int("endLine")
            def laneLine = params.int("laneLine")                
            def user = springSecurityService.currentUser
            def basicCheck = true
            def messages = qfileService.migrateXlsx(params.sampleSheet,
                                      params.laneSheet,
                                      RunStatus.PREP,
                                      startLine,
                                      endLine,
                                      laneLine,
                                      basicCheck
                                     )
            if (messages.size() == 0){
                flash.messageList = ["The xlsx file has been uploaded!",]
            } else {
                flash.messageList = messages
            }            
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.messageList = ["Error uploading the file!"]
        }

        redirect(action: "index")
    }
    
    def fetchProjectsAjax(Long runId) {
        // Given sequnece run ID, find all projects related to the sequence run. Return the list of related projects in the format [[project_name, project_name],...] to conform with select2 in view.
        def projects = SequencingCohort.where{ run.id == runId }.collect {it.project?.name}.toList()
        render utilityService.stringToSelect2Data(projects) as JSON
    }

    def updateExperimentCohortAjax(Long runId, Long experimentId, String projectName) {
        sequenceRunService.updateExperimentCohort(runId, experimentId, projectName)
        render ""
    }

    def addProject(Long runId, Long projectId) {
        try {
            sequenceRunService.addProject(runId, projectId)
            flash.message = "Project added."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }

    def removeProject(Long cohortId, Long runId) {
        try {
            sequenceRunService.removeProject(cohortId)
            flash.message = "Project removed."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }

    def deleteProject(Long cohortId, Long runId) {
        try {
            sequenceRunService.deleteProject(cohortId)
            flash.message = "Project deleted."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }

    def uploadCohortImage(Long cohortId, String type) {
        def cohort = SequencingCohort.get(cohortId)
        if (!cohort) {
            render(view: "/404")
            return
        }
        def runId = cohort.run.id
        try {
            def fieldName = "image"
            sequenceRunService.uploadCohortImage((MultipartHttpServletRequest)request, cohort, type, fieldName);
        } catch(SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }

    def removeCohortImageAjax(Long cohortId, String filepath) {
        sequenceRunService.removeCohortImage(cohortId, filepath)
        render ""
        return
    }

    /**
     * download Q-file for the sequence run, 
     * including information about samples and lane.
     * This can be activated when downloading the Queue File
     * @param runId ID of the seuqnce run
     */
    def downloadQueueFile(Long runId) {
        def results = qfileService.exportRun(runId)
        
        def sampleProperties = results.sampleExports.size() > 0 ? results.sampleExports[0].keySet() as List : ["No data!"]
        def laneProperties = results.laneExports.size() > 0 ? results.laneExports[0].keySet() as List : ["No data!"]
        
        def filesroot = utilityService.getFilesRoot()
        def template = new File(filesroot, 'QueueTemplate.xlsx');
        WebXlsxExporter webXlsxExporter = new WebXlsxExporter(template.getPath())
        webXlsxExporter.setWorksheetName("SAMPLE")

        webXlsxExporter.with {
            setResponseHeaders(response, results.filename)
            add(results.sampleExports, sampleProperties )
            sheet('Lane').with {
                add(results.laneExports, laneProperties )
            }
            save(response.outputStream)
        }
            
    }
    
    def downloadRunInfo(String remoteRoot, Long runId) {
        String RUN_INFO_TEMP = "runInfo${runId}"
        def timeout = 300 * 1000

        // generate the run info files
        def run = SequenceRun.get(runId)

        // clean path
        remoteRoot = remoteRoot.trim()

        def remotePath

        try {
            if (!remoteRoot) {
                throw new SequenceRunException(message: "Error! Please check the remote root of Run #${runId}!")
            }

            if (!run.directoryName) {
                throw new SequenceRunException(message:"Error! Please check the directory name of Run #${runId}!")
            }

            try {
                remotePath = new File(remoteRoot, run.directoryName).getPath()
            } catch (Exception e) {
                throw new SequenceRunException(message: "Error! Please check the remote root and the directory name of Run #${runId}!")
            }
        } catch (SequenceRunException e) {
            flash.message = e.message
            redirect(action: "show", id: runId)
            return
        }
        
        def localRoot = utilityService.getFilesRoot()
        def localFolder = new File(localRoot, RUN_INFO_TEMP)

        ngsRepoService.generateRunFilesInFolder(run, remotePath, localFolder)

        // compress the files
        def compressedFilename = "runInfo${runId}.tar.gz"
        def compressedFile = new File(localRoot, compressedFilename)
        def command = "tar -czf ${compressedFile.getPath()} ${RUN_INFO_TEMP}"
        def envVars = []
        def proc = command.execute(envVars, localRoot)
        proc.waitForOrKill(timeout)
        
        try {
            response.setContentType("application/gzip")
            response.setHeader("Content-disposition", "attachment;filename=\"${compressedFilename}\"")
            response.outputStream << compressedFile.getBytes()
            // remove the compressed file
            compressedFile.delete()
            // remove the original files
            command = "rm -R ${localFolder.getPath()}"
            command.execute()
            webRequest.renderView = false
        } catch(Exception e) {
            render "Error!"
        } finally {
            response.getOutputStream().flush()
            response.getOutputStream().close()
        }

    }

    def editQueue() {
        def queue = ngsRepoService.getQueue()
        [previousRunFolder: queue.previousRunFolder, queuedRuns: queue.queuedRuns]
    }

    def updateQueue(String previousRunFolder, String queuedRuns) {
        try {
            ngsRepoService.updateQueue(previousRunFolder.trim(), queuedRuns.trim())
            flash.message = "Queue has been updated!"
        } catch (NgsRepoException e) {
            flash.message = e.message
        }
        redirect(action: "editQueue")
    }

	// axa677-180306: No need for this function since we have written the next one to handle deletion using checkboxes
    def deleteSample(Long sampleId, Long runId) {
        try {
            def sample = Sample.get(sampleId)
            sampleService.delete(sample)
            flash.message = "Sample${sampleId} deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }

    
    def deleteSamples() {    
        def runId = params.long('runId')
        def sampleIds = params.list('sampleId')
        def action = params.actionType
        
        if (action == "delete") {
            try {
                sampleIds.each {
                    def sample = Sample.get(it.toLong())
                    sampleService.delete(sample)
                }
                flash.message = "Selected samples deleted!"
            } catch (SequenceRunException e) {
                flash.message = e.message
            }
        } else if (action == "remove") {
            try {
                sampleIds.each { sampleId ->
                    def experiment = SequencingExperiment.where {sequenceRun.id == runId && sample.id == sampleId.toLong()}.get()
                    if (experiment) {
                        sequenceRunService.removeExperiment(experiment.id)
                    }                    
                }                
                flash.message = "Selected samples removed!"
            } catch (SequenceRunException e) {
                flash.message = e.message
            }
        }
        
        redirect(action: "show", params: [id: runId])
    }


    def delete(Long runId) {
        try {
            sequenceRunService.delete(runId)
            flash.message = "Sequence Run ${runId} has been deleted!"
        } catch(SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    def updateRunNameAjax(Long runId, String name) {
        def result
        try {
            sequenceRunService.updateRunName(runId, name)
            result = [data: name]
        }  catch(SequenceRunException e) {
            result = [error: e.message]
        }
        render result as JSON
    }
    
    def submitSequencingRequest() {
        if (request.method == "POST") {
            URL url = new URL (params.url)
            HttpURLConnection con = (HttpURLConnection)url.openConnection()
            
            con.setRequestMethod("POST")
            con.setRequestProperty("Content-Type", "application/json; utf-8")
            con.setRequestProperty("Accept", "application/json")
            con.setDoOutput(true)
            
            String jsonInputString = params.json
            
            try {
                OutputStream os = con.getOutputStream()
       
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length)
            
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))
                StringBuilder response = new StringBuilder()
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                request.message = response.toString()
            } catch(IOException ioe) {
                request.message = ioe.toString()
            } catch(Exception e) {
                request.message = e.toString()
            }
        }
        render(view: "submitSequencingRequest")
    }

}
