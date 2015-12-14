<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
	<div><g:link controler="projects">My Projects</g:link>> -> Project xxx-xxxx </div>
	<div class="col-sm-8">
		<h2>Project: xxx-xxxx </h2>
		<p>Created: 12/12/2015, updated: 12/15/2015</p>
		<p>some description here...</p>
		<h3>Users</h3>
		<p>xxx, xxx</p>
		<h3>Samples</h3>
		<button class="btn">New Sample</button>
		<p>B: <abbr title="What is biological replication">biological replication</abbr>; 
		T: <abbr title="What is technical replication">technical replication</abbr>.</p>
		<div class="tree"><span class="bio-rep node">B</span>
		  <ul>
		    <li><span class="tech-rep node">T</span>      
		      <ul>
		        <li><span class="node">S-1 <button>Status</button> 
		        <a href="#"><span class="glyphicon glyphicon-pencil"></span></a> 
		        <a href="#"><span class="glyphicon glyphicon-registration-mark"></span></a>
		        <a href="#"><span class="glyphicon glyphicon-remove"></span></a>
		        </span></li>
		        <li><span class="node">S-2 </span></li>
		      </ul></li>
		    <li><span class="tech-rep node">T</span>
		      <ul>
		        <li><span class="node">S-3 </span></li>
		        <li><span class="node">S-4 </span></li>
		      </ul>
		    </li>
		  </ul>
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