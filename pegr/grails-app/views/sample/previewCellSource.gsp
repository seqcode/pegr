<html>
<head>
    <title>PEGR - Sample</title> 
    <meta name="layout" content="main"/>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <h4>Add Cell Source</h4>
    <g:render template="/item/details" bean="${item}" var="item"></g:render>
    <g:link action="addCellSourceToSample" params="[itemId: item.id,
                               sampleId: sampleId]" class="btn btn-primary">Add</g:link>
    <g:link action="searchCellSource" params="[sampleId:sampleId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-metadata").addClass("active");
     </script>
</div>
</body>
</html>