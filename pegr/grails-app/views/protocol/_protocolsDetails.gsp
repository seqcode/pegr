<h4>Protocol group: ${it.name} </h4>
<ol class="list-group">
<g:each in="${it.protocols}" var="protocol" status="count">
    <g:if test="${count < protInstCount}">
        <li class="list-group-item list-group-item-info">${protocol.name}
            <g:link class="btn btn-info" controller="protocol" action="showInstanceForSample" params="[prtclInstanceId: protocolInstances[count].id, sampleId: sampleId]">Details</g:link>
        </li>
	</g:if>
	<g:else>
		<li class="list-group-item">${protocol.name}
		<g:if test="${count == protInstCount}">
			<g:if test="${count >0}">
                <g:if test="${protocolInstances[count-1].completed}">
			         <g:link controller="protocol" action="createInstanceForSample" 
			params="[sampleId: sampleId, protocolId: protocol.id, priorProtInstId: it.protocols[count-1].id]" 
			class="btn btn-success">Start</g:link>
                </g:if>
			</g:if>
			<g:else>
			<g:link controller="protocol" action="createInstanceForSample" 
			params="[sampleId: sampleId, protocolId: protocol.id, priorProtInstId: null]" 
			class="btn btn-success">Start</g:link>
			</g:else>
		</g:if>
        </li>
	</g:else>
</g:each>
</ol>