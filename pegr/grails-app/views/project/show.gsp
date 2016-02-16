<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
	<div><g:link action='index'>My Projects</g:link> -> Project ${project?.name} </div>
	<div>
		<h3>Project: ${project?.name} <g:link action="edit" id="${project?.id}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus">Edit</span></g:link></h3>
		<p>Created: ${project?.dateCreated}, updated: ${project?.lastUpdated}</p>
		<p>Description: ${project?.description}</p>
        <p>Funding: ${project?.funding}</p>
        
        <h3>Users <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#addUser"><span class="glyphicon glyphicon-plus">Add</span></button></h3>
        <div id="addUser" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Add User to Project: ${project?.name}</h4>
                    </div>
                    <div class="modal-body">
                        <form class="fields" role="form" method="post">
                            <g:hiddenField name="project.id" value="${project.id}"></g:hiddenField>
                            <div>
                                <label for="userId">User</label> 
                                <g:select id="userId" name="user.id" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <div>
                                <label for="projectRole">Project Role</label>
                                <g:select id="projectRole" name="projectRole" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitToRemote type="button" class="btn btn-primary" value="Save" data-dismiss="modal"
                                              url="[action: 'addUserAjax']"
                                              update="[success: 'project-users']"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        <div id="project-users">
            <g:render template="userTable" bean="${projectUsers}" />
        </div>
        
		<h3>Samples</h3>
        <g:link controller="sample" action="create" params="[projectId: project?.id]" class="btn btn-info">Create New Sample</g:link>
        <g:link action="addSample" class="btn btn-info">Add Existing Sample</g:link>
        <g:render template="/sample/table" model="['sampleList':project?.samples]" />
		<div class="pagination">
            <g:paginate total="${sampleCount ?: 0}" />
        </div>
	</div>
		
	<script>
		$("#nav-projects").addClass("active");
	</script>
</body>
</html>