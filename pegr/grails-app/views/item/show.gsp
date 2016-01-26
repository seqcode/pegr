<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Item</title>
</head>
<body>
<g:if test="${itemInstance?.name}">
    <h4>Name: ${itemInstance.name}</h4>
</g:if>
<ul>    
    <g:if test="${itemInstance?.type}">
    <li>Type: ${itemInstance.type}</li>
    </g:if>

    <g:if test="${itemInstance?.barcode}">
    <li>Barcode: ${itemInstance.barcode }</li>
    </g:if>	

    <g:if test="${itemInstance?.location}">
    <li>Location: ${itemInstance.location}</li>
    </g:if>	

    <g:if test="${itemInstance?.notes}">
    <li>Notes: ${itemInstance.notes}</li>
    </g:if>

    <g:if test="${itemInstance?.parent}">
    <li>Parent: <g:link controller="item" action="show" id="${itemInstance.parent.id}">${itemInstance.parent.name}</g:link></li>
    </g:if>
</ul>
</body>
</html>