<%@ page import="pegr.Protocol" %>

<div class=" ${hasErrors(bean: protocol, field: 'name', 'error')} required">
	<label for="name">Name <span class="required-indicator">*</span></label>
	<g:textField name="name" required="" value="${protocol?.name}"/>

</div>

<div class=" ${hasErrors(bean: protocol, field: 'protocolVersion', 'error')} ">
	<label for="protocolVersion">Version</label>
	<g:textField name="protocolVersion" maxlength="10" value="${protocol?.protocolVersion}"/>

</div>

<div class=" ${hasErrors(bean: protocol, field: 'description', 'error')} ">
	<label for="description">Description</label>
	<g:textArea name="description" value="${protocol?.description}"/>

</div>

<div class=" ${hasErrors(bean: protocol, field: 'details', 'error')} ">
	<label for="details">Details</label>
	<g:textArea id="protocol-details" name="details" value="${protocol?.details}"/>
	<script>$("#protocol-details").cleditor();</script>
</div>

<h4>Required Item Types</h4>
<div class="row">
    <div class="col-sm-5">
	   <g:select name="from[]" id="search" class="form-control" multiple="multiple"  from="${pegr.ItemType.list()}" optionKey="id" ></g:select>
	</div>
    <div class="col-sm-2">
        <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
		<button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
    </div>
    <div class="col-sm-5">
        <select name="requiredItemTypes" id="search_to" class="form-control" size="6" multiple="multiple">
            <g:each in="${requiredItemTypes}">
            <option value="${it.id}" >${it}</option>
            </g:each>
        </select>
    </div>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$('#search').multipleselect({
		search: {
			left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
		},         unique: "true",
	});    
});
</script>
