<div class="table-responsive">
    <table class="table">
        <thead>
            <tr>					
                <g:sortableColumn property="name" title="Name" />
                <g:sortableColumn property="dateCreated" title="${message(code: 'protocolGroup.dateCreated.label', default: 'Date Created')}" />
            </tr>
        </thead>
        <tbody>
        <g:each in="${protocolGroupList}" status="i" var="protocolGroup">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="showGroup" id="${protocolGroup.id}">${fieldValue(bean: protocolGroup, field: "name")}</g:link></td>
                <td><g:formatDate date="${protocolGroup.dateCreated}" /></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<div class="pagination">
    <g:paginate total="${protocolGroupCount ?: 0}" />
</div>