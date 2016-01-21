<ul class="property-list">
	<g:if test="${cellCulture?.strain?.name}">
	<li>Strain: ${cellCulture.strain.name}</li>
	</g:if>
	
	<g:if test="${cellCulture?.sex}">
	<li>Sex:${cellCulture.sex}	</li>
	</g:if>
	
	<g:if test="${cellCulture?.cellCultureTreatments}">
	<li>Treatments: 
        <g:each in="${cellCulture.cellCultureTreatments}" var="c">
			${c}
        </g:each>
	</li>
	</g:if>
	
	<g:if test="${cellCulture?.providerUser}">
	<li>Provider User: ${cellCulture.providerUser}</li>
	</g:if>

	<g:if test="${cellCulture?.providerLab}">
	<li>Provider User: ${cellCulture.providerLab}</li>
	</g:if>

	<g:if test="${cellCulture?.biologicalSourceId}">
	<li>Biological Source ID: ${cellCulture.biologicalSourceId}</li>
	</g:if>
    
    <g:render template="/item/details" bean="${itemInstance}" />
		
</ul>