<html>
<head>
  <title>Cell Culture</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><g:link action="index">Processing</g:link></li>
        <li class="active"><a href="#">Completed</a></li>        
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