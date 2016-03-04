
<div class=" ${hasErrors(bean: strain, field: 'parent', 'error')} ">
	<label for="parent">Parent</label>
	<g:select id="parent" name="parent.id" from="${pegr.Strain.list()}" optionKey="id" value="${strain?.parent?.id}" noSelection="['null': '']"/>
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
