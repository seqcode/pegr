<ul class="property-list">
	<g:if test="${cellCultureInstance?.strain}">
	<li>Strain: ${cellCultureInstance.strain}</li>
	</g:if>
	
	<g:if test="${cellCultureInstance?.tissue}">
	<li>Tissue: ${cellCultureInstance.tissue}</li>
	</g:if>
	
	<g:if test="${cellCultureInstance?.sex}">
	<li>Sex:${cellCultureInstance.sex}	</li>
	</g:if>

	<g:if test="${cellCultureInstance?.age}">
	<li>Age: ${cellCultureInstance.age}</li>
	</g:if>
	
	<g:if test="${cellCultureInstance?.histology}">
	<li>Histology: ${cellCultureInstance.histology}</li>
	</g:if>
	
	<g:if test="${cellCultureInstance?.cellCultureTreatments}">
	<li>Treatments: 
			<g:each in="${cellCultureInstance.cellCultureTreatments}" var="c">
			${c}
			</g:each>
	</li>
	</g:if>
	
	<g:if test="${cellCultureInstance?.providerUser}">
	<li>Provider User: ${cellCultureInstance.providerUser}</li>
	</g:if>

	<g:if test="${cellCultureInstance?.providerLab}">
	<li>Provider User: ${cellCultureInstance.providerLab}</li>
	</g:if>

	<g:if test="${cellCultureInstance?.biologicalSourceId}">
	<li>Biological Source ID: ${cellCultureInstance.biologicalSourceId}</li>
	</g:if>
    
    <g:render template="/item/details" bean="${itemInstance}" />
		
</ul>