
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
            <li><g:link action="index">My Protocols</g:link></li>
            <li><g:link action="labProtocols">Lab Protocols</g:link></li>
            <li class="active"><g:link action="labProtocolGroups">Lab Protocol Groups</g:link></li>
		</ul>
		<div id="list-protocol">
			<h3>Lab Protocol Groups</h3>
            
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="overviewGroups" model="[protocolGroupList:protocolGroupList]"></g:render>
		</div>
	</body>
</html>