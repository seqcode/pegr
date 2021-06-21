package pegr
import org.springframework.web.multipart.MultipartHttpServletRequest 
import com.opencsv.CSVParser
import com.opencsv.CSVReader

class ProtocolController {
    def springSecurityService
    def protocolService
    def utilityService
            
    def index(Integer max) {
        def currentUser = springSecurityService.currentUser
        params.max = Math.min(max ?: 25, 100)
        def protocols = Protocol.where{ user == currentUser }.list(params)
        def protocolCount = Protocol.where{ user == currentUser }.count()
        [protocolList: protocols, protocolCount: protocolCount]
    }
    
    def allProtocols(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        def protocols = Protocol.list(params)
        def protocolCount = Protocol.count()
        [protocolList: protocols, protocolCount: protocolCount]
    }
    
    def labProtocols(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        def protocols = Protocol.where{ status == DictionaryStatus.Y }.list(params)
        def protocolCount = Protocol.where{ status == DictionaryStatus.Y }.count()
        [protocolList: protocols, protocolCount: protocolCount]
    }
    
    def labProtocolGroups(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        def protocolGroups = ProtocolGroup.list(params)
        [protocolGroupList: protocolGroups]
    }
    
    def show(Long id) {
        def protocol = Protocol.get(id)
        def file = protocolService.getProtocolFile(id)
        [protocol: protocol, file: file?.exists() ? file : null]
    }
    
    def showGroup(Long id) {
        def protocolGroup = ProtocolGroup.get(id)
        [protocolGroup: protocolGroup]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def protocol = new Protocol()
                bindData(protocol, params, [exclude: ['file']])
                protocol.user = springSecurityService.currentUser
                def protocolItemTypeIds = [ 
                        (pegr.ProtocolItemFunction.PARENT) : [params.long('startItemTypeId')],
                        (pegr.ProtocolItemFunction.CHILD) : [params.long('endItemTypeId')],
                        (pegr.ProtocolItemFunction.SHARED) : params.list('sharedItemTypeIds'),
                        (pegr.ProtocolItemFunction.END_PRODUCT) : params.list('endProductTypeIds'),
                        (pegr.ProtocolItemFunction.START_POOL) : [params.long('startPoolTypeId')],
                        (pegr.ProtocolItemFunction.END_POOL) : [params.long('endPoolTypeId')]
                ]
                try {
                    protocolService.save(protocol, protocolItemTypeIds)
                } catch (ProtocolException e) {
                    request.message = e.message
                    render(view: "create", model: [protocol: protocol,
                        startItemType: protocol?.startItemType,
                        endItemType: protocol?.endItemType,
                        sharedItemTypes: protocol?.sharedItemTypes,
                        endProductTypes: protocol?.endProductTypes,
                        startPoolType: protocol?.startPoolType,
                        endPoolType: protocol?.endPoolType])
                    return
                }
                try {
                    flash.message = "New protocol saved! "
                    protocolService.uploadFile( (MultipartHttpServletRequest)request, protocol.id, "file")
                } catch(ProtocolException e) {
                    flash.message = "New protocol saved, but the file is not uploaded!"
                    flash.message += e.message
                } 
                redirect(action: "show", id: protocol.id)
            }
        }
    }
    
    def edit() {
        def protocol = Protocol.get(params.long('id'))
        if (!protocol) {
            flash.message = "Protocol does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                protocol.properties = params
                def protocolItemTypeIds = [ 
                        (pegr.ProtocolItemFunction.PARENT) : [params.long('startItemTypeId')],
                        (pegr.ProtocolItemFunction.CHILD) : [params.long('endItemTypeId')],
                        (pegr.ProtocolItemFunction.SHARED) : params.list('sharedItemTypeIds'),
                        (pegr.ProtocolItemFunction.END_PRODUCT) : params.list('endProductTypeIds'),
                        (pegr.ProtocolItemFunction.START_POOL) : [params.long('startPoolTypeId')],
                        (pegr.ProtocolItemFunction.END_POOL) : [params.long('endPoolTypeId')]
                ]
                try {
                    protocolService.save(protocol, protocolItemTypeIds)
                    flash.message = "Protocol update!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "edit", model: [protocol: protocol,
                        startItemType: protocol?.startItemType,
                        endItemType: protocol?.endItemType,
                        sharedItemTypes: protocol?.sharedItemTypes,
                        endProductTypes: protocol?.endProductTypes,
                        startPoolType: protocol?.startPoolType,
                        endPoolType: protocol?.endPoolType])
                }
            }
        } else {
            [protocol: protocol,
            startItemType: protocol?.startItemType,
            endItemType: protocol?.endItemType,
            sharedItemTypes: protocol?.sharedItemTypes,
            endProductTypes: protocol?.endProductTypes,
            startPoolType: protocol?.startPoolType,
            endPoolType: protocol?.endPoolType]
        }
    }
    
    
    def delete(Long id) {
        try {
            protocolService.delete(id)
            flash.message = "Protocol deleted!"
            redirect(action: "index")
        }catch(ProtocolException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        }
    }
    
    def upload(Long protocolId) {
        try {
            protocolService.uploadFile( (MultipartHttpServletRequest)request, protocolId, "file")
            flash.message = "Protocol uploaded!"
        } catch(ProtocolException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: protocolId)
    }

    def deleteFile(Long protocolId) {
        try {
            def file = protocolService.getProtocolFile(protocolId)
            file.delete()
            flash.message = "File deleted!"
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error deleting the file!"
        }
        redirect(action: "show", id: protocolId)
    }
    
    def renderFile(Long protocolId) {
        def file = protocolService.getProtocolFile(protocolId)
        response.setHeader "Content-disposition", "inline; filename=${file.getName()}"
        response.contentType = 'application/pdf'
        response.outputStream << file
        response.outputStream.flush()
        render(contentType: "application/pdf", contentDisposition: "inline", file: file, fileName: file.getName())
    }
    
    def search(String str) {
        def c = Protocol.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort ?: "id",
                order: params.order ?: "desc",
                offset: params.offset
            ]
        def protocols = c.list(listParams) {
            and {
                eq "status", DictionaryStatus.Y
                or {
                    ilike "name", "%${str}%"
                    ilike "description", "%${str}%"
                    user {
                        ilike "username", "%${str}%"
                    }
                }
            }
        }
        [protocolList: protocols, protocolCount: protocols.totalCount, str: str]        
    }
    
    def getNamedData(String[] rawdata) {
        rawdata.eachWithIndex{ d, idx -> 
            def td = d.trim()
            if(td == "") {
                rawdata[idx] = null
            } else {
                rawdata[idx] = td
            }
        }
        [name: rawdata[0],               // A
         protocolVersion: rawdata[1],    // B
         assay: rawdata[2],              // C
         description: rawdata[3],        // D
         sharedItemTypes: rawdata[4],    // E
         endProductTypes: rawdata[5],    // F
         images: rawdata[6],             // G
         startSampleType: rawdata[7],    // H
         endSampleType: rawdata[8],      // I
         addAntibody: rawdata[9],        // J
         addIndex: rawdata[10],          // K
         startPoolType: rawdata[11],     // L
         endPoolType: rawdata[12],       // M
         url: rawdata[13],               // N
         status: rawdata[14],            // O
         file: rawdata[15]               // P
        ]
    }
    
    
    def importCSV() {
        def currentUser = springSecurityService.currentUser
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
                    } else if ((data.protocolVersion == null && Prtocol.findByName(data.name) != null) || (data.protocolVersion != null && Protocol.findByNameAndProtocolVersion(data.name, data.protocolVersion) != null)) {
                        messages.push("Line ${lineNo} is skipped: a protocol with the same name and version already exists!")
                    } else {
                        def assay = null
                        if (data.assay != null) {
                            assay = Assay.findByName(data.assay)
                            if (!assay) {
                                messages.push("Line ${lineNo} is skipped: assay not found!")
                                continue
                            }
                        }
                        
                        def addAntibody = (data.addAntibody?.toLowerCase() == "yes") ? true : false
                        def addIndex = (data.addIndex?.toLowerCase() == "yes") ? true : false
                        def status = null
                        if (data.status && data.status.toLowerCase() == "yes") {
                            status = DictionaryStatus.Y
                        }

                        try {
                            def protocol = new Protocol(name: data.name, 
                                         protocolVersion: data.protocolVersion,
                                         assay: assay,
                                         description: data.description,
                                         images: data.images,
                                         addAntibody: addAntibody,
                                         addIndex: addIndex,
                                         url: data.url,
                                         file: "protocols/${data.file}",
                                         user: currentUser,
                                         status: status
                                        )
                            protocol.save(failOnError: true)
                            
                            def dict = [(pegr.ProtocolItemFunction.SHARED) : data.sharedItemTypes,
                                 (pegr.ProtocolItemFunction.END_PRODUCT) : data.endProductTypes,   
                                 (pegr.ProtocolItemFunction.PARENT) : data.startSampleType,
                                 (pegr.ProtocolItemFunction.CHILD) : data.endSampleType,
                                 (pegr.ProtocolItemFunction.START_POOL) : data.startPoolType,
                                 (pegr.ProtocolItemFunction.END_POOL) : data.endPoolType]
                                
                            dict.each { itemFunction, typesStr ->
                                if (typesStr) {
                                    typesStr.split(",").each { str ->
                                        def s = str.trim().split(":")
                                        if (s.size() != 2) {
                                            messages.push("Line ${lineNo}: the format of item type ${str} is not correct!")
                                        } else {
                                            def category = ItemTypeCategory.findByName(s[0].trim())
                                            if (category == null) {
                                                messages.push("Line ${lineNo}: item type category ${s[0]} not found!")
                                            } else {
                                                def itemType = ItemType.findByNameAndCategory(s[1].trim(), category)
                                                if (!itemType) {
                                                    messages.push("Line ${lineNo}: item type ${str} not found!")
                                                } else {
                                                    new ProtocolItemTypes(protocol: protocol, itemType: itemType, function: itemFunction).save(failOnError: true)
                                                }
                                            }                                            
                                        }                                        
                                    }
                                }
                            }                     
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
}