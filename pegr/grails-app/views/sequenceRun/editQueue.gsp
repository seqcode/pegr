<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="sequencing"/>
    <style>
        form {
            max-width: 50em;
            padding: 10px;
        }
    </style>
</head>
<body>
    <h3>Update Sequence Queue</h3>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <p>Sequence Runs are queued before they are sent to sequencer repository.</p>
    <g:form controller="sequenceRun" action="updateQueue">
        <g:hiddenField name="runId" value="${runId}"></g:hiddenField>
        <div class="form-group">
            <label>Sequencer repository folder for the previous run </label>
            <input name="previousRunFolder" value="${previousRunFolder}" class="form-control">
        </div>
        <div class="form-group">
            <label>Sequence runs in queue</label>
            <input name="queuedRuns" value="${queuedRuns}" class="form-control">
            <p>Multiple run IDs should be delimited by "," </p>
        <div class="form-group">
        <g:submitButton name="submit" value="save" class="btn btn-primary"></g:submitButton>
        <a href="#" class="btn btn-default" onclick="window.close()">Close</a>
    </g:form>
    
</body>
</html>
