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