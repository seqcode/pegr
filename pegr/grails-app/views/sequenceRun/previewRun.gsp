<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h3>Preview Sequence Run</h3>
    <h4>Previous Run</h4>
    <p>Sequence Run #${previousRun.id} <g:if test="${previousRun.runNum}">(Old No.${previousRun.runNum})</g:if></p>
    <p>Wall E Folder: ${previousRun.directoryName}</p>
    <h4>Current Run</h4>
    <p>Sequence Run #${currentRun.id} <g:if test="${currentRun.runNum}">(Old No.${currentRun.runNum}</g:if></p>
    <p>Wall E new folders: </p>
    <g:if test="${newFolders.size()"}>
        <ul>
            <g:each in="${newFolders}"><li>${it}</li></g:each>
        </ul>
        <div class="message">The current sequence run will be linked to folder ${newFolders.first()}.</div>
    </g:if>
    <g:else>
        <div class="message">None. The current sequence run will be linked to the next new folder created on Wall E.</div>
    </g:else>
    <g:link action="run" params="[runId: runId]" class="btn btn-primary">Submit</g:link>
    <g:link action="edit" params="[runId: runId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>