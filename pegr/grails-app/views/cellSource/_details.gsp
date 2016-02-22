<ul class="property-list">

	<li>Strain: ${object?.strain?.name}</li>
        <ul>
            <li>Species: ${object?.strain?.species}</li>
            <li>Genotype: ${object?.strain?.genotype}</li>
            <g:if test="${object?.strain?.geneticModification}">
                <li>Genetic Modification: ${object?.strain?.geneticModification}</li>
            </g:if>
            <g:if test="${object?.strain?.parent}">
                <li>Parent Strain: ${object?.strain?.parent}</li>
            </g:if>
        </ul>
	
	<g:if test="${object?.sex}">
	<li>Sex:${object.sex}	</li>
	</g:if>
    
    <g:if test="${object?.age}">
	<li>Age:${object.age}	</li>
	</g:if>
    
    <g:if test="${object?.tissue}">
	<li>Tissue:${object.tissue}	</li>
	</g:if>
	
    <g:if test="${object?.histology}">
	<li>Histology:${object.histology}	</li>
	</g:if>
    
    <g:if test="${object?.growthMedia}">
	<li>Growth Media:${object.growthMedia}	</li>
	</g:if>
	
    <li>Provider: 
	<g:if test="${object?.providerUser}">
	   ${object.providerUser}
	</g:if>
	<g:if test="${object?.providerLab}">
        , ${object.providerLab}
	</g:if>
    </li>
    
	<g:if test="${object?.biologicalSourceId}">
	<li>Biological Source ID: ${object.biologicalSourceId}</li>
	</g:if>
    
    <li>Cell Source Treatments:
        <g:each in="${object.treatments}" var="c">
            ${c}
        </g:each>	
    </li>
</ul>

