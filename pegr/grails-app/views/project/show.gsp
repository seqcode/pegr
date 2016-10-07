<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
    <style>
        .dropdown-menu {
            background-color: white;
        }
    </style>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
	<div>
		<h3>Project: ${project?.name} <g:if test="${projectEditAuth}"><g:link action="edit" params="[projectId:project?.id]" class="edit">Edit</g:link></g:if></h3>
        <div class="dropdown pull-right">
            <button class="edit dropdown-toggle" type="button" data-toggle="dropdown">Notes <span class="glyphicon glyphicon-pencil"></span></button>
            <div class="dropdown-menu">
                <textarea name="notes" id="notes">${project.notes}</textarea>
                <button class="pull-right btn btn-default" onclick="saveNotes();">Save</button>
            </div>
        </div>
		<p>Created: ${project?.dateCreated}, updated: ${project?.lastUpdated}</p>
		<p>Description: ${project?.description}</p>
        <p>Funding: ${project?.fundings.join(', ')}</p>
        <h3>Users <g:if test="${projectEditAuth}"><button class="edit" data-toggle="modal" data-target="#addUser">Add</button></g:if></h3>
        <div id="project-users">
            <g:render template="userTable"/>
        </div> 
        <h3>Sequencing Runs <g:link class="edit" controller="protocolInstanceBag" action="list" params="[project:project.id]">Experiments</g:link></h3>
        <ul>
            <g:each in="${project.cohorts}" var="cohort">
                <h4>${cohort}
                    <g:if test="${cohort.report && cohort.report.status == pegr.ReportStatus.PUBLISH}"><g:link controller="report" action="show" id="${cohort.report?.id}">Report: ${cohort.report?.name}</g:link> 
                    </g:if>
                </h4>
                <g:render template="/project/sampleTable" model="['sampleList':cohort.samples, 'project':project]" />
            </g:each>
        </ul>
        <g:if test="${sampleEditAuth}">
            <div>
                <button data-toggle="modal" data-target="#selectAssay" class="btn btn-info">Create New Samples</button>
                <g:link action="searchSample" params="[projectId: project?.id]" class="btn btn-info">Add Existing Sample</g:link>
            </div>
        </g:if>            
            
        <h3>Replicates <g:if test="${sampleEditAuth}"><button data-toggle="modal" data-target="#addReplicate" class="edit">Add</button></g:if></h3>
        <div id="replicates">
            <g:render template="/replicate/list" model="['replicates':replicates]"></g:render>
        </div>
    </div>
    <br/>         
    
    <g:if test="${sampleEditAuth}">
        <div id="selectAssay" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Select Assay</h4>
                    </div>
                    <div class="modal-body">
                        <g:form action="addNewSamples" class="fields" role="form" method="get">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <div>
                                <label>Assay</label> 
                                <g:select name="assayId" optionKey="id" from="${pegr.Assay.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitButton class="btn btn-primary" name="submit" value="OK"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </g:form>         
                    </div>
                </div>
            </div>
        </div>
    
        <div id="addUser" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Add User to Project: ${project?.name}</h4>
                    </div>
                    <div class="modal-body">
                        <form class="fields" role="form" method="post">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <div>
                                <label for="userId">User</label> 
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '']" style="width: 250px"/> 
                            </div>
                            <div>
                                <label for="projectRole">Project Role</label>
                                <g:select id="projectRole" name="projectRole" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitToRemote type="button" class="btn btn-primary" value="Save" data-dismiss="modal"
                                              url="[action: 'addUserAjax']"
                                              update="[success: 'project-users']"
                                              onComplete="closeModal()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <div id="editUserRole" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Update user role in project: ${project?.name}</h4>
                    </div>
                    <div class="modal-body">
                        <form class="fields" role="form" method="post">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <g:hiddenField name="userId" id="hiddenUserId"></g:hiddenField>
                            <div>
                                <label for="projectRole">Project Role</label>
                                <g:select id="projectRole" name="projectRole" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitToRemote type="button" class="btn btn-primary" value="Save" data-dismiss="modal"
                                              url="[action: 'editUserRoleAjax']"
                                              update="[success: 'project-users']"
                                              onComplete="closeModal()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <div id="addReplicate" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3>Add Replicate Set</h3>
                    </div>
                    <div class="modal-body">
                        <form role="form" method="post" class="fields">
                            <div>
                                <label>Type</label>
                                <g:select id="replicateType" name="type" from="${pegr.ReplicateType.values()}" keys="${pegr.ReplicateType.values()*.name()}"></g:select>
                            </div>
                            <g:render template="/sample/inputSampleIds"></g:render>
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <g:submitToRemote type="button" class="btn btn-primary" name="save" value="Save" data-dismiss="modal"
                                              url="[controller: 'replicate', action: 'saveAjax']"
                                              update="[success: 'replicates']"
                                              onComplete="closeModal()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </g:if>
    
	<script>
        $(function(){
            $(".confirm").confirm();
            $("#nav-projects").addClass("active");
            $("#userId").select2();
        });

        function removeUser(userId, projectId) {
            if(confirm('Are you sure?')) {
                jQuery.ajax({
                    type:'POST',
                    data:{'userId': userId,'projectId': projectId}, 
                    url:'/pegr/project/removeUserAjax',
                    success:function(data,textStatus){
                                jQuery('#project-users').html(data);
                            }
                });
            }
        }
        
        function showEditRole(userId) {
            $(".modal-body #hiddenUserId").val(userId);
        }
        
        function saveNotes() {
            var projectId = ${project.id};
            var notes = $("#notes").val();
            $.ajax({
                type: 'POST',
                data: {'projectId': projectId, 'notes': notes},
                url: '/pegr/project/saveNotesAjax'
            });
        }
	</script>
</body>
</html>
