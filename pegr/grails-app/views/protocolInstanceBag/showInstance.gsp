<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Work bench</title>
</head>
<body>
    <div class="container-fluid">
        <g:link action="showBag" id="${protocolInstance?.bag?.id}"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>Protocol: ${protocolInstance?.protocol?.name} ${protocolInstance?.protocol?.protocolVersion} <span data-toggle="collapse" data-target="#protocol-details" class="glyphicon glyphicon-collapse-down"></span></h3>
        <div id="protocol-details" class="collapse in">
            <g:if test="${protocolInstance?.protocol?.description}">
                <h4>Description</h4>
                ${protocolInstance?.protocol?.description}
            </g:if>

            <g:if test="${protocolInstance?.protocol?.details}">
                <h4>Details</h4>
                ${raw(protocolInstance?.protocol?.details)}
            </g:if>
        </div>
        <h3>Items <g:link action="searchItemForInstance" id="${protocolInstance.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-plus"></span> Add</g:link> </h3>                
        <div id="itemList" class="row">
            <g:each in="${items}" var="item">
                <g:render template="itemInInstance" model="['item': item, 'instanceId': protocolInstance.id]" />
            </g:each>
        </div>
        <g:if test="${protocolInstance.status != pegr.ProtocolStatus.COMPLETED}">
        <div class="row well text-center">
        <g:link action="completeInstance" params="[instanceId: protocolInstance?.id, bagId: protocolInstance?.bag?.id]" class="btn btn-success">Complete <span class="glyphicon glyphicon-ok"></span> </g:link>
        </div>
        </g:if>
    </div>
    <script>
        $("#nav-bench").addClass("active"); 
    </script>
</body>
</html>