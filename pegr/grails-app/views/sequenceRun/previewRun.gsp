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
                <td>#${previousRun.id} <g:if test="${previousRun.runNum}">(Old No.${previousRun.runNum})</g:if></td>
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
            <tr>
                <g:each in="${queuedRuns}">
                    <td>#${it.id} <g:if test="${it.runNum}">(Old No.${it.runNum})</g:if></td>
                    <td>${it.directoryName}</td>
                </g:each>
            </tr>
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
                <td>#${currentRun.id} <g:if test="${currentRun.runNum}">(Old No.${currentRun.runNum})</g:if></td>
                <td><g:if test="${currentRun.runNum}">${currentRun.directoryName}</g:if></td>
            </tr>
        </tbody>
    </table>
    
    <g:link action="run" params="[runId: currentRun.id]" class="btn btn-primary">Submit</g:link>
    <g:link action="edit" params="[runId: currentRun.id]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>