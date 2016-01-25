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

    <g:link action="addItemToInstance" params="[itemId: item.id,
                               instanceId: instanceId]" class="btn btn-primary">Add Item Only</g:link>

    <g:link action="searchItemForInstance" id="${instanceId}" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left"></span> Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>