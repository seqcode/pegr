<html>
<head>
    <meta name="layout" content="sequencing"/>
</head>
<body>
    <div class="container-fluid">
        <h3>Uploading CSV File</h3>
        <p>File: ${filename}</p>
        <p>Sample sheet lines from ${startLine} to ${endLine}</p>
        <g:form action="continueCsv" >
            <div class="form-group">
                <g:hiddenField name="sampleSheet" value="${filepath}"></g:hiddenField>
                <g:hiddenField name="startLine" value="${startLine}"></g:hiddenField>
                <g:hiddenField name="endLine" value="${endLine}"></g:hiddenField>
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
</body>
</html>
