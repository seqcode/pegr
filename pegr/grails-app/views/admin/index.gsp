<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin_main"/>
</head>
<body>
	<div class="panel-group">
	<g:each in="${controllerGroups}">
		<div class="panel panel-info">
		    <div class="panel-heading">${it.key}</div>
		    <div class="panel-body">
	        <g:each in="${it.value}">
	           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
	        </g:each>
	        </div>
        </div>	
    </g:each>
    </div>
</body>
</html>