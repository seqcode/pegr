<h4>Traced Samples</h4>
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
            <th>Index</th>
        </g:if>        
    </thead>
    <tbody>            
        <g:each in="${samples}" var="sample" status="n">
            <tr>
                <td><g:link controller="item" action="show" id="${parents[n].id}" target="_blank">${parents[n].name}</g:link></td>
                <g:if test="${children}">
                    <td><g:link controller="item" action="show" id="${children[n].id}" target="_blank">${children[n].name}</g:link></td> 
                </g:if>
                <g:if test="${protocolInstance.protocol.addAntibody}">
                    <td><g:link controller="antibody" action="show" id="${sample.antibody}" target="_blank">${sample.antibody}</g:link></td> 
                </g:if>
                <g:if test="${protocolInstance.protocol.addIndex}">
                    <td>${sample.sequenceIndicesString}</td>
                </g:if>
            </tr>
        </g:each>
    </tbody>
</table>
