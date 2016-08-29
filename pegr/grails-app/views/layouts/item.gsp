<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Protocols <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body onhashchange="${pageProperty(name:'body.onhashchange')}">
    <div class="container-fluid">    
        <div class="row">
            <g:each in="${pegr.ItemTypeCategory.list(sort:'id')}" var="category">
                <g:link controller="item" action="list" params="[categoryId: category.id]" class="btn btn-info item-${category.id}">${category.name}</g:link> 
            </g:each>
            <g:link controller="sequenceIndex" class="btn btn-info item-index">Index</g:link>
            <a href="https://www.quartzy.com/e/groups/108111/order-requests" class="btn btn-info" target="_blank">Order</a>
        </div>
        <g:layoutBody/>
    </div>

     <script>
         $("#nav-inventory").addClass("active");
     </script>
</body>
</html>
</g:applyLayout>