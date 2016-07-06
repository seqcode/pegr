<html>
<head>
    <title>PEGR - Sample</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h3>Add Antibody</h3>
    <g:render template="/antibody/details" bean="${antibody}" var="object"></g:render>

    <g:link action="addAntibodyToSample" params="[antibodyId: antibody.id, sampleId: sampleId]" class="btn btn-primary">Add</g:link>

    <g:link action="searchAntibody" params="[sampleId: sampleId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-metadata").addClass("active");
     </script>
</div>
</body>
</html>