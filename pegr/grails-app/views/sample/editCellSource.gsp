<html>
<head>
    <meta name="layout" content="main">
    <title>PEGR - Sample</title>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
    <div class="container-fluid">
        <h3>Edit Sample ${sample?.id}</h3>
        <g:form action="updateCellSource" class="fields">
            <g:hiddenField name="cellSourceId" value="${cellSourceId}"></g:hiddenField>
            <g:hiddenField name="sampleId" value="${sampleId}"></g:hiddenField>
            <h4>Barcode Information</h4>
            <g:render template="/item/formWithBarcode" model="['item': item, 'itemTypeOptions':itemTypeOptions]"></g:render>
            <h4>Cell Source Information</h4>
            <g:render template="/cellSource/form" model="['cellSource': cellSource]"></g:render>
            <div>
                <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                <g:link action="edit" params="[sampleId: sampleId]" class="btn btn-default">Cancel</g:link>
            </div>
        </g:form>
    </div> 
    <script>
        $(document).ready(function(){
            $("#nav-metadata").addClass("active");
        });
    </script>
</body>
</html>