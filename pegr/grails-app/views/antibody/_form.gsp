<%@ page import="pegr.Antibody" %>

<div class="${hasErrors(bean: object, field: 'abHost', 'error')} required">
	<label for="abHost">Ab Host<span class="required-indicator">*</span></label>
	<g:select id="abHost" name="abHost.id" from="${pegr.AbHost.list()}" optionKey="id" required="" value="${object?.abHost?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: object, field: 'concentration', 'error')} required">
	<label for="concentration">Concentration (ug/ul)</label>
	<g:field name="concentration" value="${fieldValue(bean: object, field: 'concentration')}" />
</div>

<div class="${hasErrors(bean: object, field: 'immunogene', 'error')} ">
	<label for="immunogene">Immunogene</label>
	<g:textField name="immunogene" value="${object?.immunogene}"/>
</div>

<div class="${hasErrors(bean: object, field: 'clonal', 'error')} ">
	<label for="clonal">Mono/Poly Clonal</label>
	<g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${object?.clonal?.name()}"  noSelection="['': '']"/>
</div>

<div class="${hasErrors(bean: object, field: 'igType', 'error')} ">
	<label for="igType">Ig Type</label>
	<g:select id="igType" name="igType.id" from="${pegr.IgType.list()}" optionKey="id" value="${object?.igType?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: object, field: 'company', 'error')} ">
	<label for="company">Company</label>
	<g:select id="company" name="company.id" from="${pegr.Company.list()}" optionKey="id" value="${object?.company?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: object, field: 'catalogNumber', 'error')} ">
	<label for="catalogNumber">Catalog Number</label>
	<g:textField name="catalogNumber" value="${object?.catalogNumber}"/>
</div>

<div class="${hasErrors(bean: object, field: 'lotNumber', 'error')} ">
	<label for="lotNumber">Lot Number</label>
	<g:textField name="lotNumber" value="${object?.lotNumber}"/>
</div>

<div class="${hasErrors(bean: object, field: 'externalId', 'error')} ">
	<label for="externalId">External ID</label>
	<g:textField name="externalId" value="${object?.externalId}"/>
</div>

<div class="${hasErrors(bean: object, field: 'inventoryId', 'error')} ">
	<label for="inventoryId">Inventory ID</label>
	<g:textField name="inventoryId" value="${object?.inventoryId}"/>

</div>