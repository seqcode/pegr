<h4>Protocol group: ${it.name} </h4>
<ol class="list-group">
	<g:each in="${it.protocols}" var="protocol" status="count">
		<g:if test="${count < protInstCount}">
		<li class="list-group-item list-group-item-info">
		<g:link controller="protocolInstance" action="edit" id="${protocolInstances[count].id}" params="[sampleId: sampleId]">
		${protocol.name}</g:link>
		</li>
		</g:if>
		<g:else>
		<li class="list-group-item">${protocol.name}
		<g:if test="${count == protInstCount}">
			<g:if test="${count >0}">
			<g:link controller="protocolInstance" action="create" 
			params="[sampleId: sampleId, protocolId: protocol.id, priorProtInstId: it.protocols[count-1].id]" 
			class="btn btn-info">Start</g:link>
			</g:if>
			<g:else>
			<g:link controller="protocolInstance" action="create" 
			params="[sampleId: sampleId, protocolId: protocol.id, priorProtInstId: null]" 
			class="btn btn-info">Start</g:link>
			</g:else>
		</g:if>
		</li>
		</g:else>
	</g:each>
</ol>