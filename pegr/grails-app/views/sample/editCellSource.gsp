<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
</head>
<body>
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
            $("form").validate();
            var noTagPlaceholder = "Select...";
            $(".no-tag-select2").select2({
                placeholder: noTagPlaceholder
            }); 
        });
    </script>
</body>
</html>