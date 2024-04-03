<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Affiliation</th> 
                <th><a href="#" onclick="window.open('/pegr/help#project-role', 'Help: Project Role', 'width=600,height=400' )">Role <span class="glyphicon glyphicon-question-sign"></span></a></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each var="projectUser" in="${projectUsers}">
            <tr>
                <td>${projectUser.user.fullName} (${projectUser.user.username})</td>
                <td>${projectUser.user.affiliation}</td>
                <td>${projectUser.projectRole} 
                    <g:if test="${projectEditAuth}">
                        <button class="edit" data-toggle="modal" data-target="#editUserRole" onclick="showEditRole(${projectUser.user.id})">Edit</button>
                    </g:if>
                </td>
                <g:if test="${projectEditAuth}">
                <td>
                    <g:form controller="project" action="removeUser" class="remove-user-form" method="post">
                        <input type="hidden" name="projectId" value="${projectUser.project.id}">
                        <input type="hidden" name="userId" value="${projectUser.user.id}">
                        <button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-remove"></span></button>
                    </g:form>                    
                </td>                
                </g:if>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>
<script type="application/javascript">
  $(".remove-user-form").submit(function(e){
      if(!confirm("Are you sure?")) {
          e.preventDefault();
      }
  });
</script>
