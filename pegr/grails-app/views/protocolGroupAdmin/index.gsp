<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'protocolGroupInstance.label', default: 'ProtocolGroup')}" />
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
                                    <th>Name</th>
                                    <th>Protocols</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>ChIP-seq Protocol</td>
                                        <td>ChIP Reaction -- 1;ChIP End Repair -- 1;ChIP A-tailing Reaction -- 1;ChIP TruSeq Adapter Ligation -- 1;ChIP Elution and Purification -- 1;ChIP PCR -- 1</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>Notes:</p>
                            <ol>
                                <li>The first row lists the headers.</li>
                                <li>The sequence of the columns should be exactly the same as follows. </li>
                                <li>A protocol should follow the format 'PROTOCOL_NAME -- VERSION'.</li>
                                <li>Multiple protocols should be deliminated by semi-colon ';'.</li>
                            </ol>
                        </div>
                    </div>
              </div>
            </div>
            <li><g:link action="exportCsv">Export CSV</g:link></li>
		</ul>
        <g:form class="pull-right" style="padding:3px 0px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
        <div id="list-protocolGroup" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:each in="${flash.messageList}">
                <div class="message" role="status">${it}</div>
            </g:each>
            <f:table collection="${protocolGroupInstanceList}" />

            <div class="pagination">
                <g:paginate total="${protocolGroupCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>