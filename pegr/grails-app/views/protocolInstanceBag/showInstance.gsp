<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Work bench</title>
</head>
<body>
    <div class="container-fluid">
        <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
        <g:link action="showBag" id="${protocolInstance?.bag?.id}"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>Protocol: <g:link controller="protocol" action="show" id="${protocolInstance?.protocol?.id}">${protocolInstance?.protocol?.name} ${protocolInstance?.protocol?.protocolVersion}</g:link> </h3>
        <div id="protocol-details" class="collapse in">
            <g:if test="${protocolInstance?.protocol?.description}">
                <h4>Description</h4>
                ${protocolInstance?.protocol?.description}
            </g:if>

            <g:if test="${file}">
            <h4>Protocol File</h4>
            <g:link action="renderFile" params="[protocolId: protocolInstance?.protocol?.id]" target="_blank">${file.getName()}</g:link>
            </g:if>
        </div>
        <h4>Shared Items</h4>
        <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.sharedItemList,'instanceId':protocolInstance.id, 'extra':true,'edit':false]"></g:render>
        <h4>End Product</h4>
        <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.endProductList,'instanceId':protocolInstance.id, 'extra':true,'edit':false]"></g:render>
        <h4>Images</h4>
        <ul>
        <g:each in="${protocolInstance.protocol.imageTypeList}" var="imgType">
            <h5>${imgType}</h5>
            <g:if test="${protocolInstance.images}">
            <div class="row">
            <g:each in="${protocolInstance.imageMap[imgType]}" var="filepath">
                <div class="col-sm-3">
                    <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: filepath, relative: true])}' height="300"/>
                </div>
            </g:each>
            </div>
            </g:if>
        </g:each>
        </ul>
        <g:if test="${sharedItemAndPoolList.startPool}">
            <h4>Imported Pools</h4>
            <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.startPool,'instanceId':protocolInstance.id, 'extra':false,'edit':false]"></g:render>
        </g:if>
        <g:if test="${sharedItemAndPoolList.endPool}">
            <h4>Created New Pool</h4>
            <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.endPool,'instanceId':protocolInstance.id, 'extra':false,'edit':false]"></g:render>
        </g:if>          
        <g:render template="showSampleItemsTable" model="['parents':parents,'children':children, 'instanceId':protocolInstance.id, 'edit':false]"></g:render>         
    </div>
    <script>
        $("#nav-experiments").addClass("active"); 
    </script>
</body>
</html>