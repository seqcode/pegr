<img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:item?.barcode, width:100, height:100, formatStr:"QR"])}'/>
<g:form controller="item" action="printBarcode">
    <g:hiddenField name="itemId" value="${item?.id}"></g:hiddenField>
    <g:submitButton name="print" value="Print" class="btn btn-primary"></g:submitButton>
    Row<input name="row" value="1" size="2">
    Column<input name="col" value="1" size="2">
</g:form>
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
        <button class="btn btn-default" onclick="refreshBarcode()">Refresh</button>
    </div>
</div>
<script>
    function refreshBarcode() {
        var width = $("#width").val();
        var height = $("#height").val();
        var format = $("#format").val();
        var s = '${createLink(controller:"item", action:"displayBarcode", params:[barcode:item?.barcode])}';
        s += "&width=" + width + "&height=" + height + "&formatStr=" + format;
        $("img").attr("src", s);
    }
</script>