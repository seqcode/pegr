<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="protocols">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-protocol">
			<h3><g:message code="default.edit.label" args="[entityName]" /></h3>
			<g:if test="${request.message}">
			<div class="message" role="status">${request.message}</div>
			</g:if>
			<g:hasErrors bean="${protocol}">
			<ul class="errors" role="alert">
				<g:eachError bean="${protocol}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="edit" method="POST" useToken="true">
                <g:hiddenField name="id" value="${protocol?.id}" />
				<fieldset class="form fields">
					<g:render template="form"/>
				</fieldset>
				<g:submitButton class="btn btn-primary" name="save" value="save" />
                <g:link class="btn btn-default" action="show" id="${protocol.id}">Cancel</g:link>
			</g:form>
		</div>
	</body>
</html>
