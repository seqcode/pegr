<%@ page import="pegr.Protocol" %>

<div class=" ${hasErrors(bean: protocolInstance, field: 'name', 'error')} required">
	<label for="name">Name <span class="required-indicator">*</span></label>
	<g:textField name="name" maxlength="30" required="" value="${protocolInstance?.name}"/>

</div>

<div class=" ${hasErrors(bean: protocolInstance, field: 'protocolVersion', 'error')} ">
	<label for="protocolVersion">Version</label>
	<g:textField name="protocolVersion" maxlength="10" value="${protocolInstance?.protocolVersion}"/>

</div>

<div class=" ${hasErrors(bean: protocolInstance, field: 'description', 'error')} ">
	<label for="description">Description</label>
	<g:textArea name="description" value="${protocolInstance?.description}"/>

</div>

<div class=" ${hasErrors(bean: protocolInstance, field: 'details', 'error')} ">
	<label for="details">Details</label>
	<g:textArea id="protocol-details" name="details" value="${protocolInstance?.details}"/>
	<script>$("#protocol-details").jqte();</script>
</div>


