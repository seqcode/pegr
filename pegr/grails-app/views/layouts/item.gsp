<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Protocols <g:layoutTitle/></title>
  <g:layoutHead/>
    <style>
        .btn {
            padding: 0 5px;
        }
    </style>
</head>
<body onhashchange="${pageProperty(name:'body.onhashchange')}">
    <div class="container-fluid">
        <div class="row">
            <div class="pull-right">
            <g:form controller="item" action="search" style="padding:3px 0px">
                <input name="str" value="${str}">
                <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
            <br>Search by item name, barcode, location, type or username
            </g:form>
            </div>
        </div>
        <g:layoutBody/>
    </div>

     <script>
         $("#nav-inventory").addClass("active");
     </script>
</body>
</html>
</g:applyLayout>
