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

    <g:if test="${item?.user}">
    <li>User: ${item.user}</li>
    </g:if>
    
    <g:if test="${item?.notes}">
    <li>Notes: ${item.notes}</li>
    </g:if>    
</ul>