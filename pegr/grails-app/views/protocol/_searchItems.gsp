<button class="btn btn-default" onClick="resetSearchForm()">Cancel</button>
<div class="row">
    <g:each in="${items}" var="item">
        <div class="col-md-3 item">
            <h5>${itemInstance?.type?.name}</h5>
            <g:render template="/item/details" bean="${item}" var="itemInstance" />
            <g:remoteLink class="btn btn-default" params="[itemId: item.id, prtclInstId: prtclInstId]"
                url="[action: 'linkItemToPrtclInstanceAjax']"
                update="itemList"
                onSuccess="clearForm(data)">Select</g:remoteLink>
        </div>
    </g:each>
</div>
