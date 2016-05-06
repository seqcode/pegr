<div class="modal fade" id="new-treatment" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:formRemote name="addTreatment" method="post" update="treatments" onSuccess="cleanup();" url="[controller:'cellSource', action:'addTreatment']">
                <div class="modal-header">New Treatment</div>
                <div class="modal-body fields">
                    <g:hiddenField name="cellSourceId" value="${cellSource?.id}"></g:hiddenField>
                    <div>
                        <label>Name</label>
                        <g:textField name="name"></g:textField>
                    </div>
                    <div>
                        <label>Note</label>
                        <g:textArea name="note" maxlength="50"></g:textArea>
                    </div>
                </div>
                <div class="modal-footer">
                    <g:submitButton class="btn btn-primary" name="save">Save</g:submitButton>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </g:formRemote>
        </div>
    </div>
</div>
<script>
    function cleanup() {
        $('#new-treatment').modal('hide');
    }
</script>