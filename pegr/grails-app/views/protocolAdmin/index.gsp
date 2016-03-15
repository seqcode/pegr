
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-protocol" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>					
						<g:sortableColumn property="name" title="Name" />					
						<g:sortableColumn property="protocolVersion" title="Version" />
						<g:sortableColumn property="description" title="Description" />
                        <g:sortableColumn property="user" title="User" />
                        <g:sortableColumn property="status" title="Approved" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${protocolList}" status="i" var="protocol">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
						<td><g:link action="show" id="${protocol.id}">${fieldValue(bean: protocol, field: "name")}</g:link></td>					
                        <td>${fieldValue(bean: protocol, field: "protocolVersion")}</td>
                        <td>${fieldValue(bean: protocol, field: "description")}</td>
                        <td>${protocol.user}</td>
                        <td>${protocol.status}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${protocolCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
