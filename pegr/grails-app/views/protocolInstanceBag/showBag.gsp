<html>
<head>
  <title>Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>                
    <div class="container-fluid">
        <a href="#" onclick="window.open('${g.createLink(action:'help')}', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
        <ul class="nav nav-tabs">
            <li><g:link action="index"><span class="glyphicon glyphicon-home"></span> List</g:link></li>
            <g:if test="${completed}">
                <sec:ifAllGranted roles="ROLE_ADMIN"><li><g:link action="reopenBag" params="[bagId: bag?.id]" class="confirm">Reopen</g:link></li></sec:ifAllGranted>
            </g:if>
            <g:else>
                <li><a href="#" data-toggle="modal" data-target="#editName">Edit</a></li>
                <div id="editName" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <g:form class="fields" role="form" method="post">
                                <div class="modal-body">
                                    <g:hiddenField name="bagId" value="${bag?.id}"></g:hiddenField>
                                    <label>Name</label>
                                    <g:textField name="name" value="${bag?.name}"></g:textField>
                                </div>
                                <div class="modal-footer">
                                    <g:submitToRemote type="button" value="Save" 
                                                      url="[action: 'updateBagAjax']" 
                                                      params="[bagId: bag?.id]" 
                                                      update="[success: 'bagName']"
                                                      onComplete="closeModal()"
                                                      class="btn btn-primary" data-dismiss="modal"/>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </g:form>
                        </div>
                    </div>
                </div>
                <li><g:link action="deleteBag" params="[bagId: bag?.id]" class="confirm-deleteBag">Delete</g:link></li>                
            </g:else>
        </ul>
        <h4>
            <span id="bagName">${bag?.name}</span>
            <small>
                <span class="label label-default">${bag?.status}</span> 
            </small> 
        </h4>
        <p>Start Time: <g:formatDate format="yyyy-MM-dd" date="${bag?.startTime}"/>
        <g:if test="${bag?.endTime}">, End Time: <g:formatDate format="yyyy-MM-dd" date="${bag?.endTime}"/></g:if></p>   
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a data-toggle="collapse" href="#protocols">Protocols</a></h4>
                    </div>
                    <div id="protocols" class="panel-collapse collapse in">
                        <g:render template="/protocolInstanceBag/protocolInstances" model="['protocolInstances':protocolInstances, 'completedCount':count]"></g:render>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#items">Traced Samples</a>
                                <g:if test="${!completed}">
                                    <g:link action="searchItemForBag" params="[bagId: bag?.id]" class="pull-right"><span class="glyphicon glyphicon-plus"></span> Add</g:link>
                                </g:if>
                        </h4>
                    </div>                    
                    <g:render template="/protocolInstanceBag/baggedItems" model="['items':bag.tracedSamples*.item, 'completed':completed]"></g:render>
                </div>
            </div>
        </div>
        <g:if test="${toBeCompleted}">
            <div class="row well text-center">
                <button class="btn btn-success" data-toggle="modal" data-target="#confirm-complete">Complete <span class="glyphicon glyphicon-ok"></span> </button>
            </div>
            <div id="confirm-complete" class="modal fade" role="dialog">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-body">
                    <p>No changes can be made after completion!</p>
                  </div>
                  <div class="modal-footer">
                      <g:link action="completeBag" params="[bagId: bag?.id]" class="btn btn-primary">Ok</g:link>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                  </div>
                </div>
              </div>
            </div>
        </g:if>
    </div>
    <script>
        $("#nav-bench").addClass("active");
        $(".confirm").confirm();
        $(".confirm-deleteBag").confirm({text: "All protocol instances in this bag will be deleted! Are you sure you want to delete this bag?"});
        
        function closeModal() {
            $(".modal").modal('hide');
        }
     </script>
</body>
</html>