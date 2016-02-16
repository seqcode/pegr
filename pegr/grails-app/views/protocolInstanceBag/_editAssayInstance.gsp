<g:form action="addIndex">
    <h4>Traced Samples <g:submitButton name="save" class="btn btn-primary"></g:submitButton></h4>
    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
    
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
                    <td><g:link controller="item" action="show" id="${sample.item.id}" target="_blank">${parents[n].name}</g:link></td>
                    <td>
                        <g:if test="${children[n]}">
                            <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                        </g:if>
                        <g:else>
                            <g:link action="addChild" params="[sampleId:sample.id, instanceId:instanceId, childTypeId:childType.id]">Add</g:link>
                        </g:else>
                    </td>
                    <td>
                        <g:if test="${sample.antibody}">
                            <g:link controller="antibody" action="show" id="${sample.antibody}" target="_blank">${sample.antibody}</g:link>
                        </g:if>
                        <g:else>
                            <g:link action="searchAntibody" params="[sampleId:sampleId, instanceId:instanceId]">Add</g:link>
                        </g:else>
                    </td>
                    <g:hiddenField name="sampleId" value="${sample.id}"/>
                    <td><g:textField name="indexId" value="${sample.sequenceIndicesString}" /></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:form>