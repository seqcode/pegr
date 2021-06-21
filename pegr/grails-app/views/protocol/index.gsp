
<%@ page import="pegr.Protocol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="protocols">
		<g:set var="entityName" value="${message(code: 'protocol.label', default: 'Protocol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div>
            <g:link action="labProtocols" class="btn btn-info">Lab Protocols</g:link>
            <g:link action="labProtocolGroups" class="btn btn-info">Lab Protocol Groups</g:link>
            <g:link action="index" class="btn btn-info active">My Protocols</g:link>
            <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link action="allProtocols" class="btn btn-info">All Protocols</g:link></sec:ifAnyGranted>
        </div> 
		<div id="list-protocol">
			<h3>My Protocols <g:link action="create" class="edit">New</g:link>
            <sec:ifAnyGranted roles="ROLE_ADMIN"><a href="#"  data-toggle="modal" data-target="#import-csv" class="edit">Import CSV</a></sec:ifAnyGranted></h3>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
            <div id="import-csv" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="modal-title">Import CSV file</h3>
                            <br>
                            <g:uploadForm action="importCSV" name="importCSV">
                                <input type="file" name="file" />
                                <input class="btn btn-primary" type="submit" value="${message(code: 'restaurant.featuredImage.upload.label', default: 'Upload')}" />
                            </g:uploadForm>
                        </div>
                        <div class="modal-body">
                            <h4>CSV file template</h4>
                            <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <th>Name</th>
                                    <th>Version</th>
                                    <th>Assay</th>
                                    <th>Description</th>
                                    <th>Shared Item Types</th>
                                    <th>End Product Types</th>
                                    <th>Required Images</th>
                                    <th>Traced Sample Start Type</th>
                                    <th>Traced Sample End Type</th>
                                    <th>Add Antibody?</th>
                                    <th>Add Index?</th>
                                    <th>Import Pool Type</th>
                                    <th>Create Pool Type</th>
                                    <th>URL</th>
                                    <th>Approved?</th>
                                    <th>PDF</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>2.5M Glycine Recipe</td>
                                        <td>1</td>
                                        <td></td>
                                        <td>Makes 2.5M Glycine for quenching formaldehyde</td>
                                        <td>Chemical:Glycine</td>
                                        <td>Buffer:2.5M Glycine</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>yes</td>	
                                        <td>2.5M_Glycine_Recipe.pdf</td>
                                    </tr>
                                </tbody>
                            </table>
                            </div>
                            <p>Notes:</p>
                            <ol>
                                <li>The first row lists the headers.</li>
                                <li>The sequence of the columns should be exactly the same as follows. </li>
                                <li>An item type (columns E, F, H, I, L and M) should follow the format 'CATEGORY:NAME'. </li>
                                <li>Multiple item types or images should be deliminated by comma ",". </li>
                                
                            </ol>
                        </div>
                    </div>
              </div>
            </div>
            </sec:ifAnyGranted>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:each in="${flash.messageList}">
                <div class="message" role="status">${it}</div>
            </g:each>
			<g:render template="overviewProtocols" model="[protocolList:protocolList, action: 'index']"></g:render>
		</div>
	</body>
</html>
