<div id="create-protocol" class="modal fade" role="dialog">
   <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Create New Protocol</h3>
            </div>
            <div class="modal-body">
                <div id="create-protocol-error">
                    <g:if test="${message}">
                        <div class="message" role="status">${message}</div>
                    </g:if>
                </div>
                
                <form role="form" method="post" class="fields">
                    <g:render template="/protocolAdmin/form" model="[protocolGroup: protocolGroupInstance]"></g:render>
                    <div class="form-group">
                        <label><input type="file" id="file" name="file"/>(only pdf files)</label>
                    </div>
                    <g:submitToRemote type="button" class="btn btn-primary" name="save" value="Save" data-dismiss="modal"
                                      url="[controller: 'protocolInstanceBag', action: 'saveProtocolAjax']"
                                      update="[success: 'select-protocols', failure: 'create-protocol-error']"
                                      onSuccess="afterCreateProtocol()"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</div>