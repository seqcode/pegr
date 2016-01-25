<ul class="property-list">
	<g:if test="${cellSource?.strain?.name}">
	<li>Strain: ${cellSource.strain.name}</li>
	</g:if>
	
	<g:if test="${cellSource?.sex}">
	<li>Sex:${cellSource.sex}	</li>
	</g:if>
	
	<g:if test="${cellSource?.cellSourceTreatments}">
	<li>Treatments: 
        <g:each in="${cellSource.cellSourceTreatments}" var="c">
			${c}
        </g:each>
	</li>
	</g:if>
	
	<g:if test="${cellSource?.providerUser}">
	<li>Provider User: ${cellSource.providerUser}</li>
	</g:if>

	<g:if test="${cellSource?.providerLab}">
	<li>Provider User: ${cellSource.providerLab}</li>
	</g:if>

	<g:if test="${cellSource?.biologicalSourceId}">
	<li>Biological Source ID: ${cellSource.biologicalSourceId}</li>
	</g:if>
    
    <g:render template="/item/details" bean="${itemInstance}" />
		
</ul>