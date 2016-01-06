
<g:hasErrors bean="${project}">
<ul class="errors" role="alert">
    <g:eachError bean="${project}" var="error">
    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
    </g:eachError>
</ul>
</g:hasErrors>
<g:form method="post" role="form" useToken="true" id="addProjectForm">
    <div class="form-group">
        <label for="name">Name:</label>
        <g:textField name="name" id="name" class="form-control"></g:textField>
    </div>
    <div class="form-group">
        <label for="description">Description:</label>
        <g:textArea name="description" id="description" class="form-control" rows="3"></g:textArea>
    </div>
    <div class="form-group">
        <label for="name">Funding:</label>
        <g:textField name="funding" id="funding" class="form-control"></g:textField>
    </div>
    <g:submitToRemote class="btn btn-primary" value="Create" 
        url="[controller:'project', action:'addProjectAjax']"
        onSuccess="updateProject(data)"
        onLoading="showSpinner(true)"
        onComplete="showSpinner(false)"/>
    <asset:image src="spinner.gif" class="spinner" style="display:none"/>
</g:form>
