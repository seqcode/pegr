<%@ page import="pegr.Strain" %>

<div class=" ${hasErrors(bean: strain, field: 'name', 'error')} required">
	<label for="name">Name<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" pattern="${Strain.constraints.name.matches}" required="" value="${strain?.name}"/>
    (use only alphanumeric characters and "-")
</div>

<div class=" ${hasErrors(bean: strain, field: 'species', 'error')} required">
	<label for="species">Species<span class="required-indicator">*</span>
	</label>
	<g:select id="species" name="species.id" from="${pegr.Species.list()}" optionKey="id" required="" value="${strain?.species?.id}" noSelection="['null': '']" onchange="speciesChanged(this.value);"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'genotype', 'error')} ">
	<label for="genotype">Genotype</label>
	<g:select id="genotype" name="genotype.id" from="${pegr.Genotype.list()}" optionKey="id" value="${strain?.genotype?.id}"  noSelection="['null': '']"/>
</div>

<label>Genetic Modifications</label>
<div class="row">
    <div class="col-sm-3">
	   <g:select name="from[]" id="search" class="form-control" multiple="multiple" from="${pegr.GeneticModification.list()}" optionKey="id" ></g:select>
	</div>
    <div class="col-sm-1">
        <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
		<button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
    </div>
    <div class="col-sm-3">
        <select name="geneticModifications" id="search_to" class="form-control" size="8" multiple="multiple">
            <g:each in="${geneticModifications}">
            <option value="${it.id}" >${it}</option>
            </g:each>
        </select>
    </div>
</div>

<div class=" ${hasErrors(bean: strain, field: 'parent', 'error')} ">
	<label for="parent">Parent</label>
	<g:select id="parent" name="parent.id" from="${pegr.Strain.list()}" optionKey="id" value="${strain?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
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

