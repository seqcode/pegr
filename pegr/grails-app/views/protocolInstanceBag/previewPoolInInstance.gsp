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
    <h3>Add Pool</h3>
    <g:render template="/item/details" bean="${item}" var="item"></g:render>
    <g:link action="addPoolToInstance" params="[itemId: item.id,
                               instanceId: instanceId]" class="btn btn-primary">Add</g:link>
    <g:link action="searchItemForInstance" id="${instanceId}" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>