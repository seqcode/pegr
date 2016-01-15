<div class="row">
    <g:each in="${items}" var="item">
        <div class="col-md-3 item">
            <h5>${itemInstance?.type?.name}</h5>
            <g:render template="/item/details" bean="${item}" var="itemInstance" />
            
            <g:if test="${item.id in linkedItemIds}">
                <button class="btn btn-default disabled">Already linked</button>
            </g:if>
            <g:else>
                <g:remoteLink class="btn btn-info" params="[itemId: item.id, prtclInstId: prtclInstId]"
                url="[action: 'linkItemToPrtclInstanceAjax']"
                update="itemList"
                onSuccess="clearItemPreview(data)">Select</g:remoteLink>
            </g:else>
        </div>
    </g:each>
</div>
