<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>Add Antibody</h4>
    <p>Item not found! You may save it as a new item.</p>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
            <g:renderErrors bean="${antibody}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="antibody" action="save" class="fields" role="form" method="post" useToken="true">
        <g:render template="/item/form" model="['item':item]"/>
        <g:render template="/antibody/form" model="['antibody':antibody]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="list">Cancel</g:link>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>