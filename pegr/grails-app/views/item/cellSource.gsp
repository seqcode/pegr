<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cell Source</title>
</head>
<body>
    <div class="row">
        <span class="btn btn-primary">Edit</span>
        <span class="btn btn-primary">Delete</span>
        <h3>Cell Source</h3>
        <g:render template="details" 
                    model="['cellSourceInstance': cellSourceInstance, 
                    'itemInstance': itemInstance]" />
    </div>
    <script>
        $("#nav-bench").addClass("active");     
    </script>
</body>
</html>
