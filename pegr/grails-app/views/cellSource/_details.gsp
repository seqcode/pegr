<g:if test="${item?.name}">
<h4>Name: ${item.name}</h4>
</g:if>
<ul class="property-list">
    <g:if test="${item?.type}">
    <li>Type: ${item.type}</li>
    </g:if>
    
	<g:if test="${object?.strain?.name}">
	<li>Strain: ${object.strain.name}</li>
        <ul>
            <li>Species: ${object?.strain?.genotype?.species}</li>
            <li>Genotype: ${object?.strain?.genotype}</li>
            <li>Genetic Modifications: <g:each in="${object?.strain?.geneticModifications}">${it} </g:each></li>
            <li>Parent Strain: ${object?.parent}</li>
        </ul>
	</g:if>
	
	<g:if test="${object?.sex}">
	<li>Sex:${object.sex}	</li>
	</g:if>
	
	<g:if test="${object?.objectTreatments}">
	<li>Treatments: 
        <g:each in="${object.objectTreatments}" var="c">
			${c}
        </g:each>
	</li>
	</g:if>
	
    <li>Provider: 
	<g:if test="${object?.providerUser}">
	   ${object.providerUser}
	</g:if>
	<g:if test="${object?.providerLab}">
        ${object.providerLab}
	</g:if>
    </li>
    
	<g:if test="${object?.biologicalSourceId}">
	<li>Biological Source ID: ${object.biologicalSourceId}</li>
	</g:if>
    
    <g:if test="${item?.barcode}">
    <li>Barcode: ${item.barcode }</li>
    </g:if>	

    <g:if test="${item?.location}">
    <li>Location: ${item.location}</li>
    </g:if>	

    <g:if test="${item?.notes}">
    <li>Notes: ${item.notes}</li>
    </g:if>
		
</ul>