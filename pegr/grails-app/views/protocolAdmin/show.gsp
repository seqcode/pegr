
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-protocol" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list protocol">
			
				<g:if test="${protocolInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="protocol.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${protocolInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${protocolInstance?.protocolVersion}">
				<li class="fieldcontain">
					<span id="protocolVersion-label" class="property-label"><g:message code="protocol.protocolVersion.label" default="Protocol Version" /></span>
					
						<span class="property-value" aria-labelledby="protocolVersion-label"><g:fieldValue bean="${protocolInstance}" field="protocolVersion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${protocolInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="protocol.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${protocolInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${protocolInstance?.details}">
				<li class="fieldcontain">
					<span id="details-label" class="property-label"><g:message code="protocol.details.label" default="Details" /></span>
					
						<span class="property-value" aria-labelledby="details-label"><g:fieldValue bean="${protocolInstance}" field="details"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${protocolInstance?.protocolGroups}">
				<li class="fieldcontain">
					<span id="protocolGroups-label" class="property-label"><g:message code="protocol.protocolGroups.label" default="Protocol Groups" /></span>
					
						<g:each in="${protocolInstance.protocolGroups}" var="p">
						<span class="property-value" aria-labelledby="protocolGroups-label"><g:link controller="protocolGroupAdmin" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${protocolInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${protocolInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
