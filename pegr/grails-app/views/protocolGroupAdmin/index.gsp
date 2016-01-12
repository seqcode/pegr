
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
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>					
						<g:sortableColumn property="name" title="Name" />
						<g:sortableColumn property="dateCreated" title="${message(code: 'protocolGroup.dateCreated.label', default: 'Date Created')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${protocolGroupInstanceList}" status="i" var="protocolGroupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${protocolGroupInstance.id}">${fieldValue(bean: protocolGroupInstance, field: "name")}</g:link></td>
					
						<td><g:formatDate date="${protocolGroupInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${protocolGroupInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
