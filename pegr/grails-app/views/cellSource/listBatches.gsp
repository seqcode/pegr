<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:link controller="item" action="batchAddBarcode" class="confirm edit pull-right">Assign Barcodes</g:link>
    
    <ul class="nav nav-tabs">
        <li><g:link action="list">Cell Stock</g:link></li>
        <li class="active"><a href="#">Batches</a></li>
    </ul>
    <div class="container-fluid">
        <ul>
            <g:each in="${batches}">${it}</g:each>
            <li></li>
        </ul>
    </div>
    <script>
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>