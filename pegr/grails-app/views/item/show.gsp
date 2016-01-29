<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list">List</g:link></li>
        <li><g:link action="edit" id="${item.id}">Edit</g:link></li>
        <li><g:link action="changeBarcode" id="${item?.id}">Change <span class="glyphicon glyphicon-qrcode"></span></g:link></li>
        <li><g:link action="delete" id="${item.id}" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:render template="/${itemController}/details" model="['item':item, 'object': object]"></g:render>   

    <h4>Images</h4>
    <div class="row">
    <g:each in="${images}" var="img">   
        <div class="col-md-4 item">
            <g:link action="deleteImage" params="[img: img.name, itemId: item.id]" class="confirm" style="position: absolute; top: 0; let: 0"><span class="glyphicon glyphicon-remove-circle"></span></g:link> 
            <img src='${createLink(action: "displayImage", params:[img: img.name, itemId: item.id])}' height="300"/>
        </div>
    </g:each>
    </div>
    </br>
    <g:uploadForm action="upload" >
        <div class="form-group">
            <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
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