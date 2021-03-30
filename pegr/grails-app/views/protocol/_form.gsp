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
    <select name="sharedItemTypeIds" class="other-type" multiple="multiple" style="min-width:20em;">
        <g:each var="sharedItemType" in="${sharedItemTypes}">
        <option value="${sharedItemType?.id}" selected>${sharedItemType?.name}</option>
        </g:each>
    </select>
</div>

<div>
    <label for="endProduct">End Product Type (multi-select)</label>
    <select name="endProductTypeIds" class="other-type" multiple="multiple" style="min-width:20em;">
        <g:each var="endProductType" in="${endProductTypes}">
        <option value="${endProductType?.id}" selected>${endProductType?.name}</option>
        </g:each>
    </select>
</div>

<div>
    <label>Required Images</label>
    <g:textField name="images" value="${protocol?.images}"/>
    (multiple fields should be separated by comma)
</div>

<h4>Traced Samples</h4>
<div>
    <label for="startItem">Start State</label>
    <select name="startItemTypeId" class="traced-sample" id="startItem" style="min-width:20em;">
        <option value="${startItemType?.id}">${startItemType?.name}</option>
    </select>
</div>
<div>
    <label for="endItem">End State</label>
    <select name="endItemTypeId" class="traced-sample" id="endItem" style="min-width:20em;">
        <option value="${endItemType?.id}">${endItemType?.name}</option>
    </select>
</div>

<div>    
    <label>Add-on </label>
    <g:checkBox name="addAntibody" value="${protocol?.addAntibody}"/> Antibody 
    <g:checkBox name="addIndex" value="${protocol?.addIndex}"/> Index
</div>

<h4>Sample Pool</h4>
<div>
    <label for="startPool"><abbr title="A pre-existing pool that has been generated in the previous protocol.">Import Pool</abbr></label>
    <select name="startPoolTypeId" class="sample-pool" id="startPool" style="min-width:20em;">
        <option value="${startPoolType?.id}">${startPoolType?.name}</option>
    </select>    
    <label for="endPool"><abbr title="A new pool that will be generated at the end of this protocol.">Create Pool</abbr></label>
    <select name="endPoolTypeId" class="sample-pool" id="endPool" style="min-width:20em;">
        <option value="${endPoolType?.id}">${endPoolType?.name}</option>
    </select>
</div>

<div class=" ${hasErrors(bean: protocol, field: 'url', 'error')} ">
	<label for="url">URL</label>
	<g:textField name="url" value="${protocol?.url}"/>
</div>

<sec:ifAllGranted roles="ROLE_ADMIN">
    <h4>Protocol approved? <g:select name="status" from="${pegr.DictionaryStatus.values()}" value="${protocol?.status}"/></h4>
</sec:ifAllGranted>

<script>
    $(".sharedItem").select2();
    
    var noTagPlaceholder = "Select...";
    
    $.ajax({url: "/pegr/itemType/fetchItemTypesAjax?superCatalog=TRACED_SAMPLE", success: function(result){
        $(".traced-sample").select2({
            data: result,
            placeholder: noTagPlaceholder
        });
       
    }});
    
    $.ajax({url: "/pegr/itemType/fetchItemTypesAjax?superCatalog=SAMPLE_POOL", success: function(result){
        $(".sample-pool").select2({
            data: result,
            placeholder: noTagPlaceholder
        });
       
    }});
    
    $.ajax({url: "/pegr/itemType/fetchItemTypesAjax?superCatalog=OTHER", success: function(result){
        $(".other-type").select2({
            data: result,
            placeholder: noTagPlaceholder
        });       
    }});
</script>