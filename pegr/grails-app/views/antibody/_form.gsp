<%@ page import="pegr.Antibody" %>

<div class="${hasErrors(bean: antibody, field: 'abHost', 'error')} required">
	<label for="abHost">Ab Host<span class="required-indicator">*</span></label>
	<g:select id="abHost" name="abHost.id" from="${pegr.AbHost.list()}" optionKey="id" required="" value="${antibody?.abHost?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'concentration', 'error')} required">
	<label for="concentration">Concentration (ug/ul)</label>
	<g:field name="concentration" value="${fieldValue(bean: antibody, field: 'concentration')}" />
</div>

<div class="${hasErrors(bean: antibody, field: 'immunogene', 'error')} ">
	<label for="immunogene">Immunogene</label>
	<g:textField name="immunogene" value="${antibody?.immunogene}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'clonal', 'error')} ">
	<label for="clonal">Mono/Poly Clonal</label>
	<g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${antibody?.clonal?.name()}"  noSelection="['': '']"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'igType', 'error')} ">
	<label for="igType">Ig Type</label>
	<g:select id="igType" name="igType.id" from="${pegr.IgType.list()}" optionKey="id" value="${antibody?.igType?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'company', 'error')} ">
	<label for="company">Company</label>
	<g:select id="company" name="company.id" from="${pegr.Company.list()}" optionKey="id" value="${antibody?.company?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'catalogNumber', 'error')} ">
	<label for="catalogNumber">Catalog Number</label>
	<g:textField name="catalogNumber" value="${antibody?.catalogNumber}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'lotNumber', 'error')} ">
	<label for="lotNumber">Lot Number</label>
	<g:textField name="lotNumber" value="${antibody?.lotNumber}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'externalId', 'error')} ">
	<label for="externalId">External ID</label>
	<g:textField name="externalId" value="${antibody?.externalId}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'inventoryId', 'error')} ">
	<label for="inventoryId">Inventory ID</label>
	<g:textField name="inventoryId" value="${antibody?.inventoryId}"/>

</div>