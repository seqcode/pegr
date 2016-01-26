<div class="col-md-3 item">
    <g:if test="${!completed}">
        <g:link class="pull-right confirm" action="removeItemFromInstance" params="[itemId: item.id, instanceId: instanceId]"><span class="glyphicon glyphicon-remove-circle"></span></g:link>
    </g:if>
    <g:render template="/item/details" bean="${item}" var="item" />
</div>