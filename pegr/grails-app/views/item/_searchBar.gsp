<g:form class="fields form-inline well well-sm" role="form" controller="item" action="preview" >
    <div class="form-group">
        <label for="type">Type</label>
        <g:if test="${itemTypes}">
            <g:select id="type" name="typeId" optionKey="id" optionValue="label" from="${itemTypes}" noSelection="['null': '-- choose --']" style="width:250px"/>
        </g:if>
        <g:else>
            <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" style="width:250px"/>
        </g:else>
        <g:submitButton class="btn btn-primary btn-sm" name="generate" value="New instance with PEGR generated barcode"/>
    </div>        
    <div class="form-group pull-right">
        <label for="barcode">Barcode</label>
        <g:textField id="barcode" name="barcode" />
        <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
        <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
        <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search/Create instance with existing barcode"/>
    </div>
</g:form> 
<div class="row">
    <g:each in="${pegr.ItemTypeCategory.list(sort:'id')}" var="category">
        <g:link controller="item" action="list" params="[categoryId: category.id]" class="btn btn-info item-${category.id}">${category.name}</g:link> 
    </g:each>
    <g:link controller="sequenceIndex" class="btn btn-info item-index">Index</g:link>
    <g:if test="${orderLink}">
        <a href="${orderLink}" class="btn btn-info external" target="_blank">Order</a>
    </g:if>
</div>