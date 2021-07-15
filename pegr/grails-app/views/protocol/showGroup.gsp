
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
			<li><g:link class="list" action="labProtocolGroups">Protocol Group List</g:link></li>
		</ul>
		<div id="" class="content scaffold-show" role="main">
			<h3>Protocol Group: ${protocolGroup?.name}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:if test="${protocolGroup?.dateCreated}">
				Date Created: <g:formatDate date="${protocolGroup?.dateCreated}" />
			</g:if>
			
			<g:if test="${protocolGroup?.protocols}">
					<h4>Protocols</h4>
					<ol>
						<g:each in="${protocolGroup.protocols}" var="p">
						<li><span class="property-value" aria-labelledby="protocols-label"><g:link controller="protocol" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span></li>
						</g:each>
					</ol>
			</g:if>
		</div>
	</body>
</html>
