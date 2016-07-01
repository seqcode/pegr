<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
    </head>
    <body>
        <div class="container-fluid">
            <ul class="nav nav-tabs">
                <li><g:link action="index">Active Users</g:link></li>
                <li class="active"><a href="#">Inactive Users</a></li>
            </ul>
            <g:each in="${}">
            </g:each>
        </div>
        <script>
            $("#nav-admin").addClass("active");
        </script>
    </body>
</html>