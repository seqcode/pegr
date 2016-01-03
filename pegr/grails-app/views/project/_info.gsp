<%@ page import="pegr.Project" %>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')}">
    <label for="name">Name:</label>
    <g:textField name="name" id="name" value="${it?.name}" class="form-control"></g:textField>
</div>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')}">
    <label for="description">Description:</label>
    <g:textArea name="description" id="description"  value="${it?.description}" class="form-control" rows="3"></g:textArea>
</div>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')}">
    <label for="name">Funding:</label>
    <g:textField name="funding" id="funding" value="${it?.funding}" class="form-control"></g:textField>
</div>