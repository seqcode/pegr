<div class="form-group">
    <label for="type">Type</label>
    <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" />
</div>        
<div class="form-group">
    <label for="barcode">Barcode</label>
     <g:textField id="barcode" name="barcode" />
    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
</div>