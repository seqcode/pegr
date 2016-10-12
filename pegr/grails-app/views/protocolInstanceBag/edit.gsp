<g:form class="fields" role="form" method="post" action="update">
    <div>
        <g:hiddenField name="bagId" value="${bag?.id}"></g:hiddenField>
        <label>Name</label>
        <g:textField name="name" value="${bag?.name}"></g:textField>
    </div>
    <div>
        <label>Projects</label>
        <g:select name="projects" id="projects" optionKey="id" from="${pegr.Project.list()}" multiple="multiple" value="${bag?.projects}"></g:select>
    </div>
    <g:submitButton name="submit" value="Save" class="btn btn-primary"/>
    <g:link action="showBag" id="${bag?.id}" class="btn btn-default">Cancel</g:link>
</g:form>

<script>
    $("#nav-experiments").addClass("active");
    $("#projects").select2();
</script>