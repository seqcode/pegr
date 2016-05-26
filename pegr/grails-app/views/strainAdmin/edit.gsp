<%@ page import="pegr.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>

		<div id="edit-strain" class="content scaffold-edit" role="main">
			<h3><g:message code="default.edit.label" args="[entityName]" /></h3>
			<g:if test="${request.message}">
			<div class="message" role="status">${request.message}</div>
			</g:if>
			<g:hasErrors bean="${strain}">
			<ul class="errors" role="alert">
				<g:eachError bean="${strain}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action='edit' method="POST" useToken="true">
                <g:hiddenField name="id" value="${strain?.id}" />
				<g:hiddenField name="version" value="${strain?.version}" />
				<fieldset class="fields">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="save"  value="save" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
