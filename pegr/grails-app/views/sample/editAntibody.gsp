<html>
<head>
    <title>PEGR - Sample</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<div class="container-fluid">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3>Edit Antibody for Sample ${sampleId}</h3>
    <g:form action="updateAntibody" role="form" method="post">
        <g:hiddenField name="sampleId" value="${sampleId}"></g:hiddenField>
        <g:hiddenField name="antibodyId" value="${antibodyId}"></g:hiddenField>
        <div class="row">
            <div class="col-md-6 fields">
                <h4>Barcode Information</h4>
                <g:render template="/item/formWithBarcode" model="[item: item, 'itemTypeOptions':itemTypeOptions]"></g:render>
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