<h4>Traced Samples</h4>
<table class="table table-striped">
    <thead>
        <th>Start State</th>
        <th>End State</th>
    </thead>
    <tbody>        
        <g:each in="${samples}" var="sample" status="n">
            <tr>
                <td><g:link controller="item" action="show" id="${parents[n].id}" target="_blank">${parents[n].name}</g:link></td>
                <td><g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link></td>
            </tr>
        </g:each>
    </tbody>
</table>