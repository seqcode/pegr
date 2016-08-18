<ul class="list-group">
<g:each in="${protocolInstances}" var="it" status="count">
    <g:if test="${count < completedCount}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading"><g:link action="showInstance" id="${it.id}">${it.protocol.name} <span class="badge">Completed</span></g:link></h4>
            <p class="list-group-item-text">User: ${it.user}, Start Time: <g:formatDate format="yyyy-MM-dd" date="${it.startTime}"/>
    <g:if test="${it.endTime}">, End Time: <g:formatDate format="yyyy-MM-dd" date="${it.endTime}"/></g:if></p>
        </li>
	</g:if>
	<g:elseif test="${count == completedCount}">
        <g:if test="${it.status == pegr.ProtocolStatus.INACTIVE}">
            <li class="list-group-item"> 
                <h4 class="list-group-item-heading">${it.protocol.name} 
                    <g:if test="${count == 0}">
                        <g:link  action="startInstance" id="${it.id}" class="label label-success confirm-start-first">Start</g:link>
                    </g:if>
                    <g:else>
                        <g:link  action="startInstance" id="${it.id}" class="label label-success confirm-start">Start</g:link>
                    </g:else>
                </h4>
            </li>
        </g:if>
        <g:else>
            <li class="list-group-item">
                <h4 class="list-group-item-heading"><g:link action="showInstance" id="${it.id}">${it.protocol.name} <span class="badge">Processing</span></g:link> </h4>
                <p class="list-group-item-text">User: ${it.user}, Start Time: <g:formatDate format="yyyy-MM-dd" date="${it.startTime}"/>
    <g:if test="${it.endTime}">, End Time: <g:formatDate format="yyyy-MM-dd" date="${it.endTime}"/></g:if></p>
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