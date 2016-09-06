<h4>Traced Samples</h4>
<table class="table table-striped">
    <thead>
        <g:if test="${children}">
            <th>Start State:<br>${protocolInstance?.protocol?.startItemType}</th>
            <th>End State:<br>${protocolInstance?.protocol?.endItemType}</th>  
        </g:if>
        <g:else>
            <th>Sample</th>
        </g:else>
        <g:if test="${protocolInstance?.protocol?.addAntibody}">
            <th>Antibody</th>
        </g:if>
        <g:if test="${protocolInstance?.protocol?.addIndex}">
            <th>Index</th>
        </g:if>        
    </thead>
    <tbody>            
        <g:each in="${parents}" var="parent" status="n">
            <tr>
                <td><g:link controller="item" action="show" id="${parent.id}" target="_blank">${parent.name}</g:link><br>(${parent.type})</td>
                <g:if test="${children}">
                    <td>
                        <g:if test="${children[n]}">
                            <g:link controller="item" action="show" id="${children[n]?.id}" target="_blank">${children[n]?.name}</g:link><br>(${children[n]?.type})
                        </g:if>
                    </td> 
                </g:if>
                <g:if test="${protocolInstance?.protocol?.addAntibody}">
                    <td>
                        <g:if test="${children[n]?.antibody}">
                            <g:link controller="antibody" action="show" id="${children[n]?.antibody?.id}" target="_blank">${children[n]?.antibody}</g:link>
                        </g:if>
                    </td> 
                </g:if>
                <g:if test="${protocolInstance?.protocol?.addIndex}">
                    <td>${children[n]?.sequenceIndicesString}</td>
                </g:if>
            </tr>
        </g:each>
    </tbody>
</table>
