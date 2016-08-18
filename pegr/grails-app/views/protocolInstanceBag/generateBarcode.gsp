<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h4>Generate Barcode</h4>
        <div class="row">
            <div class="col-sm-6">
                <g:form controller="protocolInstanceBag" action="previewItemInInstance" class="fields">
                    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
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
                        <g:link controller="protocolInstanceBag" action="showInstance" id="${instanceId}" class="btn btn-default">Cancel</g:link>
                    </div>
                </g:form>
            </div>
            <div class="col-sm-6">
                <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:100, height:100, formatStr:"QR"])}'/>
                <div class="fields">
                    <div>
                        <label>Height</label>
                        <input id="height" type="number" name="height" value="100">
                    </div>
                    <div class="form-group">
                        <label>Width</label>
                        <input id="width" type="number" name="width" value="100">
                    </div>
                    <div class="form-group">
                        <label>Format</label>
                        <select id="format" name="formatStr">
                            <option value="QR" selected>QR</option>
                            <option value="Code 39">Code 39</option>
                        </select>
                    </div>
                    <div>
                        <button class="btn" onclick="refreshBarcode()">Refresh</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-experiments").addClass("active");
        $("select").select2();
        
        function refreshBarcode() {
            var width = $("#width").val();
            var height = $("#height").val();
            var format = $("#format").val();
            var s = '${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode])}';
            s += "&width=" + width + "&height=" + height + "&formatStr=" + format;
            $("img").attr("src", s);
        }
     </script>
</body>
</html>