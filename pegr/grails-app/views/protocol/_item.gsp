<div class="col-md-3 item">
	<h5>${itemInstance?.type?.name}
        <a class="pull-right" data-toggle="modal" data-target="#modal-${itemInstance.id}">
            <span class="glyphicon glyphicon-remove-circle"></span>
        </a>
    </h5>
    <g:render template="/item/details" bean="${itemInstance}" var="itemInstance" />

    <div id="modal-${itemInstance.id}" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Do you want to remove the item from this protocol instance?</p>
                </div>
                <div class="modal-footer">          
                    <g:remoteLink type="button" class="btn btn-primary" params="[itemId: itemInstance.id, prtclInstId: prtclInstId]"
                url="[action: 'removeItemFromPrtclInstanceAjax']"
                update="itemList" data-dismiss="modal"
                onComplete="clearModal()" >Ok</g:remoteLink>            
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>