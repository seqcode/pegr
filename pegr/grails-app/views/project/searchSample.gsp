<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.messages}">
        <g:each in="${flash.messages}"><div class="message" role="status">${it}</div></g:each>
    </g:if>
    <div>
        <h3>Add sample to <g:link action="show" id="${project?.id}">Project ${project?.name}</g:link></h3>
        <g:form class="fields" method="post" action="addExistingSamples">
            <g:hiddenField name="projectId" value="${project?.id}"></g:hiddenField>
            <div>
                <label for="sampleIds">Sample IDs</label>
                <g:textField id="sampleIds" name="sampleIds" size= "100"></g:textField>
                <p>(Multiple sample IDs shall be separated by ",")</p>
            </div>
            <g:submitButton name="Add" value="Add" class="btn btn-primary"></g:submitButton>
        </g:form>
    </div>
    <script>
        $(function(){
            $("#nav-projects").addClass("active");
        });
    </script>
</body>
</html>