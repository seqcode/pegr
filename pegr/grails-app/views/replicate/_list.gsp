<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<ul>
    <g:each in="${replicates}">
        <li>
            <h5>${it.key} Replicates <g:link class="edit" controller="replicate" action="edit" params="[sampleId:sample.id,type:it.key]">Edit</g:link></h5>
            <p>
                <g:if test="${it.value.size()}"> 
                    <g:each in="${it.value}">
                        <g:link controller="sample" action="show" id="${it}">${it} </g:link>
                    </g:each>
                </g:if>
                <g:else>None</g:else>
            </p>            
        </li>
    </g:each>
</ul>