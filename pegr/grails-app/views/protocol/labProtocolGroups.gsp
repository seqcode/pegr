
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="protocols">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
        <div>
            <g:link action="labProtocols" class="btn btn-info">Lab Protocols</g:link>
            <g:link action="labProtocolGroups" class="btn btn-info active">Lab Protocol Groups</g:link>
            <g:link action="index" class="btn btn-info">My Protocols</g:link>
            <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link action="allProtocols" class="btn btn-info">All Protocols</g:link></sec:ifAnyGranted>
        </div> 
		<div id="list-protocol">
			<h3>Lab Protocol Groups</h3>
            
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="overviewGroups" model="[protocolGroupList:protocolGroupList]"></g:render>
		</div>
	</body>
</html>