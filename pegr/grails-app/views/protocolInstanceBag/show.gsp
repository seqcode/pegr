<html>
<head>
  <title>Protocol Instance Bag</title> 
  <meta name="layout" content="base"/>
</head>
<body>                
    <div class="container-fluid">
        <h3>${bag.name}</h3> 
        <div class="panel-group">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4 class="panel-title"><a data-toggle="collapse" href="#protocols">Protocols</a></h4>
                </div>
                <div id="protocols" class="panel-collapse collapse in">
                    <g:render template="protocolInstances" model="['protocolInstances':bag.protocolInstances, 'completedCount':count]"></g:render>
                </div>
            </div>
            
            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h4 class="panel-title"><a data-toggle="collapse" href="#strains">Strains</a></h4>
                </div>
                <div id="strains" class="panel-collapse collapse in">
                    <ul class="list-group">
                    <g:each in="${strains}">
                        
                    </g:each> 
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>