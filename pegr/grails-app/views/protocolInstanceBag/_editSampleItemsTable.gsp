<h4>Traced Samples</h4>
<g:form action="addIndex">
    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
    <table class="table table-striped">
        <thead>
            <g:if test="${children}">
                <th>Start State</th>
                <th>End State</th>  
            </g:if>
            <g:else>
                <th>Sample</th>
            </g:else>
            <g:if test="${protocolInstance.protocol.addAntibody}">
                <th>Antibody</th>
            </g:if>
            <g:if test="${protocolInstance.protocol.addIndex}">
                <th><a href="#" onclick="window.open('/pegr/help#sequence-index', 'Help: Sample Submission', 'width=600,height=400' )">Index <span class="glyphicon glyphicon-question-sign"></span></a> 
                        <label class="radio-inline"><input type="radio" name="indexType" value="ID">ID</label>
                        <label class="radio-inline"><input type="radio" name="indexType" value="Sequence" checked>Sequence</label></th>
            </g:if>    
        </thead>
        <tbody>            
            <g:each in="${parents}" var="parent" status="n">
                <tr>
                    <td><g:link controller="item" action="show" id="${parent.id}" target="_blank">${parent.name}</g:link></td>
                    <g:if test="${children}">
                        <td>
                            <g:if test="${children[n]}">
                                <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                                <g:link action="removeChild" params="[childItemId: children[n].id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                                <g:link action="addChild" params="[parentItemId: parent.id, instanceId: instanceId, childTypeId: childType.id, split: true]" class="edit">Split</g:link>
                            </g:if>
                            <g:else>
                                <g:link action="addChild" params="[parentItemId: parent.id, instanceId:instanceId, childTypeId:childType.id]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:else>
                        </td> 
                    </g:if>
                    <g:if test="${protocolInstance?.protocol?.addAntibody}">
                        <td>
                            <g:if test="${children[n]?.antibody}">
                                <g:link controller="antibody" action="show" id="${children[n].antibody.id}" target="_blank">${children[n].antibody}</g:link>
                                <g:link action="removeAntibodyFromTracedSample" params="[itemId: children[n].id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                            </g:if>
                            <g:else>
                                <g:link action="searchAntibody" params="[sampleId:sample.id, instanceId:instanceId]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:else>
                        </td> 
                    </g:if>
                    <g:hiddenField name="sampleId" value="${sample.id}"/>
                    <g:if test="${protocolInstance?.protocol?.addIndex}">
                        <td><g:textField name="indexId" value="${sample.sequenceIndicesString}" size="50"/></td>
                    </g:if>
                </tr>
            </g:each>
        </tbody>
    </table>
    <g:if test="${protocolInstance?.protocol?.addIndex}">
    <div class="row">
        <g:submitButton name="save" class="btn btn-primary pull-right" value="Save Index"></g:submitButton>
    </div>
    </g:if>
</g:form>
