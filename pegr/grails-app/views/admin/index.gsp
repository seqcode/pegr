<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin_main"/>
</head>
<body>
       <g:each in="${controllers}">
           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
       </g:each>	
</body>
</html>