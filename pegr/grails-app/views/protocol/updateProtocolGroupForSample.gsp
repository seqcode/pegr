<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="/sample/nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Protocols</h3>

            <form role="form" method="post" action="updateProtocolGroupForSample">
			Select <g:select name="id" optionKey="id"
			from="${pegr.ProtocolGroup.list()}"  
            noSelection="['null': '-- choose --']" 
            onChange="${remoteFunction(action: 'showProtocolsAjax', update:'protocols', params: '\'id=\'+this.value')}"/>
			<input type="hidden" name="sampleId" value="${sampleId}">
			<g:submitButton name="Save"/>
			</form>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<div id="protocols">
			<g:if test="${protocolGroup}">
			<g:render template="protocolsDetails" bean="${protocolGroup}" />
			</g:if>
			</div>
        </div>
    </div>
    <script>
        $("#nav-sample-protocols").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>
