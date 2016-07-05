<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <h3>Edit Antibody for Sample ${sampleId}</h3>
    <g:form action="updateAntibody" role="form" method="post">
        <g:hiddenField name="sampleId" value="${sampleId}"></g:hiddenField>
        <div class="row">
            <div class="col-md-6 fields">
                <h4>Barcode Information</h4>
                <g:render template="/item/formWithBarcode" model="[item: item]"></g:render>
            </div>
            <div class="col-md-6 fields">
                <h4>Antibody Information</h4>
                <g:render template="/antibody/form" model="['object': antibody]"></g:render>
            </div>
        </div>
        <div class="text-center well">
            <g:submitButton class="btn btn-primary" name="save" value="Save"/>
            <g:link action="edit" class="btn btn-default" params="[sampleId: sampleId]">Cancel</g:link>
        </div>
    </g:form>
    
    <script>
        $("#nav-metadata").addClass("active");
     </script>
</div>
</body>
</html>