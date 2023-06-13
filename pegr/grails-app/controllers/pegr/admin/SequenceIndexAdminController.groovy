package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.SequenceIndex
import pegr.AdminCategory
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class SequenceIndexAdminController {

    SequenceIndexService sequenceIndexService
    UtilityService utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str) {
            def c = SequenceIndex.createCriteria()
            def listParams = [
                    max: max ?: 25,
                    sort: params.sort ?: "id",
                    order: params.order ?: "desc",
                    offset: params.offset
                ]
            def indexes = c.list(listParams) {
                    or {
                        ilike "indexVersion", "%${str}%"
                        ilike "sequence", "%${str}%"
                        ilike "indexId", "%${str}%"
                    }
                }
            
            respond indexes, model:[sequenceIndexCount: indexes.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond SequenceIndex.list(params), model:[sequenceIndexCount: SequenceIndex.count()]
        }
    }

    def show(Long id) {
        respond sequenceIndexService.get(id)
    }

    def create() {
        respond new SequenceIndex(params)
    }

    def save(SequenceIndex sequenceIndex) {
        if (sequenceIndex == null) {
            notFound()
            return
        }

        try {
            sequenceIndexService.save(sequenceIndex)
        } catch (ValidationException e) {
            respond sequenceIndex.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), sequenceIndex.id])
                redirect action: "show", id: sequenceIndex.id
            }
            '*' { respond sequenceIndex, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sequenceIndexService.get(id)
    }

    def update(SequenceIndex sequenceIndex) {
        if (sequenceIndex == null) {
            notFound()
            return
        }

        try {
            sequenceIndexService.save(sequenceIndex)
        } catch (ValidationException e) {
            respond sequenceIndex.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), sequenceIndex.id])
                redirect action: "show", id: sequenceIndex.id
            }
            '*'{ respond sequenceIndex, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sequenceIndexService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    
    def getNamedData(String[] rawdata) {
        rawdata.eachWithIndex{ d, idx -> 
            def td = d.trim()
            if(td == "") {
                rawdata[idx] = null
            }else {
                rawdata[idx] = td
            }
        }
        [indexId: rawdata[0],
         indexVersion: rawdata[1],
         sequence: rawdata[2],
         oligo: rawdata[3],
        ]
    }
    
    
    def importCSV() {
        def filesroot = utilityService.getFilesRoot()
        try {
            def mpf = request.getFile( "file" )
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-4..-1] == ".csv") {
                File fileDest = new File(filesroot, filename)
                mpf.transferTo(fileDest)
                
                def file = new FileReader(fileDest)
                CSVReader reader = new CSVReader(file)
                String [] rawdata
                def lineNo = 0
                def messages = []
                while ((rawdata = reader.readNext()) != null) {
                    ++lineNo
                    if (lineNo == 1) {
                        continue
                    }
                    
                    def data = getNamedData(rawdata)
                    
                    if (data.indexId == null) {
                        messages.push("Line ${lineNo} is skipped: no index ID!")
                    } else if (data.indexVersion == null) {
                        messages.push("Line ${lineNo} is skipped: no index version!")
                    } else if (data.sequence == null) {
                        messages.push("Line ${lineNo} is skipped: no sequence!")
                    } else if (SequenceIndex.findByIndexId(data.indexId)) {
                        // check if the indexId already exists
                        messages.push("Line ${lineNo} indexId ${data.indexId} already exists!")
                    } else {
                        try {       
                            new SequenceIndex(indexVersion: data.indexVersion, 
                                              indexId: data.indexId, 
                                              sequence: data.sequence,
                                              oligo: data.oligo,
                                              status: DictionaryStatus.Y
                                             ).save(failOnError: true)
                        } catch(Exception e) {
                            log.error "Error: line ${lineNo}. " + e
                            messages.push("Line ${lineNo} is not imported: an error occurred!")
                        }
                    }
                }
                flash.messageList = messages
                fileDest.delete()
            } else {
                flash.messageList = ["Only CSV files are accepted!"]
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.messageList = ["Error uploading the file! Please make sure the CSV file follows the template!"]
        }
        redirect(action: "index")
    }
    
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.PROTOCOLS
}
