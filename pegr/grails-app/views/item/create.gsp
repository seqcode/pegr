<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>Add Item </h4>
    <p>Item not found! You may save it as a new item.</p>
    <form action="save" class="fields" role="form" method="post">
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="index">Cancel</g:link>
        <g:render template="/${itemController}/form" bean="${item}" var="item"></g:render>
    </form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>