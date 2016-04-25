<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
	<div><g:link action='index'>My Projects</g:link> -> Project ${project?.name} </div>
	<div>
		<h3>Project: ${project?.name} <g:if test="${authorized}"><g:link action="edit" params="[projectId:project?.id]" class="edit">Edit</g:link></g:if></h3>
		<p>Created: ${project?.dateCreated}, updated: ${project?.lastUpdated}</p>
		<p>Description: ${project?.description}</p>
        <p>Funding: ${project?.funding}</p>
        
        <h3>Users <g:if test="${authorized}"><button class="edit" data-toggle="modal" data-target="#addUser">Add</button></g:if></h3>
        <div id="project-users">
            <g:render template="userTable"/>
        </div>
        
		<h3>Samples</h3>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#sample">Sample Information</a></li>
            <li><a data-toggle="tab" href="#alignment">Alignment</a></li>
            <li><a data-toggle="tab" href="#peak">Peak Statistics</a></li>
        </ul>

        <div class="tab-content">
            <div id="sample" class="tab-pane fade in active">
                <g:render template="/project/sampleTable" model="['sampleList':samples, 'project':project]" />
            </div>
            <div id="alignment" class="tab-pane fade">
                <g:render template="/project/alignmentTable" model="['alignmentList':alignments]" />
            </div>
            <div id="peak" class="tab-pane fade">
                <g:render template="/project/peakTable" model="['alignmentList':alignments]" />
            </div>
        </div>
        <div class="pagination">
            <g:paginate id="${project.id}" total="${sampleCount ?: 0}" max="50"/>
        </div>   
        <g:if test="${authorized}">
            <div>
                <g:link action="addNewSamples" params="[projectId: project?.id]" class="btn btn-info">Create New Sample</g:link>
                <g:link action="searchSample" params="[projectId: project?.id]" class="btn btn-info">Add Existing Sample</g:link>
            </div>
        </g:if>
	</div>
    </br>
    <g:if test="${authorized}">
        <div id="addUser" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Add User to Project: ${project?.name}</h4>
                    </div>
                    <div class="modal-body">
                        <form class="fields" role="form" method="post">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <div>
                                <label for="userId">User</label> 
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <div>
                                <label for="projectRole">Project Role</label>
                                <g:select id="projectRole" name="projectRole" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitToRemote type="button" class="btn btn-primary" value="Save" data-dismiss="modal"
                                              url="[action: 'addUserAjax']"
                                              update="[success: 'project-users']"
                                              onComplete="closeModal()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <div id="editUserRole" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Update user role in project: ${project?.name}</h4>
                    </div>
                    <div class="modal-body">
                        <form class="fields" role="form" method="post">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <div>
                                <label for="userId">User</label> 
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <div>
                                <label for="projectRole">Project Role</label>
                                <g:select id="projectRole" name="projectRole" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitToRemote type="button" class="btn btn-primary" value="Save" data-dismiss="modal"
                                              url="[action: 'editUserRoleAjax']"
                                              update="[success: 'project-users']"
                                              onComplete="closeModal()"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
    </g:if>
    
	<script>
        $(function(){
            $(".confirm").confirm();
            $("#nav-projects").addClass("active");
            if (!${authorized}) {
                $(".edit").hide();
            }
        });

        function removeUser(userId, projectId) {
            if(confirm('Are you sure?')) {
                jQuery.ajax({
                    type:'POST',
                    data:{'userId': userId,'projectId': projectId}, 
                    url:'/pegr/project/removeUserAjax',
                    success:function(data,textStatus){
                                jQuery('#project-users').html(data);
                            }
                });
            }
        }
        
        function showEditRole(userId) {
            $(".modal-body #userId").val(userId);
        }

        function closeModal() {
            $(".modal").modal('hide');
        }
        
        $(function(){
          var hash = window.location.hash;
          hash && $('ul.nav a[href="' + hash + '"]').tab('show');

          $('.nav-tabs a').click(function (e) {
            $(this).tab('show');
            var scrollmem = $('body').scrollTop() || $('html').scrollTop();
            window.location.hash = this.hash;
            $('html,body').scrollTop(scrollmem);
          });
        });
	</script>
</body>
</html>