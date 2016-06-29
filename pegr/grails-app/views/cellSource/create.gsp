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
            <g:renderErrors bean="${cellSource}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="cellSource" action="save" class="fields" role="form" method="post" useToken="true">
        <h4>Item Information</h4>
        <g:render template="form" model="['item':item]"/>
        <h4>Cell Source Information</h4>
        <g:render template="/cellSource/form" model="['cellSource':cellSource]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="list" params="[typeId:item.type.id]">Cancel</g:link>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
        // disable the dependent fields in cascade selections
        $(".species").prop("disabled", true);
        $(".parent-strain").prop("disabled", true);
        $(".strain").prop("disabled", true);
        $(".genotype").prop("disabled", true);
        $(".mutation").prop("disabled", true);
        $(".growth-media").prop("disabled", true);
        $(".genomes").prop("disabled", true);
     </script>
</div>
</body>
</html>