<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin"/>
    <style>
        #merge {
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="container-fluid text-right" id="merge">
        <button onclick="window.open('/pegr/admin/mergeForm', 'PEGR Admin', 'width=600,height=400')" class="edit">Merge</button>
    </div>
	<div class="panel-group">
		<div class="jumbotron">
			<g:each in="${controllerGroups}">
				<div class="well">
				    <h3>${it.key}</h3>
				    <div class="panel-body">
			        <g:each in="${it.value}">
			           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
			        </g:each>
			        </div>
				</div>
		    </g:each>
            <div class="well">
                <h3>Service Report <g:link action="serviceReport"><span class="glyphicon glyphicon-file"></span></g:link></h3>
            </div>            
        </div>
    </div>
</body>
</html>
