<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="type" title="Type"></g:sortableColumn>
                <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                <g:sortableColumn property="location" title="Location"></g:sortableColumn>
                <th>Notes</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${objectList}" var="object">
                <tr>
                <td >${object.type} </td>
                <td><g:link action="show" id="${object.id}">${object.name}</g:link></td>
                <td >${object.location}</td>
                <td>${object.notes}</td>
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