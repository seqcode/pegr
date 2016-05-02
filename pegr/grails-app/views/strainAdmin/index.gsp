
<%@ page import="pegr.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-strain" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'strain.name.label', default: 'Name')}" />
                        <th><g:message code="strain.species.label" default="Species" /></th>
						<th><g:message code="strain.parent.label" default="Parent" /></th>
					    <th><g:message code="strain.genotype.label" default="Genotype" /></th>
						<th><g:message code="strain.sourceLab.label" default="Source Lab" /></th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${strainList}" status="i" var="strain">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${strain.id}">${fieldValue(bean: strain, field: "name")}</g:link></td>					
                        <td>${fieldValue(bean: strain, field: "species")}</td>
						<td>${fieldValue(bean: strain, field: "parent")}</td>
				        <td>${fieldValue(bean: strain, field: "genotype")}</td>
						<td>${fieldValue(bean: strain, field: "sourceLab")}</td>
						<td>${strain.status?: "Not Approved"}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${strainCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
