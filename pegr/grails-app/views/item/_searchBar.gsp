<g:form class="fields form-inline well well-sm" role="form" controller="item" action="preview" >
    <div class="form-group">
        <label for="type">Type</label>
        <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" value="${currentType?.id}" noSelection="['null': '-- choose --']" />
    </div>        
    <div class="form-group">
        <label for="barcode">Barcode</label>
         <g:textField id="barcode" name="barcode" />
        <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
    </div>
    <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search/Create"/>
</g:form> 