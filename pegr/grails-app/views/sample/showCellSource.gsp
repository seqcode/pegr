<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Cell Source</h3>
			<g:render template="cellSourceDetails" 
						model="['cellSourceInstance': cellSourceInstance, 
						'itemInstance': itemInstance]" />
			<g:link class="btn btn-primary" >Edit</g:link>
			<g:link class="btn btn-primary">Delete</g:link>
        </div>
    </div>
    <script>
        $("#nav-sample-cellsource").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>
