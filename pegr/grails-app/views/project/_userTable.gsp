<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Affiliation</th>
                <th>Role</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each var="projectUser" in="${it}">
            <tr>
                <td>${projectUser.user.fullName} (${projectUser.user.username})</td>
                <td>${projectUser.user.affiliation}</td>
                <td>${projectUser.projectRole} <g:link action="editUserRole" ><span class="glyphicon glyphicon-pencil"></span></g:link></td>
                <td><g:remoteLink action="removeUserAjax" update="project-users" params="[userId: projectUser.user.id, projectId: projectUser.project.id]"><span class="glyphicon glyphicon-remove"></span></g:remoteLink></td>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>