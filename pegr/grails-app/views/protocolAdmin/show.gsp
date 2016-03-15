
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
		<div id="show-protocol" class="content scaffold-show" role="main">
			<h3>Protocol: ${protocol?.name} ${protocol?.protocolVersion}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <g:if test="${protocol?.assay}">
                <h4>Assay</h4>
                ${protocol?.assay}
            </g:if>
            
            <g:if test="${protocol?.description}">
                <h4>Description</h4>
                ${protocol?.description}
            </g:if>

            <h4>Shared Item Types</h4>           
            <ul>
            <g:each in="${protocol?.sharedItemTypes}">
                <li>${it}</li>
            </g:each>
            </ul>
            
            
            <h4>Traced Sample</h4>
            <ul>
                <li>Start State: ${protocol?.startItemType}</li>
                <li>End State: ${protocol?.endItemType}</li>
                <li>Add-on: 
                    <g:checkBox name="addAntibody" value="${protocol?.addAntibody}" disabled="true"/> Antibody 
                    <g:checkBox name="addIndex" value="${protocol?.addIndex}" disabled="true"/> Index
                </li>
            </ul>

			<h4>Sample Pool</h4>
            <ul>
                <li>Start Pool: ${protocol?.startPoolType}</li>
                <li>End Pool: ${protocol?.endPoolType}</li>
            </ul>
            <h4>Protocol File</h4>
            <g:if test="${file}">
                <g:link action="renderFile" params="[protocolId: protocol?.id]" target="_blank">${file.getName()}</g:link>
                <g:link action="deleteFile" params="[protocolId: protocol?.id]" class="confirm edit">Remove</g:link> 
            </g:if>
            <g:else>
                <g:uploadForm action="upload" >
                    <div class="form-group">
                        <g:hiddenField name="protocolId" value="${protocol?.id}"></g:hiddenField>
                        <input type="file" id="file" name="file"/>
                        <g:submitButton name="upload" value="Upload"/> (only pdf files)
                    </div>
                </g:uploadForm>
            </g:else>
            
            <h4>Protocol approved? ${protocol?.status}</h4>
            
			<g:form  action='delete' method="DELETE" useToken="true">
				<g:hiddenField name="id" value="${protocol?.id}" />
				<fieldset class="buttons">
					<g:link class="edit" action="edit"  id="${protocol?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
        <script>
            $(".confirm").confirm();
        </script>
	</body>
</html>
