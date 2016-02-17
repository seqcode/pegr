<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
     <ul class="nav nav-tabs">
        <li><g:link action="list">List</g:link></li>
        <li><g:link action="delete" id="${item?.id}" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${item?.name}">
        <h4>Name: ${item.name} <g:link action="edit" id="${item?.id}" class="edit">Edit</g:link></h4>
    </g:if>
    <ul>
        <g:if test="${item?.type}">
        <li>Type: ${item.type}</li>
        </g:if>

        <g:if test="${item?.barcode}">
        <li>Barcode: ${item.barcode } <g:link action="changeBarcode" id="${item?.id}" class="edit">Edit</g:link></li>
        </g:if>	

        <g:if test="${item?.location}">
        <li>Location: ${item.location}</li>
        </g:if>	

        <g:if test="${item?.notes}">
        <li>Notes: ${item.notes}</li>
        </g:if>    
    </ul>
    <g:if test="${item?.type?.category == pegr.ItemTypeCategory.TRACED_SAMPLE}">
        <h4>Sample Traces</h4>
        <g:if test="${traces?.size()==0}">
            <p>None</p>
        </g:if>
        <g:each in="${traces}">
            <g:link action="show" id="${it.id}">${it.name}</g:link>
        </g:each>
        <h4>Cell Source Information <g:link controller="cellSource" action="edit" id="${cellSource?.id}" class="edit">Edit</g:link></h4>
        <g:render template="/cellSource/details" model="[object: cellSource]"></g:render>
    </g:if>
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