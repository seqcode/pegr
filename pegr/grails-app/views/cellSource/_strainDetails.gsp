<ul>
    <li>Genotype: ${strain?.genotype}</li>
    <g:if test="${strain?.geneticModification}">
        <li>Genetic Modification: ${strain?.geneticModification}</li>
    </g:if>
    <g:if test="${strain?.parent}">
        <li>Parent Strain: ${strain?.parent}</li>
    </g:if>
</ul>