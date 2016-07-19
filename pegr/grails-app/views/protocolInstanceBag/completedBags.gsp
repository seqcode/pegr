<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <h4>Protocol Instance Bags</h4>
        <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
    <ul class="nav nav-tabs">
        <li><g:link action="index">Processing</g:link></li>
        <li class="active"><a href="#">Completed</a></li>        
        <li><g:link action='create'>New</g:link></li>
    </ul>
    <ul class="list-group">
        <g:each in="${bags}">
        <li class="list-group-item"><g:render template="/protocolInstanceBag/overview" bean="${it}"></g:render></li>
        </g:each>
    </ul>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" total="${bags.totalCount ?: 0}" />
    </div>
    
     <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>