<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="/sample/nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Protocol ${protocolInstance?.protocol?.name} for Sample ${sampleId}</h3>
            <h4>Protocol</h4>
            <p>${protocolInstance?.protocol?.details }</p>
            <form role="form">
	
			</form>
			
        </div>
    </div>
    <script>
        $("#nav-sample-protocols").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>