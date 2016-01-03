
<%@ page import="pegr.CellSource" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'cellSource.label', default: 'CellSource')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-cellSource" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
					
						<g:sortableColumn property="biologicalSourceId" title="${message(code: 'cellSource.biologicalSourceId.label', default: 'Biological Source Id')}" />
					
						<th><g:message code="cellSource.sex.label" default="Sex" /></th>
					
						<g:sortableColumn property="age" title="${message(code: 'cellSource.age.label', default: 'Age')}" />
					
						<th><g:message code="cellSource.tissue.label" default="Tissue" /></th>
					
						<th><g:message code="cellSource.histology.label" default="Histology" /></th>
					
						<g:sortableColumn property="note" title="${message(code: 'cellSource.note.label', default: 'Note')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cellSourceInstanceList}" status="i" var="cellSourceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cellSourceInstance.id}">${fieldValue(bean: cellSourceInstance, field: "biologicalSourceId")}</g:link></td>
					
						<td>${fieldValue(bean: cellSourceInstance, field: "sex")}</td>
					
						<td>${fieldValue(bean: cellSourceInstance, field: "age")}</td>
					
						<td>${fieldValue(bean: cellSourceInstance, field: "tissue")}</td>
					
						<td>${fieldValue(bean: cellSourceInstance, field: "histology")}</td>
					
						<td>${fieldValue(bean: cellSourceInstance, field: "note")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${cellSourceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
