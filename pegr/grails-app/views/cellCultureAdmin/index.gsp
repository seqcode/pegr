
<%@ page import="pegr.CellCulture" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'cellCulture.label', default: 'CellCulture')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-cellCulture" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
					
						<g:sortableColumn property="biologicalSourceId" title="${message(code: 'cellCulture.biologicalSourceId.label', default: 'Biological Source Id')}" />
					
						<th><g:message code="cellCulture.sex.label" default="Sex" /></th>
					
						<g:sortableColumn property="age" title="${message(code: 'cellCulture.age.label', default: 'Age')}" />
					
						<th><g:message code="cellCulture.tissue.label" default="Tissue" /></th>
					
						<th><g:message code="cellCulture.histology.label" default="Histology" /></th>
					
						<g:sortableColumn property="note" title="${message(code: 'cellCulture.note.label', default: 'Note')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cellCultureInstanceList}" status="i" var="cellCultureInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cellCultureInstance.id}">${fieldValue(bean: cellCultureInstance, field: "biologicalSourceId")}</g:link></td>
					
						<td>${fieldValue(bean: cellCultureInstance, field: "sex")}</td>
					
						<td>${fieldValue(bean: cellCultureInstance, field: "age")}</td>
					
						<td>${fieldValue(bean: cellCultureInstance, field: "tissue")}</td>
					
						<td>${fieldValue(bean: cellCultureInstance, field: "histology")}</td>
					
						<td>${fieldValue(bean: cellCultureInstance, field: "note")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${cellCultureInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
