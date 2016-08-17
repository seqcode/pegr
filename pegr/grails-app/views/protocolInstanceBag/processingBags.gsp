<html>
<head>
  <title>PEGR - Work Records</title> 
</head>
<body>
    <h4>Work Records</h4>
    <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Processing</a></li>
        <li><g:link action="completedBags">Completed</g:link></li>   
        <li><g:link action='create'>New</g:link></li>
    </ul>
    <div id="message" >
        <g:if test="${flash.message}">
             <div class="message" role="status">
                ${flash.message}
            </div>
        </g:if>
    </div>
    <ul class="list-group">
        <g:each in="${bags}">
        <li class="list-group-item"><g:render template="/protocolInstanceBag/overview" bean="${it}"></g:render></li>
        </g:each>
    </ul>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" total="${bags.totalCount ?: 0}" />
    </div>
</body>
</html>