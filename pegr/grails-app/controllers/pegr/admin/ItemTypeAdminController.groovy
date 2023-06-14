package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ItemType
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class ItemTypeAdminController {

    ItemTypeService itemTypeService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ItemType.hasProperty("name")) {
            def c = ItemType.createCriteria()
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
            respond items, model:[itemTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ItemType.list(params), model:[itemTypeCount: ItemType.count()]
        }
    }

    def show(Long id) {
        respond itemTypeService.get(id)
    }

    def create() {
        respond new ItemType(params)
    }

    def save(ItemType itemType) {
        if (itemType == null) {
            notFound()
            return
        }

        try {
            itemTypeService.save(itemType)
        } catch (ValidationException e) {
            respond itemType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'itemType.label', default: 'ItemType'), itemType.id])
                redirect action: "show", id: itemType.id
            }
            '*' { respond itemType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond itemTypeService.get(id)
    }

    def update(ItemType itemType) {
        if (itemType == null) {
            notFound()
            return
        }

        try {
            itemTypeService.save(itemType)
        } catch (ValidationException e) {
            respond itemType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'itemType.label', default: 'ItemType'), itemType.id])
                redirect action: "show", id: itemType.id
            }
            '*'{ respond itemType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        itemTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemType.label', default: 'ItemType'), id])
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
         fields: rawdata[1],
         category: rawdata[2]
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
                    } else if (data.category == null) {
                        messages.push("Line ${lineNo} is skipped: no category!")
                    } else if (ItemType.findByName(data.name) != null) {
                        messages.push("Line ${lineNo} is skipped: item type name already exists!")
                    } else {
                        def category = ItemTypeCategory.findByName(data.category)
                        if (!category) {
                            messages.push("Line ${lineNo} is skipped: category does not exist!")
                        } else {
                            try {
                                new ItemType(name: data.name, category: category, fields: data.fields).save(failOnError: true)
                            } catch(Exception e) {
                                log.error "Error: line ${lineNo}. " + e
                                messages.push("Line ${lineNo} is not imported: an error occurred!")
                            }
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
    
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'ItemType.csv'
        def lines = ItemType.findAll().collect { [
            it.id, 
            it.name?'"'+it.name+'"':"", 
            it.category?it.category:"", 
            it.fields?'"'+it.fields+'"':"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Category, Fields\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemType.label', default: 'ItemType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.PROTOCOLS
}
