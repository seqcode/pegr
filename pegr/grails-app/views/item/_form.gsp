<%@ page import="pegr.Item" %>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="item.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${itemInstance?.location}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'barcode', 'error')} ">
	<label for="barcode">
		<g:message code="item.barcode.label" default="Barcode" />
		
	</label>
	<g:textField id="barcode" name="barcode" value="${itemInstance?.barcode}"/>
	<input style="width:100px;height:100px" type=button value="Scan" onclick="getScan();">
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'imagePath', 'error')} ">
	<label for="imagePath">
		<g:message code="item.imagePath.label" default="Image Path" />
		
	</label>
	<g:textField name="imagePath" value="${itemInstance?.imagePath}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'protocolInstance', 'error')} ">
	<label for="protocolInstance">
		<g:message code="item.protocolInstance.label" default="Protocol Instance" />
		
	</label>
	<g:select id="protocolInstance" name="protocolInstance.id" from="${pegr.ProtocolInstance.list()}" optionKey="id" value="${itemInstance?.protocolInstance?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="item.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" value="${itemInstance?.notes}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="item.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${pegr.ItemType.list()}" optionKey="id" required="" value="${itemInstance?.type?.id}" class="many-to-one"/>

</div>

