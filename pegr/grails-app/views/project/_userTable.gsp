<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Affiliation</th> 
                <th><a href="#" onclick="window.open('${g.createLink(action: 'projectRoleHelp')}', 'Help: Project Role', 'width=600,height=400' )">Role <span class="glyphicon glyphicon-question-sign"></span></a></th>
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
