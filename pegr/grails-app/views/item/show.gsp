<html>
<head>
    <title>Workbench</title> 
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list" params="[categoryId: item?.type?.category?.id]">List</g:link></li>
        <li><g:link action="delete" params="[itemId:item?.id]" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
        
    <h4>Barcode Information <g:link action="edit" params="[itemId:item?.id]" class="edit">Edit</g:link></h4>
    <div class="row">
        <div class="col-sm-6">
            <ul>
                <g:render template="/item/status" model="[item: item]"></g:render>
                <li>Active: ${item.active}</li>
                <g:if test="${item?.name}">
                <li>Name: ${item.name}</li>
                </g:if>
                <g:if test="${item?.type}">
                <li>Type: ${item.type}</li>
                </g:if>

                <g:if test="${item?.barcode}">
                <li>Barcode: ${item.barcode }</li>
                </g:if>	

                <g:if test="${item?.location}">
                <li>Location: ${item.location}</li>
                </g:if>	

                <g:if test="${item?.user}">
                <li>User: ${item.user}</li>
                </g:if>
                
                <li>Last updated: ${item.lastUpdated}</li>

                <g:if test="${item?.notes}">
                <li>Notes: ${item.notes}</li>
                </g:if>    
                <g:each in="${item?.fieldMap}">
                    <li>${it.key}: ${it.value}</li>
                </g:each>
            </ul>
        </div>
        <div class="col-sm-6">
            <g:render template="/item/barcodeImage" model="[item: item]"></g:render>
        </div>
    </div>
    <g:if test="${traces?.size()}">
        <h4>Sample Traces</h4>
        <ul>
        <g:each in="${traces}">
            <li><g:link action="show" id="${it.id}">${it.type}: ${it.name}</g:link></li>
        </g:each>
        </ul>
    </g:if>
    <g:if test="${cellSource}">
        <h4>Cell Source Information <g:link controller="cellSource" action="edit" params="[cellSourceId:cellSource?.id]" class="edit">Edit</g:link></h4>
        <g:render template="/cellSource/details" model="[cellSource: cellSource]"></g:render>
    </g:if>
    <h4>Images</h4>
    <div class="row">
    <g:each in="${images}" var="img">   
        <div class="col-md-4 item">
            <g:link action="deleteImage" params="[img: img.name, itemId: item.id]" class="confirm" style="position: absolute; top: 0; let: 0"><span class="glyphicon glyphicon-remove-circle"></span></g:link> 
            <img src='${createLink(controller: "file", action: "displayImage", params:[filepath: img.getAbsolutePath()])}' height="300"/>
        </div>
    </g:each>
    </div>
    </br>
    <g:uploadForm action="upload" >
        <div class="form-group">
            <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
            <input type="file" id="image" name="image"/>
            <g:submitButton name="upload" value="Upload"/> (only jpeg, png, gif files, size limit: 5 MB)
        </div>
    </g:uploadForm>
    <g:if test="${item?.type?.category.superCategory == pegr.ItemTypeSuperCategory.OTHER || item?.type?.category.superCategory == pegr.ItemTypeSuperCategory.SAMPLE_POOL}">
        <h4>Related Protocol Instances</h4>
        <ul>
            <g:each in="${item.relatedInstances}">
                <li><g:link controller="protocolInstanceBag" action="showInstance" id="${it.id}"><g:formatDate format="yyyy-MM-dd" date="${it.startTime}"/> ${it.protocol}</g:link></li>
            </g:each>
        </ul>
    </g:if>
    <script>
        $(".confirm").confirm();
     </script>
</div>
</body>
</html>