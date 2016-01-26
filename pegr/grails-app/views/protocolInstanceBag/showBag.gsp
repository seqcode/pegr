<html>
<head>
  <title>Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>                
    <div class="container-fluid">
        <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        <g:render template="/protocolInstanceBag/overview" bean="${bag}"></g:render>       
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
                            <g:link action="searchItemForBag" id="${bag.id}" class="pull-right"><span class="glyphicon glyphicon-plus"></span> Add</g:link>
                        </h4>
                    </div>                    
                    <g:render template="/protocolInstanceBag/baggedItems" model="['items':bag.tracedItems,'subBags':subBags]"></g:render>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>