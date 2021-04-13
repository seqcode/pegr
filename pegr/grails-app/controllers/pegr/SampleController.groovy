package pegr
import groovy.json.*
import grails.converters.*
import grails.util.Holders

class SampleController {

    def springSecurityService
    def sampleService
    def antibodyService
    def cellSourceService
    def itemService
    def utilityService
    def reportService
    def protocolInstanceBagService

    def all(QuerySampleRegistrationCommand cmd) {        
        def samples = sampleService.search(cmd)

        def checkedCount = 0;
        if (session.checkedSample) {
            checkedCount = session.checkedSample.size()
        }
        
        [sampleList: samples, checkedCount: checkedCount, searchParams: cmd]
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
            def genomes = Genome.executeQuery("select g.name from Genome g")
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
                redirect(action: "editTarget", params: [sampleId: sampleId])
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

    def updateAntibody(Long sampleId, Long antibodyId, AntibodyCommand cmd, Item item, String ifCascade, String ifUpdateItem) {
        try {
            def updateInPlace = true
            if (antibodyId) {
                if (ifCascade != "yes") {
                    def sampleCount = Sample.where {antibody.id == antibodyId}.count() 
                    if (sampleCount > 1) {
                        updateInPlace = false
                    }
                }
            } else {
                updateInPlace = false
            }
            if (updateInPlace) {
                if (ifUpdateItem == "yes") {
                    antibodyService.update(cmd, item)
                } else {
                    antibodyService.update(cmd)
                }
            } else {
                if (ifUpdateItem == "yes") {
                    antibodyService.saveInSample(sampleId, cmd, item)
                } else {
                    antibodyService.saveInSample(sampleId, cmd)
                }
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
        def antibodyTypeId = itemTypeOptions[0].id
        [sampleId: sampleId, itemTypeOptions: itemTypeOptions, antibodyTypeId: antibodyTypeId]
    }

    def previewAntibody(Long sampleId, Long typeId, String barcode) {
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max: 1)
        def antibody = Antibody.findByItem(item)
        if (item) {
            [sampleId: sampleId, item: item, antibody: antibody]
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

    def updateCellSource(Long sampleId, Long cellSourceId, CellSourceCommand cmd, Item item, String ifUpdateItem) {
        try {
            if (cellSourceId) {
                if (ifUpdateItem == "yes") {
                    cellSourceService.update(cmd, item)
                } else {
                    cellSourceService.update(cmd)
                }
            } else {
                if (ifUpdateItem == "yes") {
                    cellSourceService.saveInSample(sampleId, cmd, item)
                } else {
                    cellSourceService.saveInSample(sampleId, cmd)
                }
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
                def types = ItemType.where{ category.superCategory == ItemTypeSuperCategory.TRACED_SAMPLE }.list()
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

    // add all checked sample ids to the current session
    def addAllCheckedSampleAjax() {
        def sampleList = JSON.parse(params.sampleIdsList)
        if (!session.checkedSample) {
            session.checkedSample = []
        }
        sampleList.each{
          session.checkedSample.push(it.toLong())
        }
        render session.checkedSample.size()
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
    
    def removeCheckedSamplesAjax() {
        def sampleList = JSON.parse(params.sampleIdsList)
        sampleList.each { it ->
            def id = it.toLong()
            if (id in session.checkedSample) {
                session.checkedSample.remove(id)
            }
        }
        
        render session.checkedSample.size()
    }


    def fetchGrowthMediaAjax() {
        def growthMedias = GrowthMedia.list().collect{it.name}
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

    def batchEdit() {
        def samples = []
        if (params.runId) {
            def run = SequenceRun.get(params.long("runId"))
            if (!run) {
                render(view: "/404")
                return
            }
            samples = run.experiments*.sample
        } else if (params.bagId) {
            def items = protocolInstanceBagService.getTracedSamples(params.long("bagId"))
            items.each { item ->
                def sample = Sample.findByItem(item)
                if (sample) {
                    samples.push(sample)
                }
            }
        }

        [samples: samples]
    }

    def updateAjax(Long sampleId, String name, String value) {
        try {
            def result = sampleService.update(sampleId, name, value)
            if (result) {
                render result as JSON
            } else {
                render "success"
            }
            
        } catch (SampleException e) {
            render (status: 500, text: e.message)
        }
    }

    def updateListAjax(String sampleList) {
        def slurper = new JsonSlurper()
        def samples = slurper.parseText(sampleList)
        samples.each { sample ->
            sampleService.update(sample.sampleId, sample.name, sample.value)
        }

        render ""
        return
    }

    def showFilesForCheckedSamples() {
        def sampleIds = session.checkedSample
        def samples = reportService.fetchDataForSamples(sampleIds)
        render(view: "/report/listFiles", model: [samples: samples])
    }
}
