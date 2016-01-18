<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
	<div><g:link action='index'>My Projects</g:link> -> Project ${project?.name} </div>
	<div class="col-sm-8">
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
        <g:render template="/sample/table" bean="${project?.samples}" />
		<div class="pagination">
            <g:paginate total="${sampleCount ?: 0}" />
        </div>
	</div>
    <div class="col-sm-4" style="padding: 10px 0">
      <div class="well">
    	<h3>Timeline</h3>
		<div>
		    <ul class="timeline">
		        <li><!---Time Line Element--->
		          <div class="timeline-badge up"><i class="fa fa-thumbs-up"></i></div>
		          <div class="timeline-panel">
		            <div class="timeline-heading">
		              <h4 class="timeline-title">Time Line Entry #1</h4>
		            </div>
		            <div class="timeline-body"><!---Time Line Body&Content--->
		              <p>Time line content is placed here...</p>
		            </div>
		          </div>
		        </li>
		        <li><!---Time Line Element--->
		          <div class="timeline-badge down"><i class="fa fa-thumbs-down"></i></div>
		          <div class="timeline-panel">
		            <div class="timeline-heading">
		              <h4 class="timeline-title">Time Line Entry #2</h4>
		            </div>
		            <div class="timeline-body"><!---Time Line Body&Content--->
		              <p>Time line content is placed here...</p>
		              <p>And some more Time line content </p>
		            </div>
		          </div>
		        </li>
		        <li><!---Time Line Element--->
		          <div class="timeline-badge neutral"><i class="fa fa-navicon"></i></div>
		          <div class="timeline-panel">
		            <div class="timeline-heading">
		              <h4 class="timeline-title">Time Line Entry #3</h4>
		            </div>
		            <div class="timeline-body"><!---Time Line Body&Content--->
		              <p>Time line content is placed here...</p>
		              <p>This appears to be a neutral time line enty...</p>
		            </div>
		          </div>
		        </li>
		    </ul>
		</div>
	  </div>
	    <div class="well">
	        <h4>Posts</h4>
	        <p>User1: blablabla</p>
	        <p>User2: blablabla</p>
	        <p>User3: blablabla</p>
	    </div>
    </div>
		
	<script>
		$("#nav-projects").addClass("active");
	</script>
</body>
</html>