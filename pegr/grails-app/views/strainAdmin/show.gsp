
<%@ page import="pegr.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-strain" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list strain">
			
				<g:if test="${strain?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="strain.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${strain}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strain?.genotype}">
				<li class="fieldcontain">
					<span id="genotype-label" class="property-label"><g:message code="strain.genotype.label" default="Genotype" /></span>
					
						<span class="property-value" aria-labelledby="genotype-label"><g:link controller="genotypeAdmin" action="show" id="${strain?.genotype?.id}">${strain?.genotype?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strain?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="strain.parent.label" default="Parent" /></span>
					
						<span class="property-value" aria-labelledby="parent-label"><g:link controller="strainAdmin" action="show" id="${strain?.parent?.id}">${strain?.parent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strain?.sourceLab}">
				<li class="fieldcontain">
					<span id="sourceLab-label" class="property-label"><g:message code="strain.sourceLab.label" default="Source Lab" /></span>
					
						<span class="property-value" aria-labelledby="sourceLab-label"><g:link controller="labAdmin" action="show" id="${strain?.sourceLab?.id}">${strain?.sourceLab?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strain?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="strain.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${strain}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strain?.species}">
				<li class="fieldcontain">
					<span id="species-label" class="property-label"><g:message code="strain.species.label" default="Species" /></span>
					
						<span class="property-value" aria-labelledby="species-label"><g:link controller="speciesAdmin" action="show" id="${strain?.species?.id}">${strain?.species?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${strain?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${strain?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
