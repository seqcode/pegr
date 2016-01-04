<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Protocols</h3>
            <form role="form">
			Select protocol group <g:select name="protocolGroup.id" optionKey="id"
			from="${pegr.ProtocolGroup.list()}"  noSelection="['null': '-- choose --']"/>
			<input type="hidden" name="sampleId" value="${sampleId}">
			<g:submitToRemote value="Save"
				url="[action:'addProtocolGroupAjax']"
				update="protocols"
				/>
			</form>
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
