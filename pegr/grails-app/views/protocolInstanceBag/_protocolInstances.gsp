<ul class="list-group">
<g:each in="${protocolInstances}" var="it" status="count">
    <g:if test="${count < completedCount}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading"><g:link action="showInstance" params="[prtclInstanceId: it.id]">${it.protocol.name} <span class="badge">Completed</span></g:link></h4>
            <p class="list-group-item-text">User: ${it.user}, updated: ${it.lastUpdated}</p>
        </li>
	</g:if>
	<g:elseif test="${count == completedCount}">
        <g:if test="${it.status == pegr.ProtocolStatus.INACTIVE}">
            <li class="list-group-item"> 
                <h4 class="list-group-item-heading">${it.protocol.name} <g:link  action="showInstance" params="[prtclInstanceId: it.id]" class="label label-success">Start</g:link></h4>
            </li>

        </g:if>
        <g:else>
            <li class="list-group-item">
                <h4 class="list-group-item-heading"><g:link action="showInstance" params="[prtclInstanceId: it.id]">${it.protocol.name} <span class="badge">Processing</span></g:link> </h4>
                <p class="list-group-item-text">List Group Item Text</p>
            </li>            
        </g:else>
    </g:elseif>
    <g:else>
        <li class="list-group-item">
            <h4 class="list-group-item-heading">${it.protocol.name}</h4>
        </li>
    </g:else>
</g:each>
</ul>