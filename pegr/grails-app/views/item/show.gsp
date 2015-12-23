
<%@ page import="pegr.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin_main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-item" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list item">
			
				<g:if test="${itemInstance?.referenceId}">
				<li class="fieldcontain">
					<span id="referenceId-label" class="property-label"><g:message code="item.referenceId.label" default="Reference Id" /></span>
					
						<span class="property-value" aria-labelledby="referenceId-label"><g:fieldValue bean="${itemInstance}" field="referenceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="item.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${itemInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.barcode}">
				<li class="fieldcontain">
					<span id="barcode-label" class="property-label"><g:message code="item.barcode.label" default="Barcode" /></span>
					
						<span class="property-value" aria-labelledby="barcode-label"><g:fieldValue bean="${itemInstance}" field="barcode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.imagePath}">
				<li class="fieldcontain">
					<span id="imagePath-label" class="property-label"><g:message code="item.imagePath.label" default="Image Path" /></span>
					
						<span class="property-value" aria-labelledby="imagePath-label"><g:fieldValue bean="${itemInstance}" field="imagePath"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.protocolInstance}">
				<li class="fieldcontain">
					<span id="protocolInstance-label" class="property-label"><g:message code="item.protocolInstance.label" default="Protocol Instance" /></span>
					
						<span class="property-value" aria-labelledby="protocolInstance-label"><g:link controller="protocolInstanceAdmin" action="show" id="${itemInstance?.protocolInstance?.id}">${itemInstance?.protocolInstance?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="item.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${itemInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="item.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:link controller="itemTypeAdmin" action="show" id="${itemInstance?.type?.id}">${itemInstance?.type?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE">
				<g:hiddenField name="id" value="${itemInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${itemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
