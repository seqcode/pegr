package pegr
import groovy.sql.Sql

/**
 * The class of security filters defines the user's authorization to 
 * the actions in controllers.
 */
class SecurityFilters {

    def dataSource
    def springSecurityService
    def projectService
    def sampleService
    
    def filters = {
        
        /**
         * allow ADMIN, MEMBER or anyone who has a role in this project to view 
         */
        projectShow(controller:'project', action:'show') {
            before = {
                def projectId = params.long('id')
                if (projectService.projectViewAuth(projectId)) {
                    return true                    
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        /*
         * The authorization to view a project's summary report is the same as 
         * that of viewing a project
         */
        reportShowProject(controller:'report', action:'showProject') {
            before = {
                def projectId = params.long('id')
                if (projectService.projectViewAuth(projectId)) {
                    return true                    
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        /*
         * The authorization to view a summary report is the same as 
         * that of viewing the project that the report belongs to.
         */
        reportShow(controller:'report', action:'show') {
            before = {
                def reportId = params.long('id')
                def projectId = SummaryReport.get(reportId)?.project?.id
                if (projectService.projectViewAuth(projectId)) {
                    return true                    
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        projectEdit(controller:'project', action:'edit|addUserAjax|removeUserAjax|editUserRoleAjax') {
            before = {
                def project = Project.get(params.long('projectId'))
                if (projectService.projectEditAuth(project)) {
                    return true                  
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        projectSampleEdit(controller:'project', action:'removeSample|searchSample|addExistingSample|addNewSamples') {
            before = {
                def project = Project.get(params.long('projectId'))
                if (projectService.sampleEditAuth(project)) {
                    return true                  
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
                
        AntibodyEdit(controller: 'antibody', action: 'edit|update|delete|editItem|updateItem|addBarcode') {
            before = {
                def antibodyId = params.long('antibodyId')
                def antibody = Antibody.get(antibodyId)
                def currUser = springSecurityService.currentUser
                if (currUser.isAdmin() || antibody?.item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }

        CellSourceEdit(controller: 'cellSource', action: 'edit|update|addTreatment') {
            before = {
                def cellSourceId = params.long('cellSourceId')
                def cellSource = CellSource.get(cellSourceId)
                def currUser = springSecurityService.currentUser
                if (currUser.isAdmin() || cellSource?.item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        ItemEdit(controller: 'item', action: 'edit|update|upload|deleteImage|delete') {
            before = {
                def itemId = params.long('itemId')
                def item = Item.get(itemId)
                def currUser = springSecurityService.currentUser
                if (currUser.isAdmin() || item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }

        /**
         * before the bag is completed
         */
        ProtocolInstanceBagEdit(controller: "protocolInstanceBag", action: "searchItemForBag|previewItemAndBag|addItemToBag|addSubBagToBag|removeItemFromBag|updateBagAjax") {
            before = {
                def bagId = params.long('bagId')
                def bag = ProtocolInstanceBag.get(bagId)
                if (bag.status != ProtocolStatus.COMPLETED) {
                    return true
                } else {
                    flash.message = "The protocol instance bag is completed and no changes are allowed. Please contact lab admin if you have further questions."
                    redirect(action: "showBag", id: bagId)
                }
            }    
        }
        
        /**
         * before the bag is completed and only admin/instance user is allowed 
         */
        ProtocolInstanceEdit(controller: "protocolInstanceBag", action: "searchItemForInstance|searchItemForTypeInstance|previewItemInInstance|addPoolToInstance|addItemToInstance|saveItemInInstance|removeItemFromInstance|addIndex|searchAntibody|previewAntibody|addAntibodyToSample|removeAntibody|addChild|removeChild|completeInstance") {
            before = {
                def instanceId = params.long('instanceId') 
                def instance = ProtocolInstance.get(instanceId)
                if (instance?.user) {
                    def currUser = springSecurityService.currentUser
                    if (!currUser.isAdmin() && instance.user != currUser) {
                        render(view: '/login/denied')
                        return false
                    }
                }                
                if (instance?.bag) {
                    if (instance.bag.status == ProtocolStatus.COMPLETED) {
                        flash.message = "The protocol instance bag is completed and no changes are allowed. Please contact lab admin if you have further questions."
                        redirect(action: "showBag", id: instance.bag.id)
                    }
                } 
                return true
            }
        }
        
        SequenceRunEdit(controller: "sequenceRun", action: "editRead|updateRead|editInfo|update|searchPool|addPool|removePool|addSamplesById|removeExperiment|edit|updateSamples|run") {
            before = {
                def runId = params.long('runId')
                def run =  SequenceRun.get(runId)
                if (run?.user) {
                    def currUser = springSecurityService.currentUser
                    if (!currUser.isAdmin() && run.user != currUser) {
                        render(view: '/login/denied')
                        return false
                    }
                }
                if (run?.status == RunStatus.COMPLETED) {
                    flash.message = "This sequence run is completed and no changes are allowed. Please contact lab admin if you have further questions."
                    redirect(action: "show", id: runId)
                }
                return true
            }
        }

        /**
         * If the replicate belongs to a project, allow admin or anyone who has
         * a role in that project to view the replicate set; else allow anyone
         * to view.
         */
        replicateShow(controller:'replicate', action:'show') {
            before = {
                def currUser = springSecurityService.currentUser
                if (currUser.isAdmin()) {
                    return true
                }
                def replicateId = params.long('id')
                def project = ReplicateSet.get(replicateId)?.project
                if (!project) {
                    return true
                }
                if (ProjectUser.where {project == project && user == currUser}.get(max: 1)) {
                    return true                    
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }

        /** 
         * If the replicate belongs to a project, the right to add or remove 
         * samples is the same as the right to edit that project, that is the
         * user is an admin or is an owner of the project; else, only the admin
         * is allowed to add/remove samples.  
         */
        replicateSample(controller:'replicate', action:'addSamples|removeSample') {
            before = {
                def replicateId = params.long('setId')
                def project = ReplicateSet.get(replicateId)?.project
                if (projectService.sampleEditAuth(project)) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }  
        
        /** 
         * If the replicate belongs to a project, the right to delete the replicate set is the same 
         * as the right to edit that project, that is 
         * the user is an admin or is an owner of the project; else, only the 
         * admin is allowed to delete the set.  
         */
        replicateDelete(controller:'replicate', action:'delete') {
            before = {
                def replicateId = params.long('id')
                def project = ReplicateSet.get(replicateId)?.project
                if (projectService.sampleEditAuth(project)) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        /** 
         * If the replicate belongs to a project, the right to create a
         * replicate set inside the project is the same as the right to edit 
         * that project, that is the user is an admin or is an owner of the
         * project; else, only the admin is allowed to create a replicate set.  
         */
        replicateCreate(controller:'replicate', action:'saveAjax') {
            before = {
                def project = Project.get(params.long('projectId'))
                if (projectService.sampleEditAuth(project)) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        /**
         * ADMIN and MEMBERS are allowed to see all samples; 
         * Others are only allowed to see their projects' samples;
         */        
        sampleShow(controller:'sample', action:'show') {
            before = {
                def user = springSecurityService.currentUser
                if (user.isAdmin() || user.isMember()) {
                    return true
                } else {
                    def sampleId = params.long('id')
                    def sql = new Sql(dataSource)
                    def count = sql.rows("SELECT count(*) as cnt FROM project_user pu JOIN project_samples ps ON pu.project_id = ps.project_id WHERE pu.user_id = ${user.id} and ps.sample_id = ${sampleId}") 
                    if (count[0].cnt > 0) {
                        return true
                    } else {
                        render(view: '/login/denied')
                        return false
                    }                    
                }               
            }    
        }
        
        /**
         * ADMIN and Project Owner/Participants are allowed to edit the sample
         */
        sampleEdit(controller:'sample', action:'edit') {
            before = {
                def sample = Sample.get(params.long('sampleId'))
                if (sampleService.editAuth(sample)) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        /**
         * Only the protocol's user or ADMIN can edit or delete the protocol.
         */
        protocolEdit(controller: 'protocol', action: 'edit|delete') {
            before = {
                def user = springSecurityService.currentUser
                if (user.isAdmin()) {
                    return true
                } else {
                    def protocol = Protocol.get(params.long('id'))
                    if (protocol.user == user) {
                        return true
                    } else {
                        render(view: '/login/denied')
                        return false
                    }
                }
            }
        }
        
        /**
         * Only the protocol's user or ADMIN can upload or delete the protocol's file.
         */
        protocolEdit(controller: 'protocol', action: 'upload|deleteFile') {
            before = {
                def user = springSecurityService.currentUser
                if (user.isAdmin()) {
                    return true
                } else {
                    def protocol = Protocol.get(params.long('protocolId'))
                    if (protocol.user == user) {
                        return true
                    } else {
                        render(view: '/login/denied')
                        return false
                    }
                }
            }
        }
    }
}

