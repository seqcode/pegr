
<%@ page import="pegr.ItemType" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'itemType.label', default: 'ItemType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-itemType" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list itemType">
			
				<g:if test="${itemTypeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="itemType.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${itemTypeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemTypeInstance?.fields}">
				<li class="fieldcontain">
					<span id="fields-label" class="property-label"><g:message code="itemType.fields.label" default="Fields" /></span>
					
						<span class="property-value" aria-labelledby="fields-label"><g:fieldValue bean="${itemTypeInstance}" field="fields"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemTypeInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="itemType.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:link controller="itemTypeCategoryAdmin" action="show" id="${itemTypeInstance?.category?.id}">${itemTypeInstance?.category?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${itemTypeInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${itemTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
