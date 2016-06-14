
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="protocols">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-tabs">
            <li class="active"><g:link action="index">My Protocols</g:link></li>
            <li><g:link action="labProtocols">Lab Protocols</g:link></li>
            <li><g:link action="labProtocolGroups">Lab Protocol Groups</g:link></li>
		</ul>
		<div id="list-protocol">
			<h3>My Protocols <g:link action="create" class="edit">New</g:link></h3>
            
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="overviewProtocols" model="[protocolList:protocolList]"></g:render>
		</div>
	</body>
</html>
