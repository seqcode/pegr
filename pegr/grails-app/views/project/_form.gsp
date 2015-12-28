<%@ page import="pegr.Project" %>
<div class="form-group">
    <label for="name">Name:</label>
    <g:textField name="name" id="name" class="form-control" value="${project?.name}"></g:textField> 
</div>

<div class="form-group">
    <label for="description">Description:</label>
    <g:textArea name="description" id="description" class="form-control" value="${project?.description}"></g:textArea>
</div>

<div class="form-group">
    <label for="name">Funding:</label>
    <g:textField name="funding" id="funding" class="form-control" value="${project?.funding}"></g:textField>
</div>