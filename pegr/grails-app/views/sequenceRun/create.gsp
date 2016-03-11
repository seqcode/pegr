<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h3>New Sequencing Run</h3>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form role="form" method="post" action="save" class="fields">
        <g:render template="basicForm" model="['run':run]"></g:render>
        <div class="row well text-center">
            <g:submitButton class="btn btn-primary" name="Save"/>
            <g:link action="index" class="btn btn-default">Cancel</g:link>
        </div>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>