<div class="col-md-3 item">
    <a class="pull-right" data-toggle="modal" data-target="#modal-${item.id}"><span class="glyphicon glyphicon-remove-circle"></span>
    </a>
    <g:render template="/item/details" bean="${item}" var="item" />

    <div id="modal-${item.id}" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <p>Are you sure you want to remove this item?</p>
          </div>
          <div class="modal-footer">
                <g:link class="btn btn-primary" action="removeItemFromInstance" params="[itemId: item.id, instanceId: instanceId]">Yes</g:link>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
          </div>
        </div>

      </div>
    </div>
</div>