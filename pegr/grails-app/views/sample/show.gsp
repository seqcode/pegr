
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sample.label', default: 'Sample')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="nav" />
        </div>
        <div class="col-sm-10 content">
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-sample" class="content scaffold-show" role="main">
			<h3><g:message code="default.show.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list sample">
			
				<g:if test="${sampleInstance?.cellSource}">
				<li class="fieldcontain">
					<span id="cellSource-label" class="property-label"><g:message code="sample.cellSource.label" default="Cell Source" /></span>
					
						<span class="property-value" aria-labelledby="cellSource-label"><g:link controller="cellSourceAdmin" action="show" id="${sampleInstance?.cellSource?.id}">${sampleInstance?.cellSource?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.antibody}">
				<li class="fieldcontain">
					<span id="antibody-label" class="property-label"><g:message code="sample.antibody.label" default="Antibody" /></span>
					
						<span class="property-value" aria-labelledby="antibody-label"><g:link controller="antibodyAdmin" action="show" id="${sampleInstance?.antibody?.id}">${sampleInstance?.antibody?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.target}">
				<li class="fieldcontain">
					<span id="target-label" class="property-label"><g:message code="sample.target.label" default="Target" /></span>
					
						<span class="property-value" aria-labelledby="target-label"><g:link controller="targetAdmin" action="show" id="${sampleInstance?.target?.id}">${sampleInstance?.target?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.protocolGroup}">
				<li class="fieldcontain">
					<span id="protocolGroup-label" class="property-label"><g:message code="sample.protocolGroup.label" default="Protocol Group" /></span>
					
						<span class="property-value" aria-labelledby="protocolGroup-label"><g:link controller="protocolGroupAdmin" action="show" id="${sampleInstance?.protocolGroup?.id}">${sampleInstance?.protocolGroup?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.biologicalReplicateSet}">
				<li class="fieldcontain">
					<span id="biologicalReplicateSet-label" class="property-label"><g:message code="sample.biologicalReplicateSet.label" default="Biological Replicate Set" /></span>
					
						<span class="property-value" aria-labelledby="biologicalReplicateSet-label"><g:link controller="biologicalReplicateSetAdmin" action="show" id="${sampleInstance?.biologicalReplicateSet?.id}">${sampleInstance?.biologicalReplicateSet?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.technicalReplicateSet}">
				<li class="fieldcontain">
					<span id="technicalReplicateSet-label" class="property-label"><g:message code="sample.technicalReplicateSet.label" default="Technical Replicate Set" /></span>
					
						<span class="property-value" aria-labelledby="technicalReplicateSet-label"><g:link controller="technicalReplicateSetAdmin" action="show" id="${sampleInstance?.technicalReplicateSet?.id}">${sampleInstance?.technicalReplicateSet?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.spikeInCellSource}">
				<li class="fieldcontain">
					<span id="spikeInCellSource-label" class="property-label"><g:message code="sample.spikeInCellSource.label" default="Spike In Cell Source" /></span>
					
						<span class="property-value" aria-labelledby="spikeInCellSource-label"><g:link controller="cellSourceAdmin" action="show" id="${sampleInstance?.spikeInCellSource?.id}">${sampleInstance?.spikeInCellSource?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.concentration}">
				<li class="fieldcontain">
					<span id="concentration-label" class="property-label"><g:message code="sample.concentration.label" default="Concentration" /></span>
					
						<span class="property-value" aria-labelledby="concentration-label"><g:fieldValue bean="${sampleInstance}" field="concentration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.publicationReference}">
				<li class="fieldcontain">
					<span id="publicationReference-label" class="property-label"><g:message code="sample.publicationReference.label" default="Publication Reference" /></span>
					
						<span class="property-value" aria-labelledby="publicationReference-label"><g:fieldValue bean="${sampleInstance}" field="publicationReference"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="sample.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${sampleInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.cellNumber}">
				<li class="fieldcontain">
					<span id="cellNumber-label" class="property-label"><g:message code="sample.cellNumber.label" default="Cell Number" /></span>
					
						<span class="property-value" aria-labelledby="cellNumber-label"><g:fieldValue bean="${sampleInstance}" field="cellNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.chromosomeAmount}">
				<li class="fieldcontain">
					<span id="chromosomeAmount-label" class="property-label"><g:message code="sample.chromosomeAmount.label" default="Chromosome Amount" /></span>
					
						<span class="property-value" aria-labelledby="chromosomeAmount-label"><g:fieldValue bean="${sampleInstance}" field="chromosomeAmount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="sample.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${sampleInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.projects}">
				<li class="fieldcontain">
					<span id="projects-label" class="property-label"><g:message code="sample.projects.label" default="Projects" /></span>
					
						<g:each in="${sampleInstance.projects}" var="p">
						<span class="property-value" aria-labelledby="projects-label"><g:link controller="projectAdmin" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.quantityReceived}">
				<li class="fieldcontain">
					<span id="quantityReceived-label" class="property-label"><g:message code="sample.quantityReceived.label" default="Quantity Received" /></span>
					
						<span class="property-value" aria-labelledby="quantityReceived-label"><g:fieldValue bean="${sampleInstance}" field="quantityReceived"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.requestedTagNumber}">
				<li class="fieldcontain">
					<span id="requestedTagNumber-label" class="property-label"><g:message code="sample.requestedTagNumber.label" default="Requested Tag Number" /></span>
					
						<span class="property-value" aria-labelledby="requestedTagNumber-label"><g:fieldValue bean="${sampleInstance}" field="requestedTagNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sampleInstance?.sequenceIndices}">
				<li class="fieldcontain">
					<span id="sequenceIndices-label" class="property-label"><g:message code="sample.sequenceIndices.label" default="Sequence Indices" /></span>
					
						<g:each in="${sampleInstance.sequenceIndices}" var="s">
						<span class="property-value" aria-labelledby="sequenceIndices-label"><g:link controller="sequenceIndexAdmin" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			

				<li class="fieldcontain">								
					Status:	${ sampleInstance.status}					
				</li>
			
			</ol>
			
			<g:form  action='delete' method="DELETE">
				<g:hiddenField name="id" value="${sampleInstance?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${sampleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
		</div>
        <script>
            $("#nav-sample-summary").addClass("active");
            $("#nav-projects").addClass("active");        
        </script>
	</body>
</html>
