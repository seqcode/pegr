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
            <ul id="itemList">
                <g:each in="${protocolInstance?.items}" var="itemInstance">
                    <li>
                        <g:render template="/item/details" bean="${itemInstance}" var="itemInstance" />
                    </li>
                </g:each>
            </ul>
            <h3>Add Item</h3>
            <g:form class="fields" role="form" useToken="true" >
                <g:hiddenField name="protocolInstance.id" value="${protocolInstance.id}"/>
                <div class=" ${hasErrors(bean: itemInstance, field: 'type', 'error')} ">
                    <label for="type">Type</label>
                    <g:select id="type" name="type.id" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" />
                </div>
                <g:render template="/item/form" bean="${itemInstance}"/>
                <g:submitToRemote class="btn btn-primary" value="Save"
                    url="[action: 'addItemToPrtclInstanceAjax']"
                    update="itemList"
                    onSuccess="clearForm(data)"/>
            </g:form>

        </div>
    </div>
    <script>
        function clearForm(e) {
            $('form').trigger("reset");
        }
        $("#nav-sample-protocols").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>