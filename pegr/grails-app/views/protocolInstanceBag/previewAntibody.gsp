<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h3>Add Antibody</h3>
    <g:render template="/antibody/details" bean="${antibody}" var="object"></g:render>

    <g:link action="addAntibodyToSample" params="[antibodyId: antibody.id, itemId: itemId, instanceId: instanceId]" class="btn btn-primary">Add</g:link>

    <g:link action="searchAntibody" params="[itemId: itemId, instanceId: instanceId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-experiments").addClass("active");
     </script>
</div>
</body>
</html>