
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
			<ul>
			
				<g:if test="${strain?.name}">
				<li>Name: ${strain.name}</li>
				</g:if>
                
                <g:if test="${strain?.species}">
				<li>Species: <g:link controller="speciesAdmin" action="show" id="${strain?.species?.id}">${strain?.species?.encodeAsHTML()}</g:link>					
				</li>
				</g:if>	
                
				<g:if test="${strain?.genotype}">
				<li>Genotype: ${strain?.genotype}
				</li>
				</g:if>
                
                <g:if test="${strain?.geneticModification}">
				    <li>GeneticModification: ${strain.geneticModification}                
                    </li>
				</g:if>
                
				<g:if test="${strain?.parent}">
				<li>Parent:<g:link controller="strainAdmin" action="show" id="${strain?.parent?.id}">${strain?.parent?.encodeAsHTML()}</g:link></li>
				</g:if>
                
				<g:if test="${strain?.sourceLab}">
				<li>Source Lab: <g:link controller="labAdmin" action="show" id="${strain?.sourceLab?.id}">${strain?.sourceLab?.encodeAsHTML()}</g:link>			
				</li>
				</g:if>
			
				<g:if test="${strain?.note}">
				<li>Note: ${strain.note}
				</li>
				</g:if>
                
                <li>Status: ${strain?.status}</li>
			</ul>
			
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
