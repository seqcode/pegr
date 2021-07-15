
<%@ page import="pegr.ProtocolGroup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocolGroup.label', default: 'ProtocolGroup')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index">Protocol Group List</g:link></li>
			<li><g:link class="create" action="create">New Protocol Group</g:link></li>
		</ul>
		<div id="" class="content scaffold-show" role="main">
			<h3>Protocol Group: ${protocolGroupInstance?.name}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:if test="${protocolGroupInstance?.dateCreated}">
				Date Created: <g:formatDate date="${protocolGroupInstance?.dateCreated}" />
			</g:if>
			
			<g:if test="${protocolGroupInstance?.protocols}">
					<h4>Protocols</h4>
					<ol>
						<g:each in="${protocolGroupInstance.protocols}" var="p">
						<li><span class="property-value" aria-labelledby="protocols-label"><g:link controller="protocol" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span></li>
						</g:each>
					</ol>
			</g:if>

			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${protocolGroupInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${protocolGroupInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
