<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Type</th>
                <th>Name</th>
                <th>Location</th>
                <th>Barcode</th>
                <th>Notes</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${itemList}">
            <tr>
                <td class="col-sm-2" rowspan="${Math.max(1, it.items.size())}">${it.type} 
                    <g:if test="${edit}"><g:link action="searchItemForTypeInstance" params="[instanceId:instanceId, typeId:it?.type?.id]"><span class="glyphicon glyphicon-plus"></span></g:link></g:if>
                </td>
                <g:each in="${it.items}" var="item"  status="counter">
                    <g:if test="${counter>0}"><tr></g:if>
                    <td class="col-sm-2"><g:link controller="item" action="show" id="${item.id}" target="_blank">${item.name} </g:link></td>
                    <td class="col-sm-2">${item.location}</td>
                    <td class="col-sm-2">${item.barcode}</td>
                    <td class="col-sm-2">${item.notes}</td>
                    <td><g:if test="${edit}"><g:link class="confirm" action="removeItemFromInstance" params="[itemId: item.id, instanceId: instanceId]"><span class="glyphicon glyphicon-remove"></span></g:link></g:if></td>
                    </tr>
                </g:each>
            </g:each>
            <g:if test="${extra && edit}">
                <tr>
                    <td colspan="5"><g:link action="searchItemForInstance" params="[id:instanceId]">Add <span class="glyphicon glyphicon-plus"></span></g:link></td>
                    <td></td>
                </tr>
            </g:if>
            <tr>
                <td colspan="5"></td>
            </tr>
        </tbody>
      </table>
</div>