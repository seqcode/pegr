<div class="col-md-3 item">
	<h5>${cellSource?.Strain?.name}
        <a class="pull-right" data-toggle="modal" data-target="#modal-${cellSource.id}">
            <span class="glyphicon glyphicon-remove-circle"></span>
        </a>
    </h5>
    <g:render template="details" bean="${cellSource}" />

    <div id="modal-${cellSource.id}" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Do you want to remove ${cellSource?.Strain?.name} from this protocol instance bag?</p>
                </div>
                <div class="modal-footer">          
                    <g:remoteLink type="button" class="btn btn-primary" params="[cellSourceId: cellSource.id, bagId: bagId]"
                url="[action: 'removeItemFromPrtclInstanceAjax']"
                update="itemList" data-dismiss="modal"
                onComplete="clearModal()" >Ok</g:remoteLink>            
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>