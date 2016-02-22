<div>
    <label>Run #</label>
    <g:textField name="runNum" value="${run?.runNum?:defaultRunNum}"></g:textField>
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