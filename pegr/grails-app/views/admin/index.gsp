<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin"/>
</head>
<body>
    <div class="row text-right"><g:link action="mergeForm" class="btn btn-default" target="_blank">Merge</g:link></div>
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