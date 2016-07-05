package pegr
import groovy.json.*
    
class SampleController {
    
    def springSecurityService
    def sampleService
    def antibodyService
    def itemService
    def utilityService
    
    def index(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "id"
            params.order = "desc"
        }
        def samples = Sample.where{status == SampleStatus.COMPLETED}.list(params)

        [sampleList: samples, sampleCount: Sample.count()]
    }
    
	def show(Long id) {
        def sample = Sample.get(id)
        if (sample) {
            def result = sampleService.getSampleDetails(sample)
            def editAuth = sampleService.editAuth(sample)
            result << [editAuth: editAuth]
        } else {
            render(view: "/404")
        }
	}
    
    def edit(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def result = sampleService.getSampleDetails(sample)
        } else {
            render(view: "/404")
        }
    }
    
    def editOther(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def species = sample.cellSource?.strain?.species
            def genomes 
            if (species) {
                genomes = Genome.executeQuery("select g.name from Genome g where g.species.id = ?", [species.id])
            } else {
                genomes = Genome.executeQuery("select g.name from Genome g")
            }            
            [sample: sample, genomes: genomes]
        } else {
            render(view: "/404")
        }
    }
    
    def updateOther(Long sampleId, String indexType, String indices) {
        def sample = Sample.get(sampleId)
        if (sample) {
            sample.properties = params
            try {
                sampleService.updateOther(sample, indexType, indices)
                flash.message = "Success updating the sample!"
                redirect(action: "edit", params: [sampleId: sampleId])
            } catch (SampleException e) {
                flash.message = e.message
                redirect(action: "editOther", params: [sampleId: sampleId])
            }
        } else {
            render(view: "/404")
        }
    }
    
    def editProtocol(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def notes = [:]
            try {
                def jsonSlurper = new JsonSlurper()
                notes += jsonSlurper.parseText(sample.prtclInstSummary.note)
            } catch(Exception e) {
                
            }
            [sample: sample, notes:notes]
        } else {
            render(view: "/404")
        }
    }
    
    def updateProtocol(Long sampleId, Long assayId, String resin, Integer pcr, Long userId, String endTime) {
        def sample = Sample.get(sampleId)
        if (sample) {
            try {
                sampleService.updateProtocol(sample, assayId, resin, pcr, userId, endTime)
                redirect(action: "edit", params: [sampleId: sampleId])
            } catch(SampleException e) {
                flash.message = e.message
                redirect(action: "editProtocol", params: [sampleId: sampleId])
            }
        } else {
            render(view: "/404")
        }
    }
    
    def editTarget(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def target = sample.target ?: sample.antibody?.defaultTarget
            [sample: sample, target: target]
        } else {
            render(view: "/404")
        }
    }
    
    def updateTarget(Long sampleId, String type, String target, String cterm, String nterm) {
        def sample = Sample.get(sampleId)
        if (sample) {
            try {
                sampleService.updateTarget(sample, target, type, nterm, cterm)
                redirect(action: "edit", params: [sampleId: sampleId])
            } catch(SampleException e) {
                flash.message = e.message
                redirect(action: "editProtocol", params: [sampleId: sampleId])
            }
        } else {
            render(view: "/404")
        }
    }
    
    def editAntibody(Long sampleId){
        def sample = Sample.get(sampleId)
        if (sample) {
            def antibody = sample.antibody
            def antibodyCommand = null
            def item = null
            if (antibody) {
                antibodyCommand = new AntibodyCommand(   
                    id : antibody.id,
                    company : antibody.company?.name,
                    catalogNumber : antibody.catalogNumber,
                    lotNumber : antibody.lotNumber,
                    abHost : antibody.abHost?.name,
                    immunogene : antibody.immunogene,
                    clonal : antibody.clonal,
                    igType : antibody.igType?.name,
                    concentration : antibody.concentration,
                    targetType : antibody.defaultTarget?.targetType,
                    target : antibody.defaultTarget?.name,
                    cterm : antibody.defaultTarget?.cTermTag,
                    nterm : antibody.defaultTarget?.nTermTag
                )
                item = antibody.item
            } 
            [antibody: antibodyCommand, item: item, sampleId: sampleId]
        } else {
            render(view: "/404")
        }
    }
    
    def updateAntibody(Long sampleId, AntibodyCommand cmd, Item item) {
        try {
            antibodyService.update(cmd)
            flash.message = "Antibody update!"
            redirect(action: "show", id: cmd.id)
        } catch(ItemException e) {
            request.message = e.message
            render(view:'edit', model:[antibody: cmd])
        } catch(Exception e) {
            request.message = "Error updating the antibody!"
            log.error "Error: ${e.message}", e
            render(view:'edit', model:[antibody: cmd])
        }
    }
    
    def searchAntibody(Long sampleId) {
        
    }
    
    def previewAntibody(Long sampleId) {
        
    }
    
    def editCellSource(Long sampleId, Long cellSourceId) {
        def cellSource = cellSourceId ? CellSource.get(cellSourceId) : null
        def item = cellSource?.item
        def itemTypeOptions = ItemType.where {category==ItemTypeCategory.TRACED_SAMPLE}.list()
        [cellSource: cellSource, item: item, sampleId: sampleId, cellSourceId: cellSourceId, itemTypeOptions: itemTypeOptions]
    }
    
    def updateCellSource(Long sampleId, Long cellSourceId, CellSourceCommand cmd, Item item) {
        if (cellSourceId) {
            try {
                cellSourceService.update(cmd, item)
                flash.message = "Cell source information updated!"
                redirect(action:"edit", params:[sampleId: sampleId])
            } catch (CellSourceException e) {
                flash.message = e.message
                redirect(action: "editCellSource", params: [sampleId: sampleId, cellSourceId: cellSourceId])
            }
        }
    }
    
    def searchCellSource(Long sampleId) {
        [sampleId: sampleId]
    }
    
    def previewCellSource(Long sampleId) {
        [sampleId: sampleId]
    }
    
    def showItem(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            if (sample.item) {
                redirect(controller: "item", action: "show", id: sample.item.id)
            } else {
                def types = ItemType.where{ category == ItemTypeCategory.TRACED_SAMPLE }.list()
                render(view: "addBarcode", model: [sampleId: sampleId, types: types])
            }
        } else {
            render(view: "/404")
        }
    }
    
    def saveBarcode(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def item = new Item(params)
            try {
                sampleService.addItem(sample, item)
                flash.message = "Barcode added!"
            } catch (SampleException e) {
                flash.message = e.message
            } catch (Exception e) {
                log.error e
                flash.message = "Error saving the barcode to the sample!"
            }
            redirect(action: "show", id: sampleId)
        } else {
            render(view: "/404")
        }
    }
    
    
    def showChecked(){
        
        def ids
        if (session.checkedSample){
            session.checkedSample.append(params.checkedSample)
        }else{
            ids = params.checkedSample
        }
        
        def sampleList = []
        ids.each{
            def sample = Sample.get(Long.parseLong(it))
            if (sample) {
                sampleList.push(sample)
            }
        }
        session.checkedSample = null
        [sampleList: sampleList, ids: ids]
    }
    
    def searchForm() {
        
    }
    
    def search() {       
        def sampleProps = Sample.metaClass.properties*.name
        def c = Sample.createCriteria()
        params.max = 15
        def samples = c.list(params) {
            and {
               if (params.species) {
                    cellSource {
                        strain {
                            species {
                                ilike "name", "%${params.species}%"
                            }
                        }
                    }
                }
                if (params.strain) {
                    cellSource {
                        strain {
                            ilike "name", "%${params.strain}%"
                        }                    
                    }
                }
                if (params.antibody) {
                    antibody {
                        ilike "catalogNumber", "%${params.antibody}%"
                    }
                }
                if (params.id) {
                    eq "id", Long.parseLong(params.id)
                }
                if (params.sourceId) {
                    eq "sourceId", params.sourceId
                }
                if (params.source) {
                    ilike "source", "%${params.source}%"
                }
                if (params.target) {
                    target {
                        ilike "name", "%${params.target}%"
                    }
                }
                eq("status", SampleStatus.COMPLETED)
            }
            order("id", "desc")
        }
        [sampleList: samples]
    }
    
    def clearCheckedSampleAjax(){
        if (session.checkedSample) {
            session.checkedSample = null
        }
        return
    }
    
}
