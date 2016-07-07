<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Affiliation</th> 
                <th><a href="#" onclick="window.open('${createLink(uri:'/help/projectRoleHelp.html')}', 'Help: Project Role', 'width=600,height=400' )">Role <span class="glyphicon glyphicon-question-sign"></span></a></th>
            </tr>
        </thead>
        <tbody>
            <g:each var="projectUser" in="${projectUsers}">
            <tr>
                <td>${projectUser.user.fullName} (${projectUser.user.username})</td>
                <td>${projectUser.user.affiliation}</td>
                <td>${projectUser.projectRole} 
                </td>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>
