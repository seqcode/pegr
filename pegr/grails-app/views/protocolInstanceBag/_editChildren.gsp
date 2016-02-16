<h4>Update Samples</h4>
<table class="table table-striped">
    <thead>
        <th>Start State</th>
        <th>End State</th>
    </thead>
    <tbody>        
        <g:each in="${samples}" var="sample" status="n">
            <tr>
                <td><g:link controller="item" action="show" id="${parents[n].id}" target="_blank">${parents[n].name}</g:link></td>
                <td>
                    <g:if test="${children[n]}">
                        <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                    </g:if>
                    <g:else>
                        <g:link action="addChild" params="[sampleId:sample.id, instanceId:instanceId, childTypeId:childType.id]">Add</g:link>
                    </g:else>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>
