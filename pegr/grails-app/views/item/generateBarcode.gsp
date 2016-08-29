<html>
<head>
    <title>Workbench</title> 
</head>
<body>
    <div class="container-fluid">
        <h4>Generate Barcode</h4>
        <div class="row">
            <div class="col-sm-6">
                <g:form controller="item" action="preview" class="fields">
                    <div>
                        <label>Item Type</label>
                        <g:select name="typeId" from="${pegr.ItemType.list()}" optionKey="id" value="${itemType?.id}"></g:select>
                    </div>
                    <div>
                        <label>Barcode</label>
                        <input type="text" name="barcode" value="${barcode}" readonly>
                    </div>
                    <div>
                        <g:submitButton name="submit" value="Create" class="btn btn-primary"></g:submitButton> 
                        <g:link controller="item" action="list" class="btn btn-default">Cancel</g:link>
                    </div>
                </g:form>
            </div>
            <div class="col-sm-6">
                <g:render template="/item/barcodeImage" model="[barcode:barcode]"></g:render>
            </div>
        </div>
    </div>
    <script>
        $("select").select2();
    </script>
</body>
</html>