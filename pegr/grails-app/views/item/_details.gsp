
<ul>
    <g:if test="${itemInstance?.barcode}">
    <li>Barcode: ${itemInstance.barcode }</li>
    </g:if>	

    <g:if test="${itemInstance?.location}">
    <li>Location: ${itemInstance.location}</li>
    </g:if>	

    <g:if test="${itemInstance?.notes}">
    <li>Notes: ${itemInstance.notes}</li>
    </g:if>
</ul>