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
        <a href="#" onclick="window.open('/pegr/admin/mergeForm', 'PEGR Admin', 'width=600,height=400')" class="edit"><u>Merge</u></a>  
    </div>
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