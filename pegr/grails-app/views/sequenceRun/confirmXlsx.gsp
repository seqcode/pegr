<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h3>Uploading Excel (xlsx) File</h3>
        <p>File: ${filename}</p>
        <p>Sample sheet lines from ${startLine} to ${endLine}</p>
        <p>Lane sheet line: ${laneLine} </p>
        <g:form action="continueXlsx" >
            <div class="form-group">
                <g:hiddenField name="sampleSheet" value="${sampleSheet}"></g:hiddenField>
                <g:hiddenField name="startLine" value="${startLine}"></g:hiddenField>
                <g:hiddenField name="endLine" value="${endLine}"></g:hiddenField>
            </div>
            <div class="form-group">
                <g:hiddenField name="laneSheet" value="${laneSheet}"></g:hiddenField>
                <g:hiddenField name="laneLine" value="${laneLine}"></g:hiddenField>
            </div>            
            <g:each in="${warnings}">
                <div class="message" role="status">
                    Warning: ${it}
                </div>
            </g:each>
            <h4>Do you want to continue?</h4>
            <div class="form-group">
                <g:submitButton name="Continue" value="Continue" class="btn btn-primary"/>
                <g:link action="index" class="btn btn-default">Cancel</g:link>
            </div>
        </g:form>
    </div>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>
