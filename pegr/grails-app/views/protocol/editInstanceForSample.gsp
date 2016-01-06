<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="/sample/nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Protocol ${protocolInstance?.protocol?.name} ${protocolInstance?.protocol?.version}</h3>
            <p>${protocolInstance?.protocol?.description }</p>
            <p>${protocolInstance?.protocol?.details }</p>
            <ul>
                <g:each in="${protocolInstance?.items}">
                
                </g:each>
            </ul>
            <h3>Add Item</h3>
            <form class="fields" role="form">
                <g:render template="/item/form" bean="${itemInstance}"/>
                <g:submitButton class="btn btn-primary" name="Save"/>
            </form>

        </div>
    </div>
    <script>
        $("#nav-sample-protocols").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>