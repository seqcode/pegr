<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'organizationInstance.label', default: 'Organization')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		</ul>
        <div id="create-organization" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.organizationInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.organizationInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save" method="POST">
                <fieldset class="form">
                    <div class="fieldcontain">
                      <label for="parent">Parent</label>
                      <g:select id="parent" name="parent.id" from="${pegr.Organization.list()}" optionKey="id" value="${this.organizationInstance?.parent?.id}" noSelection="['null': '']"/>
                    </div>
                    <div class="fieldcontain required">
                      <label for="name">Name<span class="required-indicator">*</span></label>
                      <input type="text" name="name" value="${this.organizationInstance?.name}" required="" id="name">
                    </div>
                    <div class="fieldcontain">
                      <label for="note">Note</label>
                      <input type="text" name="note" value="${this.organizationInstance?.note}" id="note">
                    </div>
                    <div class="fieldcontain">
                        <label>
                        <input type="checkbox" id="add-address" name="addAddress" value="yes" checked> Add address</label> <br>
                    </div>
                    <div id="address-fields">
                    <div class="fieldcontain">
                        <label for="line1">Line 1</label>
                        <input id="line1" type="text" name="address.line1" value="${this.organizationInstance?.address?.line1}">
                    </div>
                    <div class="fieldcontain">
                        <label for="line2">Line 2</label>
                        <input id="line2" type="text" name="address.line2" value="${this.organizationInstance?.address?.line2}">
                    </div>
                    <div class="fieldcontain">
                        <label for="city">City</label>
                        <input id="city" type="text" name="address.city" value="${this.organizationInstance?.address?.city}">
                    </div>
                    <div class="fieldcontain">
                        <label for="state">State</label>
                        <input id="state" type="text" name="address.state" value="${this.organizationInstance?.address?.state}">
                    </div>
                    <div class="fieldcontain">
                        <label for="postalCode">Postal Code</label>
                        <input id="postalCode" type="text" name="address.postalCode" value="${this.organizationInstance?.address?.postalCode}">
                    </div>
                    <div class="fieldcontain">
                        <label for="country">Country</label>
                        <input id="country" type="text" name="address.country" value="${this.organizationInstance?.address?.country}">
                    </div>
                    </div>
                    <div class="fieldcontain">
                      <label for="website">Website</label>
                      <input type="url" name="website" value="${this.organizationInstance?.website}" id="website">
                    </div>
                    <f:field bean="organizationInstance" property="status"></f:field>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
        <script>
            $("#add-address").click(function() {
                $("#address-fields").toggle();
            });
        </script>
    </body>
</html>
