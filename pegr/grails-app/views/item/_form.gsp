<%@ page import="pegr.Item" %>

<div class=" ${hasErrors(bean: item, field: 'name', 'error')} ">
    <label>Name</label>
    <g:textField name="name" value="${item?.name}"/>
</div>

<div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
    <label>Type</label>
    <select id="type" name="type.id">
        <option value="${item?.type?.id}" selected>${item?.type?.name}</option>
    </select>
</div>

<div class=" ${hasErrors(bean: item, field: 'barcode', 'error')} ">
    <label>Barcode</label>
    <g:field name="barcode" value="${item?.barcode}" readonly="readonly" />
</div>

<div class=" ${hasErrors(bean: item, field: 'location', 'error')} ">
    <label>Location</label>
    <g:textField name="location" value="${item?.location}"/>
</div>

<div class=" ${hasErrors(bean: item, field: 'notes', 'error')} ">
    <label>Notes</label>
    <g:textArea name="notes" value="${item?.notes}"/>
</div>

<div >
    <label>Parent Type</label>
    <g:select name="parentTypeId" from="${pegr.ItemType.list()}" 
              optionKey="id" oprtionValue="name" noSelection="${['null':'--Choose--']}"/>
</div>

<div>
    <label>Parent Barcode</label>
    <g:textField id="barcode" name="parentBarcode"/>
    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
</div>

