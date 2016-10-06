<%@ page import="pegr.ItemType" %>



<div class="fieldcontain ${hasErrors(bean: itemTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="itemType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${itemTypeInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemTypeInstance, field: 'fields', 'error')} ">
	<label for="fields">
		<g:message code="itemType.fields.label" default="Fields" />
		
	</label>
	<g:textField name="fields" value="${itemTypeInstance?.fields}"/>
    (multiple fields should be separated by comma)
</div>

<div class="fieldcontain ${hasErrors(bean: itemTypeInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="itemType.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${pegr.ItemTypeCategory.list()}" optionKey="id" required="" value="${itemTypeInstance?.category?.id}" class="many-to-one"/>

</div>
