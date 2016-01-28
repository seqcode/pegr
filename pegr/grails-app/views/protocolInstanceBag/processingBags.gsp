<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Processing</a></li>
        <li><g:link action="completedBags">Completed</g:link></li>   
        <li><g:link action='create'>New</g:link></li>
        <li><g:link controller="item" action="index">Items</g:link></li>
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