<%@ page import="pegr.Protocol" %>

<div class=" ${hasErrors(bean: protocol, field: 'name', 'error')} required">
	<label for="name">Name</label>
	<g:textField name="name" required="" value="${protocol?.name}"/>

	<label for="protocolVersion">Version</label>
	<g:textField name="protocolVersion" maxlength="10" value="${protocol?.protocolVersion}"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'assay', 'error')} ">
	<label for="assay">Assay</label>
	<g:select name="assay.id" id="assay" from="${pegr.Assay.list()}" optionKey="id" value="${protocol?.assay?.id}" noSelection="['null': 'N/A']"/>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'description', 'error')} ">
	<label for="description">Description</label>
	<g:textArea name="description" value="${protocol?.description}"/>

</div>

<div>
    <label for="sharedItem">Shared Item Types (multi-select)</label>
    <g:select name="sharedItemTypeIds" id="sharedItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.OTHER}.list(sort:'name')}" optionKey="id" value="${sharedItemTypeIds}" multiple="multiple" size="10"/>
</div>

<h4>Traced Samples</h4>
<div>
    <label for="startItem">Start State</label>
    <g:select name="startItemTypeId" id="startItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.TRACED_SAMPLE}.list(sort:'name')}" optionKey="id" value="${startItemTypeId}" noSelection="['null':'N/A']"/>
    <label for="endItem">End State</label>
    <g:select name="endItemTypeId" id="endItem" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.TRACED_SAMPLE}.list(sort:'name')}" optionKey="id" value="${endItemTypeId}" noSelection="['null':'N/A']"/>
</div>

<div>    
    <label>Add-on </label>
    <g:checkBox name="addAntibody" value="${protocol?.addAntibody}"/> Antibody 
    <g:checkBox name="addIndex" value="${protocol?.addIndex}"/> Index
</div>

<h4>Sample Pool</h4>
<div>
    <label for="startPool"><abbr title="A pre-existing pool that has been generated in the previous protocol.">Start Pool</abbr></label>
    <g:select name="startPoolTypeId" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.SAMPLE_POOL}.list(sort:'name')}" optionKey="id" value="${startPoolTypeId}" noSelection="['null':'N/A']"/>
    
    <label for="endPool"><abbr title="A new pool that will be generated at the end of this protocol.">End Pool</abbr></label>
    <g:select name="endPoolTypeId" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.SAMPLE_POOL}.list(sort:'name')}" optionKey="id" value="${endPoolTypeId}" noSelection="['null':'N/A']"/>
</div>

<sec:ifAllGranted roles="ROLE_ADMIN">
    <h4>Protocol approved? <g:select name="status" from="${pegr.DictionaryStatus.values()}" value="${protocol?.status}"/></h4>
</sec:ifAllGranted>