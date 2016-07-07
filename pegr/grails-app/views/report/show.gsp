<html>
<head>
    <title>My Reports</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div>
        <h3>${project?.name}</h3>
        <p>Description: ${project?.description}</p>
        <p>The number of Samples: </p>
        <div id="project-users">
            <g:render template="userTable"/>
        </div>
        <h3>Experiment Images</h3>
       
        <br><br> 
        <h3>Samples</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#sample">Description</a></li>
            <li><a data-toggle="tab" href="#epitope">Epitope Tags & FastQC</a></li>
        </ul>

        <div class="tab-content">
            <div id="sample" class="tab-pane fade in active">
                <g:render template="/report/sampleTable" model="['sampleList':samples, 'project':project]" />
            </div>
            <div id="epitope" class="tab-pane fade">
                <g:render template="/report/epitopeTable" model="['sampleList':samples]" />
            </div>
        </div>
        <h3>Mapping Statistics</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#alignment"> Number of Tags</a></li>
            <li><a data-toggle="tab" href="#alignment2"> Percentage of Tags</a></li>
            <li><a data-toggle="tab" href="#alignment3"> Additional Information</a></li>
        </ul>

        <div class="tab-content">
            <div id="alignment" class="tab-pane in active">
                <g:render template="/report/alignmentTable" model="['alignmentList':alignments]" />
            </div>
            <div id="alignment2" class="tab-pane fade">
                <g:render template="/report/alignmentTable2" model="['alignmentList':alignments]" />
            </div>
            <div id="alignment3" class="tab-pane fade">
                <g:render template="/report/alignmentTable3" model="['alignmentList':alignments]" />
            </div>
        </div>
        <h3>Downstream Analysis</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#peak">Peak Statistics</a></li>
        </ul>
        

        <div class="tab-content">
            <div id="peak" class="tab-pane fade in active">
                <g:render template="/report/peakTable" model="['sampleList':samples, 'project':project]" />
            </div>
        </div>
        <h4> - MEME Motifs</h4>
        <br><br>
        <h4> - Tag PileUp</h4>
        <br><br>
        <div class="pagination">
            <g:paginate id="${project.id}" total="${sampleCount ?: 0}" max="50"/>
        </div>   
    </div>
    </br>          
    
    <g:if test="${authorized}">
        <div id="selectAssay" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Select Assay</h4>
                    </div>
                    <div class="modal-body">
                        <g:form action="addNewSamples" class="fields" role="form" method="get">
                            <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
                            <div>
                                <label>Assay</label> 
                                <g:select name="assayId" optionKey="id" from="${pegr.Assay.list()}" noSelection="['null': '-- choose --']" /> 
                            </div>
                            <g:submitButton class="btn btn-primary" name="submit" value="OK"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </g:form>                    
                    </div>
                </div>
            </div>
        </div>
    
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
                                <g:select id="userId" name="userId" optionKey="id" from="${pegr.User.list()}" noSelection="['null': '']" style="width: 250px"/> 
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
                            <g:hiddenField name="userId" id="hiddenUserId"></g:hiddenField>
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
            $("#userId").select2();
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
            $(".modal-body #hiddenUserId").val(userId);
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
