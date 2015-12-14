<html>
    <head>
      <title>My Projects</title> 
      <meta name="layout" content="main"/>
    </head>
    <body>    
        <button class="btn"><span class="glyphicon glyphicon-plus"></span> Add Project</button>
        <ul class="list-group">
        	<g:each var="i" in="${ (0..<3) }">
        	<li class="list-group-item">
	  	    	 <h4>
		           <g:link controler="projects" action="show">Project: xxx-xxxx</g:link>
		        </h4>
		        <p>Created: 12/12/2015; Updated: 12/15/2015. </p>
   			</li>
   			</g:each>
        </ul>
         <script>
			$("#nav-projects").addClass("active");
		 </script>
    </body>
</html>


