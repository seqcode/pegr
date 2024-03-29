<%@ page import="pegr.Strain" %>


<div class=" ${hasErrors(bean: strain, field: 'species', 'error')} required">
	<label for="species">Species<span class="required-indicator">*</span>
	</label>
	<g:select id="species" name="species.id" from="${pegr.Species.list()}" optionKey="id" required="" value="${strain?.species?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: strain, field: 'name', 'error')} required">
	<label for="name">Name</label>
	<g:textField name="name" value="${strain?.name}"/>
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
	<g:textField name="geneticModification" value="${strain?.geneticModification}"/>
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

<div class=" ${hasErrors(bean: strain, field: 'status', 'error')} ">
	<label for="status">Status</label>
	<g:select name="status" from="${pegr.DictionaryStatus.values()}" keys="${pegr.DictionaryStatus.values()*.name()}"  value="${strain?.status}" noSelection="${['':'Select...']}"/>

</div>

<script type="text/javascript">
    jQuery(document).ready(function($) {
        $('#search').multipleselect({
            search: {
                left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            },   
            unique: "true"
        });    
    });
</script>

