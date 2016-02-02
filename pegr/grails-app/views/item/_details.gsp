<g:if test="${item?.name}">
<h4>Name: ${item.name}</h4>
</g:if>
<ul>
    <g:if test="${item?.type}">
    <li>Type: ${item.type}</li>
    </g:if>
    
    <g:if test="${item?.barcode}">
    <li>Barcode: ${item.barcode }</li>
    </g:if>	

    <g:if test="${item?.location}">
    <li>Location: ${item.location}</li>
    </g:if>	

    <g:if test="${item?.notes}">
    <li>Notes: ${item.notes}</li>
    </g:if>
    
    
    <li>Parent: <g:if test="${item?.parent}"><g:link controller="item" action="show" id="${item.parent.id}">${item.parent.name}</g:link></g:if> (<g:link action="updateParent" params="[itemId: item.id]">Update</g:link>)</li>
    
</ul>