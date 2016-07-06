<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<ul>
    <g:each in="${replicates}">
        <li>
            <h5>${it.key} Replicate Sets</h5>
            <ul>
                <g:if test="${it.value.size()}">                
                    <g:each in="${it.value}">
                        <li>
                            <g:link controller="replicate" action="show" id="${it.id}">Set ${it.id}</g:link>
                        </li>
                    </g:each>
                </g:if>
                <g:else>None</g:else>
            </ul>            
        </li>
    </g:each>
</ul>