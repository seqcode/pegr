<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
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
            <g:each var="projectUser" in="${projectUsers}">
            <tr>
                <td>${projectUser.user.fullName} (${projectUser.user.username})</td>
                <td>${projectUser.user.affiliation}</td>
                <td>${projectUser.projectRole} 
                    <g:if test="${authorized}">
                        <button class="edit" data-toggle="modal" data-target="#editUserRole" onclick="showEditRole(${projectUser.user.id})">Edit</button>
                    </g:if>
                </td>
                <g:if test="${authorized}">
                <td><a href="#" onclick="removeUser(${projectUser.user.id}, ${projectUser.project.id})"><span class="glyphicon glyphicon-remove"></span></a></td>
                </g:if>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>
