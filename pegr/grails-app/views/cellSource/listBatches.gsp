<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:render template="/cellSource/head"></g:render>
    <ul class="nav nav-tabs">
        <li><g:link action="list">Cell Stock</g:link></li>
        <li class="active"><a href="#">Batches</a></li>
    </ul>
    <div class="container-fluid">
        <ul>
            <g:each in="${batches}">
                <li>
                    <g:link action="showBatch" id="${it.id}">${it}</g:link>
                </li>
            </g:each>
        </ul>
    </div>
    <script>
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>