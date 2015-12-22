<html>
    <head>
      <title>My Projects</title> 
      <meta name="layout" content="main"/>
    </head>
    <body>    
        <ul class="list-group">
        	<g:each var="i" in="${ (0..<1) }">
        	<li class="list-group-item">
	  	    	 <h4>
		           <g:link controler="projects" action="show">Harvest</g:link>
		        </h4>
		        <p>User: Nina; time: 12/15/2015. </p>
		        <button class="btn"><a href="zxing://scan/?ret=http%3A%2F%2F172.29.0.70%3A8080%2Fitem%2F%7BCODE%7D%2Fshow&SCAN_FORMATS=UPC_A,EAN_13">Scan Barcode</a></button>
   			</li>
   			</g:each>
        </ul>
         <button class="btn"><span class="glyphicon glyphicon-plus"></span> Add Protocol</button>
         <script>
			$("#nav-projects").addClass("active");
		 </script>
    </body>
</html>


