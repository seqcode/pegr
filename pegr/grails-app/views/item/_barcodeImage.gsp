<img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:item?.barcode, width:100, height:100, formatStr:"QR"])}'/>
<g:form controller="item" action="printBarcode">
    <g:hiddenField name="itemId" value="${item?.id}"></g:hiddenField>
    <g:submitButton name="print" value="Print" class="btn btn-primary"></g:submitButton>
    <div>
        <input type="radio" id="template-src" name="template_src" value="select" checked>
        <label>Select a template</label>
        <select name="template">
            <option> --Select --</option>
            <option value="1">Rectangular Cryotags LCRY-1700</option>
            <option value="2">Circular Spot5000</option>
            <option value="3">Rectangular DYMO</option>
            <option value="4">Circular DYMO</option>
        </select>
    </div>
    <div>
        <input type="radio" id="template-src" name="template_src" value="customize">
        <label>Customize your template (mm)</label>
    </div>
    <div id="custom-template" class="fields" style="margin-left: 12px">
        <div class="form-group">
            <label>Page size: Width</label> <input name="pageWidth" size="5"> 
            <label>Height</label> <input name="pageHeight" size="5">
        </div>
        <div class="form-group">
            <label>Page margins: Top</label> <input name="marginTop" size="5"> 
            <label>Left</label> <input name="marginLeft" size="5">
        </div>
        <div class="form-group">
            <label>Block size: Width</label> <input name="blockWidth" size="5"> 
            <label>Height</label> <input name="blockHeight" size="5">
        </div>
        <div class="form-group">
            <label>Barcode size:</label> <input name="barcodeSize" size="5">
        </div>
        <div class="form-group">
            <input type="checkbox" name="printText" value="print"> <label>Include date, barcode text and item name:</label>
        </div>
    </div>
    <div class="form-group">
        <label>Start Row</label><input name="row" value="1" size="2">
        <label>Column</label><input name="col" value="1" size="2">
        <label>Copies</label><input name="copies" value="1" size="2">
    </div>
</g:form>

<script>
    function refreshBarcode() {
        var width = $("#width").val();
        var height = $("#height").val();
        var format = $("#format").val();
        var s = '${createLink(controller:"item", action:"displayBarcode", params:[barcode:item?.barcode])}';
        s += "&width=" + width + "&height=" + height + "&formatStr=" + format;
        $("img").attr("src", s);
    }
     
    $("#custom-template").hide();
    
    $('input:radio[name="template_src"]').change(
      function(){
        if ($(this).is(':checked') && $(this).val() == 'select') {
            $("#custom-template").hide();
        } else {
            $("#custom-template").show();
        }
      });
</script>