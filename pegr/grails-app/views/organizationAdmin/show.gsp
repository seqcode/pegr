<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'organizationInstance.label', default: 'Organization')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
        <div id="show-organization" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <ol class="property-list company">    
                <li class="fieldcontain">
                    <span id="parent-label" class="property-label">Parent</span>
                    <div class="property-value" aria-labelledby="parent-label">${organizationInstance.parent}</div>
                </li>
                <li class="fieldcontain">
                    <span id="name-label" class="property-label">Name</span>
                    <div class="property-value" aria-labelledby="name-label">${organizationInstance.name}</div>
                </li>
                <li class="fieldcontain">
                    <span id="note-label" class="property-label">Note</span>
                    <div class="property-value" aria-labelledby="note-label">${organizationInstance.note}</div>
                </li>
                <li class="fieldcontain">
                    <span id="address-label" class="property-label">Address</span>
                    <div class="property-value" aria-labelledby="address-label">${organizationInstance.address?.line1}</div>
                    <div class="property-value" aria-labelledby="address-label">${organizationInstance.address?.line2}</div>
                    <div class="property-value" aria-labelledby="address-label">${organizationInstance.address?.city}</div>
                    <div class="property-value" aria-labelledby="address-label">${organizationInstance.address?.state} ${organizationInstance.address?.postalCode}</div>
                    <div class="property-value" aria-labelledby="address-label">${organizationInstance.address?.country}</div>
                </li>
                <li class="fieldcontain">
                    <span id="website-label" class="property-label">Website</span>
                    <div class="property-value" aria-labelledby="website-label">${organizationInstance.website}</div>
                </li>
                <li class="fieldcontain">
                    <span id="status-label" class="property-label">Status</span>
                    <div class="property-value" aria-labelledby="status-label">${organizationInstance.status}</div>
                </li>
            </ol>
            <g:form id="${this.organizationInstance.id}" action="delete" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" id="${this.organizationInstance.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
