<div id="create-protocol" class="modal fade" role="dialog">
   <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Create New Protocol</h3>
            </div>
            <div class="modal-body">
                <form role="form" method="post" class="fields">
                    <g:render template="/protocolAdmin/form" model="[protocolGroup: protocolGroupInstance]"></g:render>
                    <g:submitToRemote type="button" class="btn btn-primary" name="save" value="Save" data-dismiss="modal"
                                      url="[controller: 'protocolInstanceBag', action: 'saveAjax']"
                                      update="[success: 'select-protocols']"
                                      onComplete="afterCreateProtocol()"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</div>