<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'sequenceIndexInstance.label', default: 'SequenceIndex')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            <li><a href="#"  data-toggle="modal" data-target="#import-csv">Import CSV</a></li>
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
                            <table class="table table-bordered">
                                <thead>
                                    <th>Index ID</th>
                                    <th>Index Version</th>
                                    <th>Sequence</th>
                                    <th>Oligo</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>X1</td>
                                        <td>Single</td>
                                        <td>AAAAACCCCTTTTGGGG</td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>Notes:</p>
                            <ol>
                                <li>The first row lists the headers.</li>
                                <li>The sequence of the columns should be exactly the same as above. </li>
                            </ol>
                        </div>
                    </div>
              </div>
            </div>
		</ul>
        <g:form class="pull-right" style="padding:3px 0px">
            <input name="str" value="${str}">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
        <div id="list-sequenceIndex" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.messageList}">
                <div class="message" role="status">
                <g:each in="${flash.messageList}">
                  <p>${it}</p>
                </g:each>
                </div>
            </g:if>
            <table class="table-bordered">
                <thead>
                    <g:sortableColumn property="indexId" title="Index ID"></g:sortableColumn>
                    <g:sortableColumn property="indexVersion" title="Index Version"></g:sortableColumn>
                    <th>Sequence</th>
                    <th>Oligo</th>
                    <g:sortableColumn property="status" title="Status"></g:sortableColumn>
                </thead>
                <tbody>                        
                    <g:each in="${sequenceIndexInstanceList}">
                        <tr>
                            <td><g:link action="show" id="${it.id}">${it.indexId}</g:link></td>
                            <td>${it.indexVersion}</td>
                            <td>${it.sequence}</td>
                            <td>${it.oligo}</td>
                            <td>${it.status}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${sequenceIndexCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>