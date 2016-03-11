<%@ page import="pegr.Protocol" %>

<div class=" ${hasErrors(bean: protocol, field: 'name', 'error')} required">
	<label for="name">Name</label>
	<g:textField name="name" required="" value="${protocol?.name}"/>

	<label for="protocolVersion">Version</label>
	<g:textField name="protocolVersion" maxlength="10" value="${protocol?.protocolVersion}"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'assay', 'error')} ">
	<label for="assay">Assay (optional)</label>
	<g:select name="assay.id" id="assay" from="${pegr.Assay.list()}" optionKey="id" value="${protocol?.assay?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'description', 'error')} ">
	<label for="description">Description</label>
	<g:textArea name="description" value="${protocol?.description}"/>

</div>

<h4>Traced Samples</h4>
<div>
    <label for="startItem">Start State</label>
    <g:select name="startItemTypeId" id="startItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.TRACED_SAMPLE}.list(sort:'name')}" optionKey="id" value="${protocol?.startItemType?.id}" noSelection="['null':'N/A']"/>
    <label for="endItem">End State</label>
    <g:select name="endItemTypeId" id="endItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.TRACED_SAMPLE}.list(sort:'name')}" optionKey="id" value="${protocol?.endItemType?.id}" noSelection="['null':'N/A']"/>
</div>

<h4>Required Item Types (multi-select)</h4>
<div>
    <label for="sharedItem">Shared</label>
    <g:select name="sharedItemTypeIds" id="sharedItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.OTHER}.list(sort:'name')}" optionKey="id" value="${protocol?.sharedItemTypes*.id}" multiple="multiple" size="20"/>
    
    <label for="individualItem">Individual Sample</label>
    <g:select name="individualItemTypeIds" id="individualItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.OTHER || category==pegr.ItemTypeCategory.ANTIBODY}.list(sort:'name')}" optionKey="id" value="${protocol?.individualItemTypes*.id}" multiple="multiple" size="20"/>
</div>

<h4>Sample Pool (optional)</h4>
<div>
    <label for="startPool">Start Pool</label>
    <g:select name="startPoolTypeId" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.SAMPLE_POOL}.list(sort:'name')}" optionKey="id" value="${protocol?.startPoolType?.id}" noSelection="['null':'N/A']"/>
    
    <label for="endPool">End Pool</label>
    <g:select name="endPoolTypeId" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.SAMPLE_POOL}.list(sort:'name')}" optionKey="id" value="${protocol?.endPoolType?.id}" noSelection="['null':'N/A']"/>
</div>