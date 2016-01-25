<html>
<head>
  <title>Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>                
    <div class="container-fluid">
        <g:render template="/protocolInstanceBag/overview" bean="${bag}"></g:render>       
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a data-toggle="collapse" href="#protocols">Protocols</a></h4>
                    </div>
                    <div id="protocols" class="panel-collapse collapse in">
                        <g:render template="/protocolInstanceBag/protocolInstances" model="['protocolInstances':bag.protocolInstances, 'completedCount':count]"></g:render>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#cell-cultures">Cell Cultures</a>
                            <g:link action="addCellCultureToBag" params="[prtclInstBagId: bag.id]" class="pull-right"><span class="glyphicon glyphicon-plus"></span> Add</g:link>
                        </h4>
                    </div>
                    <div id="cell-cultures" class="panel-collapse collapse in">
                        <ul class="list-group">
                        <g:each in="${cellCultures}">
                            <li class="list-group-item">
                                <h4 class="list-group-item-heading">${it.strain?.name}</h4>
                            </li>
                        </g:each> 
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-cell-culture").addClass("active");
     </script>
</body>
</html>