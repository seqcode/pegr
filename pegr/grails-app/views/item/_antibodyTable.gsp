<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="id" title="ID"></g:sortableColumn>
                <g:sortableColumn property="catalogNumber" title="Catalog"></g:sortableColumn>
                <g:sortableColumn property="lotNumber" title="Lot"></g:sortableColumn>
                <th>Host</th>
                <th>Immunogene</th>
                <th>Clonal</th>
                <th>Ig Type</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${objectList}" var="object">
                <tr>
                    <td><g:link controller="item" action="show" id="${object.id}">${object.id}</g:link></td>
                    <td>${object.catalogNumber}</td>
                    <td>${object.lotNumber}</td>
                    <td>${object?.abHost}</td>
                    <td>${object.immunogene}</td>
                    <td>${object.clonal}</td>
                    <td>${object?.igType}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="4"></td>
            </tr>
        </tbody>
      </table>
</div>

<div class="pagination">
    <g:paginate next="Next" prev="Prev" controller="object" action="list" total="${objectCount ?: 0}" />
</div>