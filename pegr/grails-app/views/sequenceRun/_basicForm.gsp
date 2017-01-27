<div>
    <label>Platform</label>
    <g:select name="platform.id" optionKey="id" from="${pegr.SequencingPlatform.list()}" noSelection="['null': '-- choose --']" value="${run?.platform?.id}"/>
</div>
<div>
    <label>Date</label>
    <g:datePicker name="date" default="${run?.date ?: new Date()}"/>
</div>
<div>
    <label>Old Run No.</label>
    <g:textField name="runNum" value="${run?.runNum?:defaultRunNum}"></g:textField>
</div>
<div>
    <label>Directory Name</label>
    <g:textField name="directoryName" value="${run?.directoryName}"></g:textField>
</div>
<div>
    <label title="Normally, FC ID is a substring of the directory name, starting from the second character of the last section.">FC ID</label>
    <g:textField name="fcId" value="${run?.fcId}"></g:textField>
</div>
<div>
    <label>Lane</label>
    <g:textField name="lane" value="${run?.lane}"></g:textField>
</div>
<div>
    <label>Notes</label>
    <g:textArea name="note" value="${run?.note}"></g:textArea>
</div>