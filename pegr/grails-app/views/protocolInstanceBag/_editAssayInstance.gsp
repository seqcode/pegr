<g:form action="addIndex">
    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
    <h4>Traced Samples</h4>
    
    <table class="table table-striped">
        <thead>
            <th>Start State</th>
            <th>End State</th>
            <th>Antibody</th>
            <th>Index <g:submitButton name="save" class="edit"></g:submitButton></th>
        </thead>
        <tbody>            
            <g:each in="${samples}" var="sample" status="n">
                <tr>
                    <td><g:link controller="item" action="show" id="${sample.item.id}" target="_blank">${parents[n].name}</g:link></td>
                    <td>
                        <g:if test="${children[n]}">
                            <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                            <g:link action="removeChild" params="[sampleId: sample.id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                        </g:if>
                        <g:else>
                            <g:link action="addChild" params="[sampleId:sample.id, instanceId:instanceId, childTypeId:childType.id]"><span class="glyphicon glyphicon-plus"></span></g:link>
                        </g:else>
                    </td>
                    <td>
                        <g:if test="${sample.antibody}">
                            <g:link controller="antibody" action="show" id="${sample.antibody}" target="_blank">${sample.antibody}</g:link>
                            <g:link action="removeAntibodyFromSample" params="[sampleId: sample.id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                        </g:if>
                        <g:else>
                            <g:link action="searchAntibody" params="[sampleId:sample.id, instanceId:instanceId]"><span class="glyphicon glyphicon-plus"></span></g:link>
                        </g:else>
                    </td>
                    <g:hiddenField name="sampleId" value="${sample.id}"/>
                    <td><g:textField name="indexId" value="${sample.sequenceIndicesString}" /></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:form>
<p>* multiple indices for a single sample should be separated by comma ","</p>