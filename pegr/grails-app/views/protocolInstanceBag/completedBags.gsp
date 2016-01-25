<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><g:link action="index">Processing</g:link></li>
        <li class="active"><a href="#">Completed</a></li>        
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