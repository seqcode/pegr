<%@ page import="pegr.ProtocolGroup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocolGroup.label', default: 'ProtocolGroup')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index">Protocol Group List</g:link></li>
			<li><g:link class="create" action="create">New Protocol Group</g:link></li>
		</ul>

		<div id="edit-protocolGroup" class="content scaffold-edit" role="main">
			<h3>Edit Protocol Group</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${protocolGroupInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${protocolGroupInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action='update' method="PUT" useToken="true" >
                <g:hiddenField name="id" value="${protocolGroupInstance?.id}" />
				<fieldset class="form fields">
                    <div class="${hasErrors(bean: protocolGroupInstance, field: 'name', 'error')} required">
                        <label for="name">Name	<span class="required-indicator">*</span></label>
                        <g:textField name="name" required="" value="${protocolGroupInstance?.name}"/>
                    </div>
					<g:render template="form" model="[protocolGroup: protocolGroupInstance]"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    <g:link action="show" id="${protocolGroupInstance.id}">Cancel</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
