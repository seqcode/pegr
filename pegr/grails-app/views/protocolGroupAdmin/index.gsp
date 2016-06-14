
<%@ page import="pegr.ProtocolGroup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocolGroup.label', default: 'ProtocolGroup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create">New Protocol Group</g:link></li>
		</ul>
		<div id="list-protocolGroup" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/protocol/overviewGroups" model="protocolGroupList: protocolGroupInstanceList, protocolGroupCount: protocolGroupInstanceCount"></g:render>
		</div>
	</body>
</html>
