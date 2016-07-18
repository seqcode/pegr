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
                <th><a href="#" onclick="window.open('${g.createLink(controller:'project',action:'sampleSubmissionHelp', fragment:'sequence-index')}', 'Help: Sample Submission', 'width=600,height=400' )">Index <span class="glyphicon glyphicon-question-sign"></span></a> 
                        <label class="radio-inline"><input type="radio" name="indexType" value="ID">ID</label>
                        <label class="radio-inline"><input type="radio" name="indexType" value="Sequence" checked>Sequence</label></th>
            </g:if>    
        </thead>
        <tbody>            
            <g:each in="${samples}" var="sample" status="n">
                <tr>
                    <td><g:link controller="item" action="show" id="${parents[n].id}" target="_blank">${parents[n].name}</g:link></td>
                    <g:if test="${children}">
                        <td>
                            <g:if test="${children[n]}">
                                <g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link>
                                <g:link action="removeChild" params="[sampleId: sample.id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                                <g:link action="addChild" params="[sampleId: sample.id, instanceId: instanceId, childTypeId: childType.id, split: true]" class="edit">Split</g:link>
                            </g:if>
                            <g:else>
                                <g:link action="addChild" params="[sampleId:sample.id, instanceId:instanceId, childTypeId:childType.id]"><span class="glyphicon glyphicon-plus"></span></g:link>
                            </g:else>
                        </td> 
                    </g:if>
                    <g:if test="${protocolInstance?.protocol?.addAntibody}">
                        <td>
                            <g:if test="${sample.antibody}">
                                <g:link controller="antibody" action="show" id="${sample.antibody.id}" target="_blank">${sample.antibody}</g:link>
                                <g:link action="removeAntibodyFromSample" params="[sampleId: sample.id, instanceId: instanceId]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
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
    <div class="row">
        <g:submitButton name="save" class="btn btn-primary pull-right" value="Save Index"></g:submitButton>
    </div>
    
</g:form>
