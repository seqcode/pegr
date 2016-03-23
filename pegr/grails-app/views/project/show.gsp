<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
	<div><g:link action='index'>My Projects</g:link> -> Project ${project?.name} </div>
	<div>
		<h3>Project: ${project?.name} <g:if test="${authorized}"><g:link action="edit" params="[projectId:project?.id]" class="edit">Edit</g:link></g:if></h3>
		<p>Created: ${project?.dateCreated}, updated: ${project?.lastUpdated}</p>
		<p>Description: ${project?.description}</p>
        <p>Funding: ${project?.funding}</p>
        
        <h3>Users <g:if test="${authorized}"><button class="edit" data-toggle="modal" data-target="#addUser">Add</button></g:if></h3>
        <div id="project-users">
            <g:render template="userTable"/>
        </div>
        
		<h3>Samples</h3>
        <g:link action="addSamples" params="[projectId: project?.id]" class="btn btn-info">Create New Sample</g:link>
        <g:link action="addSample" class="btn btn-info">Add Existing Sample</g:link>
        <g:render template="/sample/table" model="['sampleList':project?.samples]" />
		<div class="pagination">
            <g:paginate total="${sampleCount ?: 0}" />
        </div>
	</div>
		
    <g:if test="${authorized}">
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
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '-- choose --']" /> 
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
                            <div>
                                <label for="userId">User</label> 
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
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
    </g:if>
    
	<script>
        $(function(){
            $("#nav-projects").addClass("active");
            if (!${authorized}) {
                $(".edit").hide();
            }
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
            $(".modal-body #userId").val(userId);
        }

        function closeModal() {
            $(".modal").modal('hide');
        }
	</script>
</body>
</html>