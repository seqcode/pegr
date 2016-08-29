<div class="form-group">
    <label for="type">Type</label>
    <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.where{if (currentSuperCategory) category.superCategory==currentSuperCategory}.list()}" value="${currentType?.id}" noSelection="['': '-- choose --']" required="required" style="width:200px"/>
</div>        
<div class="form-group">
    <label for="barcode">Barcode</label>
     <g:textField id="barcode" name="barcode" />
    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
    <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
</div>