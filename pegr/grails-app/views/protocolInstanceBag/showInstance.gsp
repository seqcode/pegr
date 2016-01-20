<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="base"/>
<title>Protocol Instance</title>
<script type="text/javascript" >
var changingHash=false;
</script>
</head>
<body onhashchange="getHash()">
<div class="container-fluid">
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
    <div id="itemList" class="row">
        <g:each in="${protocolInstance?.items}" var="itemInstance">
            <g:render template="item" model="['itemInstance': itemInstance, 'prtclInstId': protocolInstance.id]" />
        </g:each>
    </div>
     <div>
        <ul class="nav nav-tabs">
          <li class="active"><a data-toggle="tab" href="#new-item"><span class="glyphicon glyphicon-plus"></span> New</a></li>
          <li><a data-toggle="tab" href="#search-item"><span class="glyphicon glyphicon-search"></span> Search</a></li>
        </ul>

        <div class="tab-content">
          <div id="new-item" class="tab-pane fade in active">
            <h4>New Item</h4>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors>
                <div class="errors">
                    <g:renderErrors bean="${ItemInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form class="fields" role="form" >
                <g:hiddenField name="prtclInstId" value="${protocolInstance.id}"/>
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

          <div id="search-item" class="tab-pane fade">
            <h4>Search Item</h4>
            <form class="fields" role="form" id="search-form" method="post">
                <g:hiddenField name="prtclInstId" value="${protocolInstance.id}"/>
                <div class="form-group">
                    <label for="type">Type</label>
                    <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" />
                </div>
                <div class="form-group">
                    <label for="barcode">Barcode</label>
                     <g:textField id="barcode" name="barcode" />
                    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
                </div>
                <g:submitToRemote class="btn btn-primary" value="Search"
                    url="[action: 'searchAjax']"
                    update="itemPreview" onSuccess="clearSearchForm(data)"/>
                <button class="btn btn-default" onClick="clearItemPreview(); return false;">Cancel</button>
              </form>
            <div id="itemPreview"></div>                      
          </div>
        </div>
    </div>
    <div class="row well text-center">
    <g:link action="completePrtclInst" params="[sampleId: sampleId, prtclInstId: protocolInstance.id]" class="btn btn-success">Complete <span class="glyphicon glyphicon-ok"></span> </g:link>
    </div>
</div>
    <script>
        function clearForm(e) {
            $('form').trigger("reset");
        }
        
        function createItemPreview(data) {
            $.each(data, function(index, value) {
                $('itemPreview').append(value.barcode)
            });
        }
        $("#nav-sample-protocols").addClass("active");
        $("#nav-projects").addClass("active"); 
        
        function clearItemPreview() {
            $('#itemPreview').empty();
        }
        
       function clearModal() {
           $('body').removeClass('modal-open');
           $('.modal-backdrop').remove();
        }
    </script>
</body>
</html>