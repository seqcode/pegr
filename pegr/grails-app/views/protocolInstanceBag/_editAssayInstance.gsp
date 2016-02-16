<h4>Traced Samples</h4>
<g:form action="addIndex">
    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
    <g:submitButton name="save" clas="btn btn-primary"></g:submitButton>
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
                            <g:link action="addChild" params="[sampleId:sample.id, instanceId:instanceId]">Add</g:link>
                        </g:else>
                    </td>
                    <td>
                        <g:if test="${sample.antibody}">
                            <g:link controller="antibody" action="show" id="${sample.antibody}" target="_blank">${sample.antibody}</g:link>
                        </g:if>
                        <g:else>
                            <g:link action="addAntibody" params="[sampleId:sampleId]">Add</g:link>
                        </g:else>
                    </td>
                    <g:hiddenField name="sampleId" value="${sample.id}"/>
                    <td><g:textField name="indexId" value="${sample.sequenceIndicesString}" /></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:form>