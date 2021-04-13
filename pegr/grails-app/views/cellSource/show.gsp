<html>
<head>
    <title>Inventory</title> 
    <meta name="layout" content="item"/>
        <style>
        .btn {
            padding: 0 5px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list">List</g:link></li>
        <li><g:link action="delete" params="[cellSourceId:cellSource.id]" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${!cellSource.item}"><h4>Barcode: none <g:link action="editBarcode" params="[cellSourceId: cellSource.id]" class="edit">Add</g:link></h4></g:if>
    <g:else>
    <h4>Barcode Information <g:link action="editBarcode" params="[cellSourceId:cellSource.id]" class="edit">Edit</g:link></h4>
    <div class="row">
        <div class="col-sm-6">
            <ul>
                <li><g:render template="/item/active" model="[item: cellSource.item]"></g:render></li>
                <li><g:render template="/item/status" model="[item: cellSource.item]"></g:render></li>
                <g:if test="${cellSource.item?.name}">
                <li>Name: ${cellSource.item?.name}</li>
                </g:if>
                <g:if test="${cellSource.item?.type}">
                <li>Type: ${cellSource.item?.type}</li>
                </g:if>

                <g:if test="${cellSource.item?.barcode}">
                <li>Barcode: ${cellSource.item?.barcode }</li>
                </g:if>	

                <g:if test="${cellSource.item?.location}">
                <li>Location: ${cellSource.item?.location}</li>
                </g:if>	

                <g:if test="${cellSource.item?.user}">
                <li>User: ${cellSource.item?.user}</li>
                </g:if>

                <g:if test="${cellSource.item?.notes}">
                <li>Notes: ${cellSource.item?.notes}</li>
                </g:if>    
                <g:each in="${cellSource.item?.fieldMap}">
                    <li>${it.key}: ${it.value}</li>
                </g:each>
            </ul>
        </div>
        <div class="col-sm-6">
            <g:render template="/item/barcodeImage" model="[item:cellSource.item]"></g:render>
        </div>
    </div>
    </g:else>
    <h4>Cell Source Information <g:link controller="cellSource" action="edit" params="[cellSourceId:cellSource?.id]" class="edit">Edit</g:link></h4>
    <g:render template="/cellSource/details" model="[cellSource: cellSource]"></g:render>
    <g:if test="${cellSource.item}">
    <h4>Images</h4>
    <div class="row">
    <g:each in="${images}" var="img">   
        <div class="col-md-4 item">
            <g:link controller="item" action="deleteImage" params="[img: img.name, itemId: cellSource.item?.id]" class="confirm" style="position: absolute; top: 0; let: 0"><span class="glyphicon glyphicon-remove-circle"></span></g:link> 
            <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: img.getAbsolutePath()])}' height="300"/>
        </div>
    </g:each>
    </div>
    </br>
    <g:uploadForm controller="item" action="upload" >
        <div class="form-group">
            <g:hiddenField name="itemId" value="${cellSource.item?.id}"></g:hiddenField>
            <input type="file" id="image" name="image"/>
            <g:submitButton name="upload" value="Upload"/> (only jpeg, png, gif files, size limit: 5 MB)
        </div>
    </g:uploadForm>
    </g:if>
    <script>
        $(".confirm").confirm();
     </script>
</div>
</body>
</html>