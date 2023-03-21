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
        <div id="showLinks">
          <strong>Linkouts: </strong>
          <g:each in="${links}" var="link">
          <g:if test="${link.url}">
            <a href="${link.url}" class="btn btn-primary">${link.name}</a>
          </g:if>
          <g:else>
            <a href="${link.url}" class="btn btn-default">${link.name}</a>
          </g:else>
          </g:each>
          <g:link action="editLinks" params="[projectId:project?.id]" class="btn btn-link" >Edit</g:link>
        </div>
        <h3>Users <g:if test="${projectEditAuth}"><button class="edit" data-toggle="modal" data-target="#addUser">Add</button></g:if></h3>
        <div id="project-users">
            <g:render template="userTable"/>
        </div> 
        
        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
        <h3>Experiments <g:link class="edit" controller="protocolInstanceBag" action="list" params="[projectId:project.id]">link</g:link></h3>
        </sec:ifAnyGranted>
        
        <h3>Sequencing Runs</h3>
        <p>Click each run to see the list of samples</p>
        <ul>
            <g:each in="${project.cohorts}" var="cohort" status="n">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a data-toggle="collapse" href="#collapse${n}">${cohort}</a> 
                                <g:if test="${cohort.report && cohort.report.status == pegr.ReportStatus.PUBLISH}"><g:link controller="report" action="show" id="${cohort.report?.id}">Report: ${cohort.report?.name}</g:link> 
                                </g:if>
                            </h4>
                        </div>
                    </div>
                    <br>
                    <div>
                        <p><i>Notes:</i> 
                        <span class="cohort-notes-input" style="display:none">
                            <g:hiddenField class="cohort-id" name="id" value="${cohort.id}"></g:hiddenField>
                            <input class="cohort-notes-text" name="notes" value="${cohort.notes}">
                            <button class="cohort-notes-save btn btn-primary">Save</button>
                            <button class="cohort-notes-cancel btn btn-default">Cancel</button>
                        </span>                            
                        <i class="cohort-notes-show">${cohort.notes}</i> <button class="cohort-notes-edit btn btn-default">Edit</button>
                        </p>
                    </div>
                    <div id="collapse${n}" class="panel-collapse collapse">
                        <g:render template="/project/sampleTable" model="['sampleList':cohort.samples, 'project':project]" />
                    </div>
                </div>  
            </g:each>
        </ul>
        <h4>Other Samples</h4>
        <g:render template="/project/sampleTable" model="['sampleList':otherSamples, 'project':project, 'removable':true]" />
        <g:if test="${sampleEditAuth}">
            <div>
                <button data-toggle="modal" data-target="#selectAssay" class="btn btn-info">Create New Samples</button>
                <g:link action="searchSample" params="[projectId: project?.id]" class="btn btn-info">Add Existing Sample</g:link>
            </div>
        </g:if>
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
                            <input onclick="jQuery.ajax({type:'POST',data:jQuery(this).parents('form:first').serialize(), url:'/pegr/project/addUserAjax',success:function(data,textStatus){jQuery('#project-users').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){},complete:function(XMLHttpRequest,textStatus){closeModal()}});return false" type="button" value="Save" class="btn btn-primary">
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
                            <input onclick="jQuery.ajax({type:'POST',data:jQuery(this).parents('form:first').serialize(), url:'/pegr/project/editUserRoleAjax',success:function(data,textStatus){jQuery('#project-users').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){},complete:function(XMLHttpRequest,textStatus){closeModal()}});return false" type="button" value="Save" class="btn btn-primary">
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
        
        $(".cohort-notes-edit").click(function(){
            var div = $(this).closest("div");
            div.find(".cohort-notes-show").hide()
            $(this).hide();
            div.find(".cohort-notes-input").show();
        });

        $(".cohort-notes-save").click(function(){
            var div = $(this).closest("div");
            var notes = div.find(".cohort-notes-text").val();
            var id = div.find(".cohort-id").val();
            $.ajax({ url: "/pegr/project/updateCohortNotesAjax?cohortId=" + id + "&notes=" + notes,
                success: function(result) {
                    div.find(".cohort-notes-show").text(result);
                    div.find(".cohort-notes-text").val(result);
                    div.find(".cohort-notes-show").show();
                    div.find(".cohort-notes-edit").show();
                    div.find(".cohort-notes-input").hide();
                }                
            });
        });

        $(".cohort-notes-cancel").click(function(){
            var div = $(this).closest("div");
            div.find(".cohort-notes-show").show();
            div.find(".cohort-notes-edit").show();
            div.find(".cohort-notes-input").hide();
        });
	</script>
</body>
</html>
