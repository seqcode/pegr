
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
            <g:link action="labProtocolGroups" class="btn btn-info">Lab Protocol Groups</g:link>
            <g:link action="index" class="btn btn-info active">My Protocols</g:link>
        </div> 
		<div id="list-protocol">
			<h3>My Protocols <g:link action="create" class="edit">New</g:link></h3>
            
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="overviewProtocols" model="[protocolList:protocolList]"></g:render>
		</div>
	</body>
</html>
