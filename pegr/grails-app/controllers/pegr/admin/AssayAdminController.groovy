package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Assay
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class AssayAdminController {
    AssayService assayService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Assay.hasProperty("name")) {
            def c = Assay.createCriteria()
            def listParams = [
                    max: max ?: 25,
                    sort: params.sort ?: "id",
                    order: params.order ?: "desc",
                    offset: params.offset
                ]
            def likeStr = "%" + str + "%"
            def items = c.list(listParams) {
                or {
                    ilike "name", likeStr
                }
            }
            respond items, model:[assayCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Assay.list(params), model:[assayCount: Assay.count()]
        }
    }

    def show(Long id) {
        respond assayService.get(id)
    }

    def create() {
        respond new Assay(params)
    }

    def save(Assay assay) {
        if (assay == null) {
            notFound()
            return
        }

        try {
            assayService.save(assay)
        } catch (ValidationException e) {
            respond assay.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assay.label', default: 'Assay'), assay.id])
                redirect action: "show", id: assay.id
            }
            '*' { respond assay, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assayService.get(id)
    }

    def update(Assay assay) {
        if (assay == null) {
            notFound()
            return
        }

        try {
            assayService.save(assay)
        } catch (ValidationException e) {
            respond assay.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assay.label', default: 'Assay'), assay.id])
                redirect action: "show", id: assay.id
            }
            '*'{ respond assay, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assayService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assay.label', default: 'Assay'), id])
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
        [name: rawdata[0],
         description: rawdata[1]
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
                    
                    if (data.name == null) {
                        messages.push("Line ${lineNo} is skipped: no name!")
                    } else if (Assay.findByName(data.name) != null) {
                        messages.push("Line ${lineNo} is skipped: assay name already exists!")
                    } else {
                        try {
                            new Assay(name: data.name, description: data.description).save(failOnError: true)
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assay.label', default: 'Assay'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	public static AdminCategory category = AdminCategory.PROTOCOLS
}