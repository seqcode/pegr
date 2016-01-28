<html>
<head>
  <title>Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>                
    <div class="container-fluid">
        <h5><g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link></h5>
        <g:render template="/protocolInstanceBag/overview" bean="${bag}"></g:render>       
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
                            <a data-toggle="collapse" href="#items">Traced Items</a>
                                <g:if test="${!completed}">
                                    <g:link action="searchItemForBag" id="${bag.id}" class="pull-right"><span class="glyphicon glyphicon-plus"></span> Add</g:link>
                                </g:if>
                        </h4>
                    </div>                    
                    <g:render template="/protocolInstanceBag/baggedItems" model="['items':bag.tracedItems,'subBags':subBags]"></g:render>
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
     </script>
</body>
</html>