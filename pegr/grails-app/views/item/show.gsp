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
        <li><g:link action="delete" id="${item.id}">Delete</g:link></li>   
    </ul>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:render template="/${itemController}/details" model="['item':item, 'object': object]"></g:render>   

    <h4>Image</h4>
    <g:uploadForm action="upload" >
        <div class="form-group">
            <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
            <label for="image">Image: </label>
            <input type="file" id="image" name="image"/>
            <g:submitButton name="upload" value="Upload"/>
        </div>
    </g:uploadForm>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>