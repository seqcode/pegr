<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>Add Traced Sample</h4>
    <p>Item not found! You may save it as a new item.</p>
    
    <p class="alert alert-danger">If the traced sample already has a parent, please add the child sample during sample preparation!</p>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="saveWithCellSource" class="fields" role="form" method="post" useToken="true">
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="index">Cancel</g:link>
        <g:render template="form" model="['item':item]"/>
        <h4>Cell Source Information</h4>
        <g:render template="/cellSource/form" model="['object':object]"></g:render>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>