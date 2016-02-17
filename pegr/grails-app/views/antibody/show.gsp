<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list">List</g:link></li>
        <li><g:link action="delete" id="${antibody.item?.id}" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${!antibody.item}"><h4>Barcode: none <g:link action="addBarcode" id="${antibody.id}" class="edit">Add</g:link></h4></g:if>
    <g:if test="${antibody.item?.name}">
        <h4>Name: ${antibody.item.name} <g:link action="edit" id="${antibody.item?.id}" class="edit">Edit</g:link></h4>
    </g:if>
    <ul>
        <g:if test="${antibody.item?.barcode}">
        <li>Barcode: ${antibody.item.barcode } <g:link action="changeBarcode" id="${antibody.item?.id}" class="edit">Edit</g:link></li>
        </g:if>	

        <g:if test="${antibody.item?.location}">
        <li>Location: ${antibody.item.location}</li>
        </g:if>	

        <g:if test="${antibody.item?.notes}">
        <li>Notes: ${antibody.item.notes}</li>
        </g:if>    
    </ul>
    <h4>Antibody Information <g:link controller="antibody" action="edit" id="${antibody?.id}" class="edit">Edit</g:link></h4>
    <g:render template="/antibody/details" model="[object: antibody]"></g:render>

    <h4>Images</h4>
    <div class="row">
    <g:each in="${images}" var="img">   
        <div class="col-md-4 item">
            <g:link action="deleteImage" params="[img: img.name, itemId: antibody?.item?.id]" class="confirm" style="position: absolute; top: 0; let: 0"><span class="glyphicon glyphicon-remove-circle"></span></g:link> 
            <img src='${createLink(action: "displayImage", params:[img: img.name, itemId: antibody?.item?.id])}' height="300"/>
        </div>
    </g:each>
    </div>
    </br>
    <g:uploadForm action="upload" >
        <div class="form-group">
            <g:hiddenField name="itemId" value="${antibody?.item?.id}"></g:hiddenField>
            <input type="file" id="image" name="image"/>
            <g:submitButton name="upload" value="Upload"/> (only jpeg, png, gif files)
        </div>
    </g:uploadForm>    

    <script>
        $("#nav-bench").addClass("active");
        $(".confirm").confirm();
     </script>
</div>
</body>
</html>