<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'genomeInstance.label', default: 'Genome')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
        <div id="edit-genome" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.genomeInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.genomeInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="update" id="${this.genomeInstance.id}" method="PUT">
                <g:hiddenField name="version" value="${this.genomeInstance?.version}" />
                <fieldset class="form">
                    <f:field bean="genomeInstance" property="name"/>
                    <div class="fieldcontain">
                      <label for="species">Species</label>
                      <g:select name="species.id" id="species" from="${pegr.Species.list().sort { a,b ->
a.genusName.toLowerCase() <=> b.genusName.toLowerCase() ?: a.name.toLowerCase() <=> b.name.toLowerCase() }}" noSelection="['null':'']" optionKey="id" value="${this.genomeInstance?.species?.id}"></g:select>
                    </div>
                    <f:field bean="genomeInstance" property="url"/>
                    <f:field bean="genomeInstance" property="status"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
