<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Work bench</title>
</head>
<body>
    <div class="container-fluid">
        <g:link action="showBag" id="${protocolInstance?.bag?.id}"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>Protocol: ${protocolInstance?.protocol?.name} ${protocolInstance?.protocol?.protocolVersion} <span data-toggle="collapse" data-target="#protocol-details" class="glyphicon glyphicon-collapse-down"></span></h3>
        <div id="protocol-details" class="collapse in">
            <g:if test="${protocolInstance?.protocol?.description}">
                <h4>Description</h4>
                ${protocolInstance?.protocol?.description}
            </g:if>

            <g:if test="${protocolInstance?.protocol?.details}">
                <h4>Details</h4>
                ${raw(protocolInstance?.protocol?.details)}
            </g:if>
        </div>
        <h3>Items</h3>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Barcode</th>
                        <th>Notes</th>
                        <g:if test="${!completed}">
                            <th></th>
                        </g:if>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${itemList}">
                    <tr>
                        <td class="col-sm-2" rowspan="${Math.max(1, it.items.size())}">${it.type} <g:if test="${!completed}"><g:link action="searchItemForTypeInstance" params="[instanceId:protocolInstance.id, typeId:it?.type?.id]"><span class="glyphicon glyphicon-plus"></span></g:link></g:if> </td>
                        <g:each in="${it.items}" var="item"  status="counter">
                            <g:if test="${counter>0}"><tr></g:if>
                            <td class="col-sm-2"><g:link controller="item" action="show" id="${item.id}" target="_blank">${item.name} </g:link></td>
                            <td class="col-sm-2">${item.location}</td>
                            <td class="col-sm-2">${item.barcode}</td>
                            <td class="col-sm-2">${item.notes}</td>
                            <g:if test="${!completed}">
                                <td>
                                <g:link class="confirm" action="removeItemFromInstance" params="[itemId: item.id, instanceId: protocolInstance.id]"><span class="glyphicon glyphicon-remove"></span></g:link></td>
                            </g:if>
                            </tr>
                        </g:each>
                </g:each>
                <tr>
                    <td colspan="5"><g:if test="${!completed}"><g:link action="searchItemForInstance" params="[id:protocolInstance.id]">Add <span class="glyphicon glyphicon-plus"></span></g:link></g:if></td>
                    <g:if test="${!completed}">
                        <td></td>
                    </g:if>
                </tr>
                <tr>
                    <td colspan="5"></td>
                </tr>
                </tbody>
              </table>
        </div>
        <g:if test="${toBeCompleted}">
        <div class="row well text-center">
        <g:link action="completeInstance" params="[instanceId: protocolInstance?.id, bagId: protocolInstance?.bag?.id]" class="btn btn-success">Complete <span class="glyphicon glyphicon-ok"></span> </g:link>
        </div>
        </g:if>
    </div>
    <script>
        $("#nav-bench").addClass("active"); 
        $(".confirm").confirm();
    </script>
</body>
</html>