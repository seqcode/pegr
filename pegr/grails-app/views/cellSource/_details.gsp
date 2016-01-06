<ul class="property-list">
	<g:if test="${cellSourceInstance?.strain}">
	<li>Strain: ${cellSourceInstance.strain}</li>
	</g:if>
	
	<g:if test="${cellSourceInstance?.tissue}">
	<li>Tissue: ${cellSourceInstance.tissue}</li>
	</g:if>
	
	<g:if test="${cellSourceInstance?.sex}">
	<li>Sex:${cellSourceInstance.sex}	</li>
	</g:if>

	<g:if test="${cellSourceInstance?.age}">
	<li>Age: ${cellSourceInstance.age}</li>
	</g:if>
	
	<g:if test="${cellSourceInstance?.histology}">
	<li>Histology: ${cellSourceInstance.histology}</li>
	</g:if>
	
	<g:if test="${cellSourceInstance?.cellSourceTreatments}">
	<li>Treatments: 
			<g:each in="${cellSourceInstance.cellSourceTreatments}" var="c">
			${c}
			</g:each>
	</li>
	</g:if>
	
	<g:if test="${cellSourceInstance?.providerUser}">
	<li>Provider User: ${cellSourceInstance.providerUser}</li>
	</g:if>

	<g:if test="${cellSourceInstance?.providerLab}">
	<li>Provider User: ${cellSourceInstance.providerLab}</li>
	</g:if>

	<g:if test="${cellSourceInstance?.biologicalSourceId}">
	<li>Biological Source ID: ${cellSourceInstance.biologicalSourceId}</li>
	</g:if>
    
    <g:render template="/item/details" bean="${itemInstance}"
		
</ul>