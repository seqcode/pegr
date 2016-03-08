<%@ page import="pegr.Protocol" %>

<div class=" ${hasErrors(bean: protocol, field: 'name', 'error')} required">
	<label for="name">Name <span class="required-indicator">*</span></label>
	<g:textField name="name" required="" value="${protocol?.name}"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'protocolVersion', 'error')} ">
	<label for="protocolVersion">Version</label>
	<g:textField name="protocolVersion" maxlength="10" value="${protocol?.protocolVersion}"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'assay', 'error')} ">
	<label for="assay">Assay</label>
	<g:select name="assay.id" id="assay" from="${pegr.Assay.list()}" optionKey="id" value="${protocol?.assay?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'description', 'error')} ">
	<label for="description">Description</label>
	<g:textArea name="description" value="${protocol?.description}"/>

</div>

<div>
    <label for="startItem">Traced Sample: Start State</label>
    <g:select name="startItemTypeId" id="startItem" from="${pegr.ItemType.list()}" optionKey="id" value="${protocol?.startItemType?.id}" noSelection="['null':'N/A']"/>
    <label for="endItem">End State</label>
    <g:select name="endItemTypeId" id="endItem" from="${pegr.ItemType.list()}" optionKey="id" value="${protocol?.endItemType?.id}" noSelection="['null':'N/A']"/>
</div>

<div>
    <label for="sharedItem">Required Item Types: Shared</label>
    <g:select name="sharedItemTypeIds" id="sharedItem" from="${pegr.ItemType.list()}" optionKey="id" value="${protocol?.sharedItemTypes*.id}" noSelection="['null':'N/A']" multiple="multiple"/>
    
    <label for="individualItem">Individual</label>
    <g:select name="individualItemTypeIds" id="individualItem" from="${pegr.ItemType.list()}" optionKey="id" value="${protocol?.individualItemTypes*.id}" noSelection="['null':'N/A']" multiple="multiple"/>
</div>
