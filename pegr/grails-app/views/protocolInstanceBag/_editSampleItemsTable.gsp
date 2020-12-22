<h4>Traced Samples <g:render template="print"></g:render>
    <g:link controller="sample" action="batchEdit" params="[bagId:protocolInstance?.bag?.id]" class="edit" target="_blank">Edit</g:link>
    <g:link class="edit" controller="item" action="batchEdit" params="[instanceId: protocolInstance?.id]" target="_blank">Customized Fields</g:link>
</h4>
<g:form action="addIndex">
    <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
    <table class="table table-striped">
        <thead>
            <g:if test="${children}">
                <th>Start State:<br>${protocolInstance?.protocol?.startItemType}</th>
                <th>End State:<g:if test="${!children.any { it }}"><g:link action="addAllChildren" params="[instanceId: protocolInstance.id]" class="edit" style="color:white">+All</g:link></g:if><br>${protocolInstance?.protocol?.endItemType}</th>  
            </g:if>
            <g:else>
                <th>Sample</th>
            </g:else>
            <g:if test="${protocolInstance.protocol.addAntibody}">
                <th>Antibody</th>
            </g:if>
            <g:if test="${protocolInstance.protocol.addIndex}">
                <th><a href="#" onclick="window.open('/pegr/help/sequenceIndexHelp#sequence-index', 'Help: Sample Submission', 'width=600,height=400' )">Index <span class="glyphicon glyphicon-question-sign"></span></a> </th>
            </g:if>    
        </thead>
        <tbody>            
            <g:each in="${parents}" var="parent" status="n">
                <tr>
                    <td><g:link controller="item" action="show" id="${parent.id}" target="_blank">${parent.name}</g:link><br>(${parent.type})</td>
                    <g:if test="${children}">
                        <td>
                            <g:if test="${children[n]}">
                                <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                                <g:link action="removeChild" params="[childItemId: children[n].id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                                <g:link action="addChild" params="[parentItemId: parent.id, instanceId: instanceId, childTypeId: childType.id, split: true]" class="edit">Split</g:link><br>(${children[n]?.type})
                            </g:if>
                            <g:else>
                                <g:link action="addChild" params="[parentItemId: parent.id, instanceId:instanceId, childTypeId:childType.id]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:else>
                        </td> 
                    </g:if>
                    <g:if test="${protocolInstance?.protocol?.addAntibody}">
                        <td>
                          <g:if test="${children}">
                            <g:if test="${children[n]?.antibody}">
                                <g:link controller="antibody" action="show" id="${children[n].antibody.id}" target="_blank">${children[n].antibody}</g:link>
                                <g:link action="removeAntibodyFromTracedSample" params="[itemId: children[n].id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                            </g:if>
                            <g:elseif test="${children[n]}">
                                <g:link action="searchAntibody" params="[itemId:children[n].id, instanceId:instanceId]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:elseif>
                          </g:if>
                          <g:else>
                              <g:if test="${parents[n]?.antibody}">
                                <g:link controller="antibody" action="show" id="${parents[n].antibody.id}" target="_blank">${parents[n].antibody}</g:link>
                                <g:link action="removeAntibodyFromTracedSample" params="[itemId: parents[n].id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                            </g:if>
                            <g:elseif test="${parents[n]}">
                                <g:link action="searchAntibody" params="[itemId:parents[n].id, instanceId:instanceId]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:elseif>
                          </g:else>  
                        </td> 
                    </g:if>
                    <g:if test="${protocolInstance?.protocol?.addIndex}">
                        <td>
                          <g:if test="${children}">
                              <g:if test="${children[n]}">
                                <g:hiddenField name="itemId" value="${children[n].id}"/>
                                <g:textField name="indexId" value="${children[n].sequenceIndicesIdString}" size="50"/>
                                ${children[n].sequenceIndicesString}
                            </g:if>
                          </g:if>    
                          <g:else>
                              <g:if test="${parents[n]}">
                                <g:hiddenField name="itemId" value="${parents[n].id}"/>
                                <g:textField name="indexId" value="${parents[n].sequenceIndicesIdString}" size="50"/>
                                ${parents[n].sequenceIndicesString}
                            </g:if>
                          </g:else>
                        </td>
                    </g:if>
                </tr>
            </g:each>
        </tbody>
    </table>
    <g:if test="${protocolInstance?.protocol?.addIndex}">
    <div class="text-center" style="margin:12px">
        <g:submitButton name="save" class="btn btn-primary" value="Save Index"></g:submitButton>
    </div>
    </g:if>
</g:form>
