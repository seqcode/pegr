<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <g:link action='create'  class="btn btn-primary pull-right"><span class="glyphicon glyphicon-plus"></span> New</g:link>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Processing</a></li>
        <li><g:link action="completedBags">Completed</g:link></li>        
    </ul>
    <ul class="list-group" id="pendingCellSources">
        <g:each in="${bags}">
        <li class="list-group-item"><g:render template="/protocolInstanceBag/overview" bean="${it}"></g:render></li>
        </g:each>
    </ul>

     <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>