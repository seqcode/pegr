<ul>
	<li>Strain: ${cellSource?.strain?.name}</li>
        <ul>
            <li>Species: ${cellSource?.strain?.species}</li>
            <li>Genotype: ${cellSource?.strain?.genotype}</li>
            <g:if test="${cellSource?.strain?.geneticModification}">
                <li>Genetic Modification: ${cellSource?.strain?.geneticModification}</li>
            </g:if>
            <g:if test="${cellSource?.strain?.parent}">
                <li>Parent Strain: ${cellSource?.strain?.parent}</li>
            </g:if>
        </ul>
	
	<g:if test="${cellSource?.sex}">
	<li>Sex:${cellSource.sex}	</li>
	</g:if>
    
    <g:if test="${cellSource?.age}">
	<li>Age:${cellSource.age}	</li>
	</g:if>
    
    <g:if test="${cellSource?.tissue}">
	<li>Tissue:${cellSource.tissue}	</li>
	</g:if>
	
    <g:if test="${cellSource?.histology}">
	<li>Histology:${cellSource.histology}	</li>
	</g:if>
    
    <g:if test="${cellSource?.growthMedia}">
	<li>Growth Media:${cellSource.growthMedia}	</li>
	</g:if>
	
    <li>Provider: 
	<g:if test="${cellSource?.providerUser}">
	   ${cellSource.providerUser}
	</g:if>
	<g:if test="${cellSource?.providerLab}">
        , ${cellSource.providerLab}
	</g:if>
    </li>
    
	<g:if test="${cellSource?.biologicalSourceId}">
	<li>Biological Source ID: ${cellSource.biologicalSourceId}</li>
	</g:if>
    
    <li>Cell Source Treatments:
        <g:each in="${cellSource.treatments}" var="c">
            ${c}
        </g:each>	
    </li>
</ul>

