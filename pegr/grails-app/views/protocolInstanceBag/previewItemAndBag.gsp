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
    <h4>Add Traced Sample(s)</h4>
    <g:form action="addItemsToBag">
    <g:hiddenField name="bagId" value="${bagId}"></g:hiddenField>
    <ul>
        <g:each in="${items}" var="item">
            <li><input type="checkbox" name="itemIds" value="${item.id}" checked> <span class="label">${item.status}</span>${item.type.name} ${item.name}</li>
        </g:each>
    </ul>
    <g:if test="${priorInstance}">
        <p>This item is associated with protocol instance <g:link controller="ProtocolInstanceBag" action="showInstance" id="${priorInstance.id}" target="_blank">${priorInstance.protocol.name} ${priorInstance.endTime}</g:link></p>
    </g:if>
    
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#import-sample">Import Sample(s)</a></li>
        <g:if test="${priorInstance}"><li><a data-toggle="tab" href="#import-bag">Import All Samples from Work Record</a></li>
        </g:if>
        <li><a data-toggle="tab" href="#split">Split and Add Sample(s)</a></li>
    </ul>

    <div class="tab-content">
        <div id="import-sample" class="tab-pane fade in active">
            <p>You will continue working on the above sample.</p>
            <g:submitButton class="btn btn-primary" name="add" value="Submit">Import Sample</g:submitButton>
        </div>
        <g:if test="${priorInstance}">
        <div id="import-bag" class="tab-pane fade">
            <p>You will continue working on all the samples in the above experiment record.</p>
            <g:link action="addSubBagToBag" params="[instanceId: priorInstance.id, bagId: bagId]" class="btn btn-primary">Import Entire Bag</g:link>
        </div>
        </g:if>
        <div id="split" class="tab-pane fade">
            <p>You will take a portion of the above sample and work only on that portion.</p>
            <g:submitButton class="btn btn-primary" name="split" value="Submit"></g:submitButton>
        </div>
    </div>
    </g:form>
    <script>
        $(function() {
            $("#split-form").hide();
            $("#nav-experiments").addClass("active");
        });        
        
        $(".label").each(function(){
            if ($(this).text() == "GOOD") {
                $(this).addClass("label-success");
            } else {
                $(this).addClass("label-danger");
            }
        });
     </script>
</div>
</body>
</html>