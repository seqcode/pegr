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
        <li><g:link action="delete" id="${item.id}" class="confirm">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:render template="/${itemController}/details" model="['item':item, 'object': object]"></g:render>   

    <h4>Images</h4>
    <div>
    <g:each in="${images}" var="img">
        <img src='${createLink(action: "displayImage", params:[img: img.name, itemId: item.id])}' height="300"/>
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