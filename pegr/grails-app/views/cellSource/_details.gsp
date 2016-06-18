<ul>
    <li>Genus: ${cellSource?.strain?.species?.genusName}</li>
    <li>Species: ${cellSource?.strain?.species?.name}</li>
    <li>Parent Strain: ${cellSource?.strain?.parent}</li>
	<li>Strain: ${cellSource?.strain?.name}</li>
    <li>Genotype: ${cellSource?.strain?.genotype}</li>
    <li>Genetic Modification: ${cellSource?.strain?.geneticModification}</li>
	
    <g:if test="${cellSource?.tissue}">
	<li>Tissue: ${cellSource.tissue}	</li>
	</g:if>
    
    <g:if test="${cellSource?.age}">
	<li>Age: ${cellSource.age}	</li>
	</g:if>
    
	<g:if test="${cellSource?.sex}">
	<li>Sex: ${cellSource.sex}	</li>
	</g:if>
	
    <g:if test="${cellSource?.histology}">
	<li>Histology: ${cellSource.histology}	</li>
	</g:if>
    
    <g:if test="${cellSource?.growthMedia}">
	<li>Growth Media: ${cellSource.growthMedia}	</li>
	</g:if>
        
    <li>Cell Source Treatments:
        ${cellSource.treatments.join("; ")}
    </li>
	
    <li>Provider: 
	<g:if test="${cellSource?.providerUser}">
	    ${cellSource.providerUser}
	</g:if>
    </li>
    <li>Provider Lab:
	<g:if test="${cellSource?.providerLab}">
        ${cellSource.providerLab}
	</g:if>
    </li>
    
	<g:if test="${cellSource?.biologicalSourceId}">
	<li>Biological Source ID: ${cellSource.biologicalSourceId}</li>
	</g:if>

</ul>

