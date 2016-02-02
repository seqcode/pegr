<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <h4>Add Traced Item</h4>
    <g:render template="/item/details" bean="${item}" var="item"></g:render>
    <g:if test="${subBag}">
        <p>This item is associated with bag <g:link controller="ProtocolInstanceBag" action="showBag" id="${subBag.id}" target="_blank">${subBag.name}</g:link></p>
    </g:if>
    <g:link action="addItemToBag" params="[itemId: item.id,
                               bagId: bagId]" class="btn btn-primary">Add Item Only</g:link>
    <g:if test="${subBag}">
        <g:link action="addSubBagToBag" params="[subBagId: subBag.id, bagId: bagId]" class="btn btn-primary">Add Entire Bag</g:link>
    </g:if>
    <g:link action="searchItemForBag" id="${bagId}" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>