<h4>Traced Samples</h4>
<table class="table table-striped">
    <thead>
        <th>Start State</th>
        <th>End State</th>            
        <th>Antibody</th>
        <th>Index</th>
    </thead>
    <tbody>            
        <g:each in="${samples}" var="sample" status="n">
            <tr>
                <td><g:link controller="item" action="show" id="${parents[n].id}" target="_blank">${parents[n].name}</g:link></td>
                <td><g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link></td> 
                <td><g:link controller="antibody" action="show" id="${sample.antibody}" target="_blank">${sample.antibody}</g:link></td> 
                <td>${sample.sequenceIndicesString}</td>
            </tr>
        </g:each>
    </tbody>
</table>
