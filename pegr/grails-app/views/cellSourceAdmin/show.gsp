
<%@ page import="pegr.CellSource" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'cellSource.label', default: 'CellSource')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-cellSource" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cellSource">
			
				<g:if test="${cellSourceInstance?.biologicalSourceId}">
				<li class="fieldcontain">
					<span id="biologicalSourceId-label" class="property-label"><g:message code="cellSource.biologicalSourceId.label" default="Biological Source Id" /></span>
					
						<span class="property-value" aria-labelledby="biologicalSourceId-label"><g:fieldValue bean="${cellSourceInstance}" field="biologicalSourceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.sex}">
				<li class="fieldcontain">
					<span id="sex-label" class="property-label"><g:message code="cellSource.sex.label" default="Sex" /></span>
					
						<span class="property-value" aria-labelledby="sex-label"><g:link controller="sexAdmin" action="show" id="${cellSourceInstance?.sex?.id}">${cellSourceInstance?.sex?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.age}">
				<li class="fieldcontain">
					<span id="age-label" class="property-label"><g:message code="cellSource.age.label" default="Age" /></span>
					
						<span class="property-value" aria-labelledby="age-label"><g:fieldValue bean="${cellSourceInstance}" field="age"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.tissue}">
				<li class="fieldcontain">
					<span id="tissue-label" class="property-label"><g:message code="cellSource.tissue.label" default="Tissue" /></span>
					
						<span class="property-value" aria-labelledby="tissue-label"><g:link controller="tissueAdmin" action="show" id="${cellSourceInstance?.tissue?.id}">${cellSourceInstance?.tissue?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.histology}">
				<li class="fieldcontain">
					<span id="histology-label" class="property-label"><g:message code="cellSource.histology.label" default="Histology" /></span>
					
						<span class="property-value" aria-labelledby="histology-label"><g:link controller="histologyAdmin" action="show" id="${cellSourceInstance?.histology?.id}">${cellSourceInstance?.histology?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="cellSource.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${cellSourceInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.providerUser}">
				<li class="fieldcontain">
					<span id="providerUser-label" class="property-label"><g:message code="cellSource.providerUser.label" default="Provider User" /></span>
					
						<span class="property-value" aria-labelledby="providerUser-label"><g:link controller="userAdmin" action="show" id="${cellSourceInstance?.providerUser?.id}">${cellSourceInstance?.providerUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.providerLab}">
				<li class="fieldcontain">
					<span id="providerLab-label" class="property-label"><g:message code="cellSource.providerLab.label" default="Provider Lab" /></span>
					
						<span class="property-value" aria-labelledby="providerLab-label"><g:link controller="labAdmin" action="show" id="${cellSourceInstance?.providerLab?.id}">${cellSourceInstance?.providerLab?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.cellSourceTreatments}">
				<li class="fieldcontain">
					<span id="cellSourceTreatments-label" class="property-label"><g:message code="cellSource.cellSourceTreatments.label" default="Cell Source Treatments" /></span>
					
						<g:each in="${cellSourceInstance.cellSourceTreatments}" var="c">
						<span class="property-value" aria-labelledby="cellSourceTreatments-label"><g:link controller="cellSourceTreatmentAdmin" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cellSourceInstance?.strain}">
				<li class="fieldcontain">
					<span id="strain-label" class="property-label"><g:message code="cellSource.strain.label" default="Strain" /></span>
					
						<span class="property-value" aria-labelledby="strain-label"><g:link controller="strainAdmin" action="show" id="${cellSourceInstance?.strain?.id}">${cellSourceInstance?.strain?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE">
				<g:hiddenField name="id" value="${cellSourceInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${cellSourceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
