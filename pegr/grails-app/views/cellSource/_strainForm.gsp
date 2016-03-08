<ul>
    <li class=" ${hasErrors(bean: strain, field: 'name', 'error')} ">
        <label for="name">Strain Name</label>
        <g:textField name="strainName" value="${strain?.name}"/>
    </li>

    <li class=" ${hasErrors(bean: strain, field: 'parent', 'error')} ">
        <label for="parent">Parent</label>
        <g:select id="parent" name="parent.id" from="${pegr.Strain.list()}" optionKey="id" value="${strain?.parent?.id}" noSelection="['null': '']"/>
    </li>

    <li class=" ${hasErrors(bean: strain, field: 'genotype', 'error')} ">
        <label for="genotype">Genotype</label>
        <g:textField name="genotype" value="${strain?.genotype}"/>
    </li>

    <li class=" ${hasErrors(bean: strain, field: 'geneticModification', 'error')}">
    <label>Genetic Modifications</label>
        <g:textField name="geneticModification" pattern="${pegr.Strain.constraints.geneticModification.matches}"  value="${strain?.geneticModification}"/>
        (use only alphanumeric characters and "-")
    </li>

    <li class=" ${hasErrors(bean: strain, field: 'sourceLab', 'error')} ">
        <label for="sourceLab">Source Lab</label>
        <g:select id="sourceLab" name="sourceLab.id" from="${pegr.Lab.list()}" optionKey="id" value="${strain?.sourceLab?.id}" noSelection="['null': '']"/>
    </li>
</ul>