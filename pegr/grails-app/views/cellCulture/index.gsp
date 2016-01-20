<html>
<head>
  <title>Cell Culture</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <g:link action='create'  class="btn btn-primary pull-right"><span class="glyphicon glyphicon-plus"></span> New</g:link>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Processing</a></li>
        <li><g:link action="completedBags">Completed</g:link></li>        
    </ul>
    <ul class="list-group" id="pendingCellCultures">
        <g:each in="${bags}">
        <li class="list-group-item"><g:render template="/protocolInstanceBag/overview" bean="${it}"></g:render></li>
        </g:each>
    </ul>

    
     <script>
        $("#nav-cell-culture").addClass("active");
     </script>
</body>
</html>