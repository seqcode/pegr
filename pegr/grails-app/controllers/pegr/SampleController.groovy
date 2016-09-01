package pegr
import groovy.json.*
import grails.converters.*
    
class SampleController {
    
    def springSecurityService
    def sampleService
    def antibodyService
    def cellSourceService
    def itemService
    def utilityService
    def reportService
    
    def all(Integer max) {
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
    
    def updateProtocol(Long sampleId, Long assayId, String resin, Integer pcr, Long userId, String endTime, String growthMedia) {
        def sample = Sample.get(sampleId)
        if (sample) {
            try {
                def treatments = params.list("treatments")
                sampleService.updateProtocol(sample, assayId, resin, pcr, userId, endTime, growthMedia, treatments)
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
    
    def editAntibody(Long sampleId, Long antibodyId){
        def cmd
        def item
        if (antibodyId == null) {
            // create
            cmd = new AntibodyCommand()
        } else {
            def antibody = Antibody.get(antibodyId)
            if (!antibody) {
                render(view: "/404")
                return
            }
            cmd = new AntibodyCommand(   
                antibodyId : antibody.id,
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
        def itemTypeOptions = ItemType.where {category.superCategory == ItemTypeSuperCategory.ANTIBODY}.list()
        [antibody: cmd, item: item, sampleId: sampleId, antibodyId: antibodyId, itemTypeOptions: itemTypeOptions]
    }
    
    def updateAntibody(Long sampleId, Long antibodyId, AntibodyCommand cmd, Item item) {
        try {
            if (antibodyId) {
                log.error item
                antibodyService.update(cmd, item)
            } else {
                antibodyService.saveInSample(sampleId, cmd, item)
            }
            flash.message = "Antibody update!"
            redirect(action: "edit", params: [sampleId: sampleId])
        } catch(AntibodyException e) {
            flash.message = e.message
            redirect(action:'editAntibody', params: [sampleId: sampleId, antibodyId: antibodyId])
        }
    }
    
    def searchAntibody(Long sampleId) {
        def itemTypeOptions = ItemType.where {category.superCategory == ItemTypeSuperCategory.ANTIBODY}.list()
        [sampleId: sampleId, itemTypeOptions: itemTypeOptions]
    }
    
    def previewAntibody(Long sampleId, Long typeId, String barcode) {
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max: 1)
        if (item) {
            [sampleId: sampleId, item: item]    
        } else {
            flash.message = "Antibody not found!"
        }
    }
    
    def addAntibodyToSample(Long sampleId, Long itemId) {
        try {
            antibodyService.addAntibodyToSample(sampleId, itemId)
            flash.message = "Antibody updated!"
        } catch (AntibodyException e) {
            flash.message = e.message
        }
        redirect(action: "edit", params: [sampleId: sampleId])
    }
    
    def editCellSource(Long sampleId, Long cellSourceId) {
        def cmd
        def item
        def treatments
        if (cellSourceId == null) {
            // create
            cmd = new CellSourceCommand()
        } else {
            // edit 
            def cellSource = CellSource.get(cellSourceId)
            if (!cellSource) {
                render(view: "/404")
                return
            }
            cmd = new CellSourceCommand(
                cellSourceId: cellSource.id,
                itemId: cellSource.item?.id,
                genus: cellSource.strain?.species?.genusName,
                speciesId: cellSource.strain?.species?.id,
                speciesName: cellSource.strain?.species?.name,
                parentStrain: cellSource.strain?.parent?.name,
                strain: cellSource.strain?.name,
                genotype: cellSource.strain?.genotype,
                mutation: cellSource.strain?.geneticModification,
                tissue: cellSource.tissue?.name,
                age: cellSource.age,
                sex: cellSource.sex?.name,
                histology: cellSource.histology?.name,
                providerId: cellSource.providerUser?.id,
                providerLabId: cellSource.providerLab?.id,
                bioSourceId: cellSource.biologicalSourceId
            )
            item = cellSource.item
        }
        def itemTypeOptions = ItemType.where {category.superCategory == ItemTypeSuperCategory.TRACED_SAMPLE}.list()
        [cellSource: cmd, item: item, sampleId: sampleId, cellSourceId: cellSourceId, itemTypeOptions: itemTypeOptions]
    }
    
    def updateCellSource(Long sampleId, Long cellSourceId, CellSourceCommand cmd, Item item) {
        try {
            if (cellSourceId) {
                cellSourceService.update(cmd, item)
            } else {
                cellSourceService.saveInSample(sampleId, cmd, item)
            }            
            flash.message = "Cell source information updated!"
            redirect(action:"edit", params:[sampleId: sampleId])
        } catch (CellSourceException e) {
            flash.message = e.message
            redirect(action: "editCellSource", params: [sampleId: sampleId, cellSourceId: cellSourceId])
        }
    }
    
    def searchCellSource(Long sampleId) {
        def itemTypeOptions = ItemType.where {category.superCategory == ItemTypeSuperCategory.TRACED_SAMPLE}.list()
        [sampleId: sampleId, itemTypeOptions: itemTypeOptions]
    }
    
    def previewCellSource(Long sampleId, Long typeId, String barcode) {
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max: 1)
        if (item) {
            [sampleId: sampleId, item: item]    
        } else {
            flash.message = "Cell source not found!"
        }            
    }
    
    def addCellSourceToSample(Long sampleId, Long itemId) {
        try {
            cellSourceService.addCellSourceToSample(sampleId, itemId)
            flash.message = "Cell Source updated!"
        } catch (CellSourceException e) {
            flash.message = e.message
        }
        redirect(action: "edit", params: [sampleId: sampleId])
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
        
    }
    
    def searchForm() {
        
    }
    
    def search(Integer max) {       
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "id"
            params.order = "desc"
        }        
        def samples = sampleService.search(params)

        def checkedCount = 0;
        if (session.checkedSample) {
            checkedCount = session.checkedSample.size()
        }
        [sampleList: samples, checkedCount: checkedCount, searchParams: params]
    }
    
    def fetchDataForCheckedSamplesAjax() {
        def sampleIds = session.checkedSample
        def data = reportService.fetchDataForSamples(sampleIds)
        render(template: '/report/details', model: [ sampleDTOs: data])        
    }
    
    def clearCheckedSampleAjax(){
        if (session.checkedSample) {
            session.checkedSample = null
        }
        render true
    }
    
    def addCheckedSampleAjax(Long id) {
        if (!session.checkedSample) {
            session.checkedSample = []
        }
        if (!(id in session.checkedSample)) {
            session.checkedSample << id
        }
        render session.checkedSample.size()
    }
    
    def removeCheckedSampleAjax(Long id) {
        if (id in session.checkedSample) {
            session.checkedSample.remove(id)
        }
        render session.checkedSample.size()
    }
    
    def fetchGrowthMediaAjax(Long speciesId) {
        def selectedSpecies = Species.get(speciesId)
        def growthMedias = GrowthMedia.where { (species == null) || (species == selectedSpecies) }.collect{it.name}
        render utilityService.stringToSelect2Data(growthMedias) as JSON
    }
    
    def fetchTreatmentsAjax() {
        def treatments = CellSourceTreatment.executeQuery("select t.name from CellSourceTreatment t")
        render utilityService.stringToSelect2Data(treatments) as JSON
    }
    
    def fetchDataForSampleAjax(Long id) {
        def data = reportService.fetchDataForSample(id)
        render(template: '/sample/bioinformatics', model: [ sampleDTOs: data])    
    }
}
