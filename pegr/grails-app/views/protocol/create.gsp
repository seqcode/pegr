<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="protocols">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="create-protocol" class="content scaffold-create" role="main">
			<h3><g:message code="default.create.label" args="[entityName]" /></h3>
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
			<g:form action='create' useToken="true" enctype="multipart/form-data">
				<fieldset class="form fields">
					<g:render template="form"/>
				</fieldset>
                <div class="form-group">
                    <label><input type="file" id="file" name="file"/>(only pdf files)</label>
                </div>
				<g:submitButton name="create" class="btn btn-primary" value="Save" />
                <g:link action="index" class="btn btn-default">Cancel</g:link>
			</g:form>
		</div>
	</body>
</html>
