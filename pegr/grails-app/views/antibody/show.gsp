<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="item"/>
</head>
<body>
    <div class="container-fluid">
         <ul class="nav nav-tabs">
            <li><g:link action="list">List</g:link></li>
            <li><g:link action="delete" params="[antibodyId:antibody.id]" class="confirm">Delete</g:link></li>   
        </ul>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:if test="${!antibody.item}"><h4>Barcode: none <g:link action="addBarcode" params="[antibodyId:antibody.id]" class="edit">Add</g:link></h4></g:if>
        <g:else>
            <h4>Barcode Information <g:link action="editItem" params="[antibodyId:antibody.id]" class="edit">Edit</g:link></h4>
            <div class="row">
                <div class="col-sm-6">
                    <ul>
                        <g:render template="/item/status" model="[item: antibody.item]"></g:render>
                        <g:if test="${antibody.item?.name}">
                        <li>Name: ${antibody.item.name}</li>
                        </g:if>
                        <g:if test="${antibody.item?.barcode}">
                        <li>Barcode: ${antibody.item.barcode }</li>
                        </g:if>	

                        <g:if test="${antibody.item?.location}">
                        <li>Location: ${antibody.item.location}</li>
                        </g:if>	

                        <g:if test="${antibody.item?.user}">
                        <li>User: ${antibody.item.user}</li>
                        </g:if>

                        <g:if test="${antibody.item?.notes}">
                        <li>Notes: ${antibody.item.notes}</li>
                        </g:if>    
                        <g:each in="${antibody.item?.fieldMap}">
                            <li>${it.key}: ${it.value}</li>
                        </g:each>
                    </ul>
                </div>
                <div class="col-sm-6">
                    <g:render template="/item/barcodeImage" model="[item:antibody?.item]"></g:render>
                </div>
            </div>
        </g:else>
        <h4>Antibody Information <g:link controller="antibody" action="edit" params="[antibodyId:antibody.id]" class="edit">Edit</g:link></h4>
        <g:render template="/antibody/details" model="[object: antibody]"></g:render>
        <g:if test="${antibody.item}">
        <h4>Images</h4>
        <div class="row">
        <g:each in="${images}" var="img">   
            <div class="col-md-4 item">
                <g:link controller="item" action="deleteImage" params="[img: img.name, itemId: antibody.item?.id]" class="confirm" style="position: absolute; top: 0; let: 0"><span class="glyphicon glyphicon-remove-circle"></span></g:link> 
                <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: img.getAbsolutePath()])}' height="300"/>
            </div>
        </g:each>
        </div>
        </br>
        <g:uploadForm controller="item" action="upload" >
            <div class="form-group">
                <g:hiddenField name="itemId" value="${antibody.item?.id}"></g:hiddenField>
                <input type="file" id="image" name="image"/>
                <g:submitButton name="upload" value="Upload"/> (only jpeg, png, gif files, size limit: 5 MB)
            </div>
        </g:uploadForm>
        </g:if>
    </div>
    <script>
        $(".confirm").confirm();
     </script>
</body>
</html>