<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <g:link action="searchItemForBag" params="[bagId:bagId]"><span class="glyphicon glyphicon-chevron-left"></span> Back</g:link>
    <a href="#" onclick="window.open('/pegr/help#addSampleToBag', 'Help: Work Bench', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
    <h4>Add Traced Sample</h4>
    <g:render template="/item/details" bean="${item}" var="item"></g:render>
    <g:if test="${priorInstance}">
        <p>This item is associated with protocol instance <g:link controller="ProtocolInstanceBag" action="showInstance" id="${priorInstance.id}" target="_blank">${priorInstance.protocol.name} ${priorInstance.endTime}</g:link></p>
    </g:if>
    
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#split">Split and Add Sample</a></li>
        <li><a data-toggle="tab" href="#import-sample">Import Sample</a></li>
        <g:if test="${priorInstance}"><li><a data-toggle="tab" href="#import-bag">Import Entire Bag</a></li>
        </g:if>
    </ul>

    <div class="tab-content">
        <div id="split" class="tab-pane fade in active">
            <p>You will take a portion of the above sample and work only on that portion.</p>
            <g:form action="splitAndAddItemToBag">
                <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
                <g:hiddenField name="bagId" value="${bagId}"></g:hiddenField>
                <g:render template="childForm" model="[item: null, childType: item.type]"></g:render>
                <g:submitButton class="btn btn-primary" name="submit" value="Submit"></g:submitButton>
            </g:form>
        </div>
        <div id="import-sample" class="tab-pane fade">
            <p>You will continue working on the above sample.</p>
            <g:link action="addItemToBag" params="[itemId: item.id,
                           bagId: bagId]" class="btn btn-primary">Import Sample</g:link>
        </div>
        <g:if test="${priorInstance}">
        <div id="import-bag" class="tab-pane fade">
            <p>You will continue working on all the samples in the above protocol instance.</p>
            <g:link action="addSubBagToBag" params="[instanceId: priorInstance.id, bagId: bagId]" class="btn btn-primary">Import Entire Bag</g:link>
        </div>
        </g:if>
    </div>

    <script>
        $(function() {
            $("#split-form").hide();
            $("#nav-experiments").addClass("active");
        });        
     </script>
</div>
</body>
</html>