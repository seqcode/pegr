
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-protocol" class="content scaffold-show" role="main">
			<h3>Protocol: ${protocol?.name} ${protocol?.protocolVersion}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <g:if test="${protocol?.assay}">
                <h4>Assay</h4>
                ${protocol?.assay}
            </g:if>
            
            <g:if test="${protocol?.description}">
                <h4>Description</h4>
                ${protocol?.description}
            </g:if>
			
            <g:if test="${protocol?.details}">
                <h4>Details</h4>
                ${raw(protocol.details)}
            </g:if>
            
             <g:if test="${requiredItemTypes}">
                <h4>Required Item Types</h4>
                <ul>
                <g:each in="${requiredItemTypes}">
                <li>${it}</li>
                </g:each>
                </ul>
            </g:if>
			
			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${protocol?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${protocol?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
