<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>New Sequencing Run</h4>
    <g:form role="form" method="post" action="save" class="fields">
        <div>
            <label>Run #</label>
            <g:textField name="runNum" value="${defaultRunNum}"></g:textField>
        </div>
        <div>
            <label>Platform</label>
            <g:select name="platform.id" optionKey="id" from="${pegr.SequencingPlatform.list()}" noSelection="['null': '-- choose --']" value="${run?.platform?.id}"/>
        </div>
        <div>
            <label>Date</label>
            <g:datePicker name="date" default="${new Date()}"/>
        </div>
        <div>
            <label>Note</label>
            <g:textArea name="note" value="${run?.note}"></g:textArea>
        </div>
        <g:submitButton class="btn btn-primary" name="Save"/>
        <g:link action="index" class="btn btn-default">Cancel</g:link>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>