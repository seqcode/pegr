<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
	<div><g:link action='index'>My Projects</g:link> -> Project ${project?.name} </div>
	<div class="col-sm-8">
		<h3>Project: ${project?.name} <g:link action="edit" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus">Edit</span></g:link></h3>
		<p>Created: ${project?.dateCreated}, updated: ${project?.lastUpdated}</p>
		<p>Description: ${project?.description}</p>
        <p>Funding: ${project?.funding}</p>
        
		<h3>Users <g:link action="addUser" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus">Add</span></g:link></h3>
        <g:render template="userTable" bean="${projectUsers}" />
        
		<h3>Samples</h3>
        <g:link controller="sample" action="create" params="[projectId: project?.id]" class="btn btn-info">Create New Sample</g:link>
        <g:link action="addSample" class="btn btn-info">Add Existing Sample</g:link>
        <g:render template="/sample/table" bean="${project?.samples}" />
        
		<p>B: <abbr title="What is biological replication">biological replication</abbr>; 
		T: <abbr title="What is technical replication">technical replication</abbr>.</p>
		
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