
<%@ page import="pegr.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="list-item" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="referenceId" title="${message(code: 'item.referenceId.label', default: 'Reference Id')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'item.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="barcode" title="${message(code: 'item.barcode.label', default: 'Barcode')}" />
					
						<g:sortableColumn property="imagePath" title="${message(code: 'item.imagePath.label', default: 'Image Path')}" />
					
						<th><g:message code="item.protocolInstance.label" default="Protocol Instance" /></th>
					
						<g:sortableColumn property="notes" title="${message(code: 'item.notes.label', default: 'Notes')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" status="i" var="itemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "referenceId")}</g:link></td>
					
						<td>${fieldValue(bean: itemInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "barcode")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "imagePath")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "protocolInstance")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "notes")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
