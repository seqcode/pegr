<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h4>Add Pool</h4>
    <g:render template="/item/details" model="['item': poolItem]"></g:render>
    <g:link action="addPool" params="[poolItemId: poolItem.id, runId: runId]" class="btn btn-primary">Add</g:link>
    <g:link action="searchSample" params="[runId: runId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>