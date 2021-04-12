
<%@ page import="pegr.ItemType" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'itemType.label', default: 'ItemType')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
        <g:form class="pull-right" style="padding:3px 0px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
		<div id="list-itemType" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'itemType.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="fields" title="${message(code: 'itemType.fields.label', default: 'Fields')}" />
					
						<g:sortableColumn property="category" title="${message(code: 'itemType.category.label', default: 'Category')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemTypeInstanceList}" status="i" var="itemTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemTypeInstance.id}">${fieldValue(bean: itemTypeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: itemTypeInstance, field: "fields")}</td>
					
						<td>${fieldValue(bean: itemTypeInstance, field: "category")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${itemTypeCount ?: 0}" params="[str:str]" max="25"/>
			</div>
            <span class="pagination pull-right">
                <g:link params="[max:25]">25</g:link>
                <g:link params="[max:100]">100</g:link>
                / page
            </span>
		</div>
	</body>
</html>
