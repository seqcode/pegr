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
        <h3>Protocol: <g:link controller="protocol" action="show" id="${protocolInstance?.protocol?.id}">${protocolInstance?.protocol?.name} ${protocolInstance?.protocol?.protocolVersion}</g:link></h3>
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
        <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.sharedItemList,'instanceId':protocolInstance.id, 'extra':true,'edit':true]"></g:render>
        <h4>End Product</h4>
        <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.endProductList,'instanceId':protocolInstance.id, 'extra':true,'edit':true]"></g:render>
        <h4>Images</h4>
        <ul>
        <g:each in="${protocolInstance.protocol.imageTypeList}" var="imgType">
            <h5>${imgType}</h5>
            <g:if test="${protocolInstance.images}">
            <div class="row">
            <g:each in="${protocolInstance.imageMap[imgType]}" var="filepath">
                <div class="col-sm-3">
                    <span class="glyphicon glyphicon-remove btn remove-image"></span>
                    <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: filepath, relative: true])}' height="300"/>
                    <g:hiddenField class="filepath" name="filepath" value="${filepath}"></g:hiddenField>
                </div>
            </g:each>
            </div>
            </g:if>            
            <g:uploadForm controller="protocolInstanceBag" action="uploadImage">
                <g:hiddenField name="type" value="gel"></g:hiddenField>
                <g:hiddenField name="instanceId" value="${protocolInstance.id}"></g:hiddenField>
                <input type="file" name="image"/>
                <g:submitButton name="upload" value="Upload"/>
            </g:uploadForm>
        </g:each>
        </ul>
        <g:if test="${sharedItemAndPoolList.startPool}">
            <h4>Import Pools</h4>
            <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.startPool,'instanceId':protocolInstance.id, 'extra':false,'edit':true]"></g:render>
        </g:if>
        <g:if test="${sharedItemAndPoolList.endPool}">
            <h4>Created New Pool</h4>
            <g:render template="sharedItemsTable" model="['itemList':sharedItemAndPoolList.endPool,'instanceId':protocolInstance.id, 'extra':false,'edit':true]"></g:render>
        </g:if>          
        <g:render template="editSampleItemsTable" model="['parents':parents,'children':children, 'instanceId':protocolInstance.id]"></g:render>
        <div class="row well text-center">
            <g:if test="${toBeCompleted}">
                <g:link action="completeInstance" params="[instanceId: protocolInstance?.id, bagId: protocolInstance?.bag?.id]" class="btn btn-success">Complete <span class="glyphicon glyphicon-ok"></span> </g:link>
            </g:if>
            <g:elseif test="${protocolInstance.status == pegr.ProtocolStatus.COMPLETED}">
                <button class="btn btn-default disabled">Completed</button>
            </g:elseif>
            <g:else>
                <button class="btn btn-default disabled">Processing</button>
            </g:else>
        </div>
    </div>
    <script>
        $("#nav-experiments").addClass("active"); 
        $(".confirm").confirm();
        $(".remove-image").on("click", function(){
            var parent = $(this).parent();
            var filepath = parent.find("input.filepath").val();
            var instanceId = ${protocolInstance.id};
            $.ajax({
                url:"/pegr/protocolInstanceBag/removeInstanceImageAjax",
                type: "POST",
                data: {instanceId: instanceId, filepath: filepath},
                success: function(){
                    parent.remove();
                }});
        });
    </script>
</body>
</html>