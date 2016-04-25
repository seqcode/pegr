<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h3>Preview Sequence Run</h3>
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
                <td>Run#${previousRun.id} <g:if test="${previousRun.runNum}">(Old No.${previousRun.runNum})</g:if></td>
                <td>${previousRun.directoryName}</td>
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
                <td>Run#${it.id} <g:if test="${it.runNum}">(Old No.${it.runNum})</g:if></td>
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
                <td>Run#${currentRun.id} <g:if test="${currentRun.runNum}">(Old No.${currentRun.runNum})</g:if></td>
                <td><g:if test="${currentRun.runNum}">${currentRun.directoryName}</g:if></td>
            </tr>
        </tbody>
    </table>
    <g:form action="run">
        <g:hiddenField name="runId" value="${currentRun.id}"></g:hiddenField>
        <div>
            <h4>Schedule Meeting</h4>
            <g:datePicker name="meetingTime" value="${meetingTime}"/>
        </div>
        <g:submitButton class="btn btn-primary" name="submit" value="Submit"></g:submitButton>
        <g:link action="edit" params="[runId: currentRun.id]" class="btn btn-default">Cancel</g:link>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>