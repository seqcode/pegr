<div class="table-responsive">
    <table class="table">
        <thead>
            <tr>					
                <g:sortableColumn property="name" title="Name" />					
                <g:sortableColumn property="protocolVersion" title="Version" />
                <g:sortableColumn property="description" title="Description" />
                <g:sortableColumn property="user" title="User" />
                <g:sortableColumn property="status" title="Approved" />
            </tr>
        </thead>
        <tbody>
        <g:each in="${protocolList}" status="i" var="protocol">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
                <td><g:link action="show" id="${protocol.id}">${fieldValue(bean: protocol, field: "name")}</g:link></td>					
                <td>${fieldValue(bean: protocol, field: "protocolVersion")}</td>
                <td>${fieldValue(bean: protocol, field: "description")}</td>
                <td>${protocol.user}</td>
                <td>${protocol.status}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<div class="pagination">
    <g:paginate total="${protocolCount ?: 0}" />
</div>