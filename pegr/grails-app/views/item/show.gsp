<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list">List</g:link></li>
        <li><g:link action="edit" id="${item.id}">Edit</g:link></li>
        <li><g:link action="delete" id="${item.id}">Delete</g:link></li>   
    </ul>

    <g:render template="/${itemController}/details" model="['item':item, 'object': object]"></g:render>   

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>