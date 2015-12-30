<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Create Item</title>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <h3>New Item</h3>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${itemInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${itemInstance}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
    </g:hasErrors>
    <g:form action='save' >
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
    <g:uploadForm action="upload" >
        <div class="form-group">
            <label for="image">Image: </label>
            <input type="file" id="image" name="image"/>
            <g:submitButton name="upload" value="Upload"/>
        </div>
    </g:uploadForm>


</div>

</body>
</html>
