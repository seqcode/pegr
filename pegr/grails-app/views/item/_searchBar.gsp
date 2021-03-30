<g:form class="fields form-inline well well-sm" role="form" controller="item" action="preview" >
    <div class="form-group">
        <label for="type">Type</label>
        <g:if test="${itemTypes}">
            <g:select id="type" name="typeId" optionKey="id" optionValue="label" from="${itemTypes}" noSelection="['null': '-- choose --']" style="width:250px"/>
        </g:if>
        <g:else>
            <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" style="width:250px"/>
        </g:else>        
    </div>        
    <div class="form-group">
        <label for="barcode">Barcode</label>
        <g:textField id="barcode" name="barcode" />
        <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
        <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
    </div>
    <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search/Create"/>
    <g:submitButton class="btn btn-primary btn-sm" name="generate" value="Generate Barcode"/>
</g:form> 