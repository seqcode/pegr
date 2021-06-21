package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ProtocolGroup
import pegr.ProtocolGroupException
import grails.transaction.Transactional
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class ProtocolGroupAdminController {

    def utilityService
	public static AdminCategory category = AdminCategory.PROTOCOLS
    def protocolGroupService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ProtocolGroup.hasProperty("name")) {
            def c = ProtocolGroup.createCriteria()
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
            respond items, model:[protocolGroupCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ProtocolGroup.list(params), model:[protocolGroupCount: ProtocolGroup.count()]
        }
    }

    def show(Long id) {
        respond protocolGroupService.get(id)
    }

    def create() {
        respond new ProtocolGroup(params)
    }

    def edit(Long id) {
        respond protocolGroupService.get(id)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'protocolGroup.label', default: 'ProtocolGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def save() {
        // update with new input
        def protocolGroup = new ProtocolGroup(params)
        def protocolList = params.list("protocolList")

        try {
            protocolGroupService.save(protocolGroup, protocolList)
            flash.message = "Protocol Group ${protocolGroup.name} has been saved!"
            redirect(id: protocolGroup.id, action: 'show')
        } catch(ProtocolGroupException e) {
            flash.message = e.message
            redirect(action: 'create', params:[protocolGroupInstance: protocolGroup])
        }
    }
    
    def update() {
        withForm {
            def protocolGroup = ProtocolGroup.get(params.id)
            def protocolList = params.list("protocolList")
            if (protocolGroup) {
                // update with new input
                protocolGroup.properties = params
                try {
                    protocolGroupService.update(protocolGroup, protocolList)
                    flash.message = "Protocol Group ${protocolGroup.name} has been updated!"
                    redirect(id: protocolGroup.id, action: 'show')
                } catch(ProtocolGroupException e) {
                    flash.message = e.message
                    redirect(id: protocolGroup.id, action: 'edit', params:[protocolGroupInstance: protocolGroup])
                }
            } else {
                render status: 404
            }
        }
    }
    
    def delete(Long id) {
        try {
            protocolGroupService.delete(id)
            flash.message = "Protocol Group #${id} has been deleted!"
            redirect(action: "index")
        } catch(ProtocolGroupException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
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
         protocols: rawdata[1]
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
                    } else if (data.protocols == null) {
                        messages.push("Line ${lineNo} is skipped: no protocols provided!")
                    } else {
                        def protocolGroup = new ProtocolGroup(name: data.name)
                        protocolGroup.save(failOnError: true)
                        def idx = 0
                        data.protocols.split(';').each {str ->
                            def s = str.split(' -- ')
                            if (s.size() != 2) {
                                messages.push("Line ${lineNo}: the format of protocol ${str} is not correct!")
                            }
                            def name  = s[0].trim()
                            def version = s[1].trim()
                            def protocol
                            if (version == '') {
                                protocol = Protocol.findByName(name)
                            } else {
                                protocol = Protocol.findByNameAndProtocolVersion(name, version)
                            }                            
                            if (!protocol) {
                                messages.push("Line ${lineNo}: protocol ${s} is not found!")
                            } else {
                                new ProtocolGroupProtocols(protocolGroup: protocolGroup, protocol: protocol, protocolsIdx: idx).save(failOnError: true)
                            }
                            idx++
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
    
}
