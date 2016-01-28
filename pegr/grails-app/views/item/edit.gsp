<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">

    <g:form action="update" class="fields" role="form" method="post">
        <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
        <g:render template="/${itemController}/form" model="['item': item, 'object': object]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${item.id}">Cancel</g:link>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>