<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h3>Preview Sequence Run <a href="#" onclick="window.open('${g.createLink(action:'editQueue')}', 'Edit Queue for Sample Submission', 'width=600,height=400' )" class="edit">Edit</a></h3>
    <h4>Previous Run</h4>
    <table>
        <thead>
            <tr>
                <th width="300">Sequence Run</th>
                <th>Wall E Folder</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Run#${previousRun?.runNum} <g:if test="${previousRun?.runNumAlias}">(Old No.${previousRun?.runNumAlias})</g:if></td>
                <td>${previousRun?.directoryName}</td>
            </tr>
        </tbody>
    </table>
    
    <h4>Queued Runs</h4>
    <table>
        <thead>
            <tr>
                <th width="300">Sequence Run</th>
                <th>Wall E Folder</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${queuedRuns}">
            <tr>
                <td>Run#${it.runNum} <g:if test="${it.runNumAlias}">(Old No.${it.runNumAlias})</g:if></td>
                <td>${it.directoryName}</td>    
            </tr>
            </g:each>
        </tbody>
    </table>
    
    <h4>Current Run</h4>
    <table>
        <thead>
            <tr>
                <th width="300">Sequence Run</th>
                <th>Wall E Folder</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Run#${currentRun.runNum} <g:if test="${currentRun.runNumAlias}">(Old No.${currentRun.runNumAlias})</g:if></td>
                <td><g:if test="${currentRun.runNumAlias}">${currentRun.directoryName}</g:if></td>
            </tr>
        </tbody>
    </table>
    <g:form action="run">
        <g:hiddenField name="runId" value="${currentRun.id}"></g:hiddenField>
        <g:submitButton class="btn btn-primary" name="submit" value="Submit"></g:submitButton>
        <g:link action="show" id="${currentRun.id}" class="btn btn-default">Cancel</g:link>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>
