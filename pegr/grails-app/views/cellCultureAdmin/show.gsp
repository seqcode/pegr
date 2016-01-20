
<%@ page import="pegr.CellCulture" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'cellCulture.label', default: 'CellCulture')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-cellCulture" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cellCulture">
			
				<g:if test="${cellCultureInstance?.biologicalSourceId}">
				<li class="fieldcontain">
					<span id="biologicalSourceId-label" class="property-label"><g:message code="cellCulture.biologicalSourceId.label" default="Biological Source Id" /></span>
					
						<span class="property-value" aria-labelledby="biologicalSourceId-label"><g:fieldValue bean="${cellCultureInstance}" field="biologicalSourceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.sex}">
				<li class="fieldcontain">
					<span id="sex-label" class="property-label"><g:message code="cellCulture.sex.label" default="Sex" /></span>
					
						<span class="property-value" aria-labelledby="sex-label"><g:link controller="sexAdmin" action="show" id="${cellCultureInstance?.sex?.id}">${cellCultureInstance?.sex?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.age}">
				<li class="fieldcontain">
					<span id="age-label" class="property-label"><g:message code="cellCulture.age.label" default="Age" /></span>
					
						<span class="property-value" aria-labelledby="age-label"><g:fieldValue bean="${cellCultureInstance}" field="age"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.tissue}">
				<li class="fieldcontain">
					<span id="tissue-label" class="property-label"><g:message code="cellCulture.tissue.label" default="Tissue" /></span>
					
						<span class="property-value" aria-labelledby="tissue-label"><g:link controller="tissueAdmin" action="show" id="${cellCultureInstance?.tissue?.id}">${cellCultureInstance?.tissue?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.histology}">
				<li class="fieldcontain">
					<span id="histology-label" class="property-label"><g:message code="cellCulture.histology.label" default="Histology" /></span>
					
						<span class="property-value" aria-labelledby="histology-label"><g:link controller="histologyAdmin" action="show" id="${cellCultureInstance?.histology?.id}">${cellCultureInstance?.histology?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="cellCulture.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${cellCultureInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.providerUser}">
				<li class="fieldcontain">
					<span id="providerUser-label" class="property-label"><g:message code="cellCulture.providerUser.label" default="Provider User" /></span>
					
						<span class="property-value" aria-labelledby="providerUser-label"><g:link controller="userAdmin" action="show" id="${cellCultureInstance?.providerUser?.id}">${cellCultureInstance?.providerUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.providerLab}">
				<li class="fieldcontain">
					<span id="providerLab-label" class="property-label"><g:message code="cellCulture.providerLab.label" default="Provider Lab" /></span>
					
						<span class="property-value" aria-labelledby="providerLab-label"><g:link controller="labAdmin" action="show" id="${cellCultureInstance?.providerLab?.id}">${cellCultureInstance?.providerLab?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.cellCultureTreatments}">
				<li class="fieldcontain">
					<span id="cellCultureTreatments-label" class="property-label"><g:message code="cellCulture.cellCultureTreatments.label" default="Cell Culture Treatments" /></span>
					
						<g:each in="${cellCultureInstance.cellCultureTreatments}" var="c">
						<span class="property-value" aria-labelledby="cellCultureTreatments-label"><g:link controller="cellCultureTreatmentAdmin" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cellCultureInstance?.strain}">
				<li class="fieldcontain">
					<span id="strain-label" class="property-label"><g:message code="cellCulture.strain.label" default="Strain" /></span>
					
						<span class="property-value" aria-labelledby="strain-label"><g:link controller="strainAdmin" action="show" id="${cellCultureInstance?.strain?.id}">${cellCultureInstance?.strain?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<g:form  action='delete' method="DELETE">
				<g:hiddenField name="id" value="${cellCultureInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${cellCultureInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
