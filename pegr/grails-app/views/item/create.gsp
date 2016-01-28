<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>Add Item </h4>
    <p>Item not found! Do you want to add it as a new item?</p>
    <button class="btn btn-primary" onClick="$('form').show()">Yes</button>
    <g:link class="btn btn-default" action="index">No</g:link>

    <form action="save" class="fields" role="form" method="post">
        <g:render template="/${itemController}/form" bean="${item}" var="item"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
    </form>

    <script>
        $("#nav-bench").addClass("active");
        $("form").hide();
     </script>
</div>
</body>
</html>