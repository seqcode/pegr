
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'sample.label', default: 'Sample')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-sample" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
					
						<th><g:message code="sample.cellSource.label" default="Cell Source" /></th>
					
						<th><g:message code="sample.antibody.label" default="Antibody" /></th>
					
						<th><g:message code="sample.target.label" default="Target" /></th>
					
						<th><g:message code="sample.protocolGroup.label" default="Protocol Group" /></th>
					
						<th><g:message code="sample.biologicalReplicateSet.label" default="Biological Replicate Set" /></th>
					
						<th><g:message code="sample.technicalReplicateSet.label" default="Technical Replicate Set" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${sampleInstanceList}" status="i" var="sampleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${sampleInstance.id}">${fieldValue(bean: sampleInstance, field: "cellSource")}</g:link></td>
					
						<td>${fieldValue(bean: sampleInstance, field: "antibody")}</td>
					
						<td>${fieldValue(bean: sampleInstance, field: "target")}</td>
					
						<td>${fieldValue(bean: sampleInstance, field: "protocolGroup")}</td>
					
						<td>${fieldValue(bean: sampleInstance, field: "biologicalReplicateSet")}</td>
					
						<td>${fieldValue(bean: sampleInstance, field: "technicalReplicateSet")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${sampleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
