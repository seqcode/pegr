<ul>
    <g:if test="${object?.company}">
    <li>Company: ${object?.company}</li>
    </g:if>

    <g:if test="${object?.catalogNumber}">
    <li>Catalog Number: ${object.catalogNumber}</li>
    </g:if>

    <g:if test="${object?.lotNumber}">
    <li>Lot Number: ${object.lotNumber}</li>
    </g:if>
    
    <g:if test="${object?.abHost}">
    <li>Ab Host: ${object?.abHost}</li>
    </g:if>   
    
    <g:if test="${object?.immunogene}">
    <li>Immunogene: ${object.immunogene}</li>
    </g:if>
    
    <g:if test="${object?.clonal}">
    <li>Mono/Poly Clonal: ${object.clonal}</li>
    </g:if>

    <g:if test="${object?.igType}">
    <li>Ig Type: ${object?.igType}</li>
    </g:if>  

    <g:if test="${object?.concentration != null}">
    <li>Concentration: ${object.concentration}</li>
    </g:if> 
    
    <g:if test="${object?.externalId}">
    <li>External ID: ${object.externalId}</li>
    </g:if>

    <g:if test="${object?.inventoryId}">
    <li>Inventory ID: ${object.inventoryId}</li>
    </g:if>
    
    <h5>Default Target</h5>
    <ul>
        <g:if test="${object?.defaultTarget?.targetType}">
        <li>Target Type: ${object?.defaultTarget?.targetType?.name}</li>
        </g:if>

        <g:if test="${object?.defaultTarget?.name}">
        <li>Target: ${object?.defaultTarget?.name}</li>
        </g:if>

        <g:if test="${object?.defaultTarget?.cTermTag}">
        <li>C-Term: ${object?.defaultTarget?.cTermTag}</li>
        </g:if>

        <g:if test="${object?.defaultTarget?.nTermTag}">
        <li>N-Term: ${object?.defaultTarget?.nTermTag}</li>
        </g:if>
    </ul>
</ul>