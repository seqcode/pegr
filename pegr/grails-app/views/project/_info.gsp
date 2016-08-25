<%@ page import="pegr.Project" %>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')} required">
    <label for="name">Name<span class="required-indicator">*</span></label>
    <g:textField name="name" required="" id="name" value="${it?.name}" class="form-control"></g:textField>
</div>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')}">
    <label for="description">Description</label>
    <g:textArea name="description" id="description"  value="${it?.description}" class="form-control" rows="3"></g:textArea>
</div>
<div class="form-group ${hasErrors(bean: project, field: 'name', 'error')}">
    <label for="name">Funding</label>
    <g:select from="${pegr.Funding.list()}" name="funding" value="${it?.fundings}" optionKey="id" noSelection="${['null':'']}" multiple="multiple" style="width: 250px"></g:select> 
    <script>
        $("select").select2();
    </script>
</div>