<%@ page import="pegr.Strain" %>


<div class=" ${hasErrors(bean: strain, field: 'species', 'error')} required">
	<label for="species">Species<span class="required-indicator">*</span>
	</label>
	<g:select id="species" name="species.id" from="${pegr.Species.list()}" optionKey="id" required="" value="${strain?.species?.id}" noSelection="['null': '']" onchange="speciesChanged(this.value);"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'name', 'error')} required">
	<label for="name">Strain</label>
	<g:textField name="name" pattern="${Strain.constraints.name.matches}" value="${strain?.name}"/>
    (use only alphanumeric characters and "-")
</div>

<div class=" ${hasErrors(bean: strain, field: 'parent', 'error')} ">
	<label for="parent">Parent</label>
	<g:select id="parent" name="parent.id" from="${pegr.Strain.list()}" optionKey="id" value="${strain?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'genotype', 'error')} ">
	<label for="genotype">Genotype</label>
	<g:textField name="genotype" value="${strain?.genotype}"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'geneticModification', 'error')}">
<label>Genetic Modifications</label>
	<g:textField name="geneticModification" pattern="${Strain.constraints.geneticModification.matches}"  value="${strain?.geneticModification}"/>
    (use only alphanumeric characters and "-")
</div>

<div class=" ${hasErrors(bean: strain, field: 'sourceLab', 'error')} ">
	<label for="sourceLab">Source Lab</label>
	<g:select id="sourceLab" name="sourceLab.id" from="${pegr.Lab.list()}" optionKey="id" value="${strain?.sourceLab?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'note', 'error')} ">
	<label for="note">Note</label>
	<g:textArea name="note" value="${strain?.note}"/>

</div>

<script type="text/javascript">
    function speciesChanged(speciesId) {
        <g:remoteFunction controller="strainAdmin" action="speciesChangedAjax"
            update="genotype"
            params="'speciesId='+speciesId"/>
    }
            
    jQuery(document).ready(function($) {
        $('#search').multipleselect({
            search: {
                left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            },   
            unique: "true"
        });    
    });
</script>

