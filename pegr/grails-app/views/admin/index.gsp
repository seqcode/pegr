<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin_main"/>
		<title>Admin</title>
	</head>
	<body>
        <g:each in="${controllers}">
            <li><g:link controller="${it.key}">${it.value}</g:link></li>
        </g:each>	
	</body>
</html>