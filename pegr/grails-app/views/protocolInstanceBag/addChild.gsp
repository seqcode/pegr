<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div>
    <h4>Add Child for <g:link controller="item" action="show" id="${sample?.item?.id}" target="_blank">${sample?.item?.name}</g:link></h4>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="row">
        <div class="col-sm-6">
            <g:form action="addChild" class="fields" role="form" method="POST" useToken="true">
                <g:hiddenField name="instanceId" value="${instanceId}"/>
                <g:hiddenField name="sampleId" value="${sample.id}"/>
                <div class=" ${hasErrors(bean: item, field: 'barcode', 'error')} ">
                    <label>Barcode</label>
                    <g:field name="barcode" value="${item?.barcode}" id="barcode"/>
                    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
                    <a href="#" onclick="generateBarcode()" class="btn btn-default">Generate</a>
                </div>            
                <div class=" ${hasErrors(bean: item, field: 'name', 'error')} ">
                    <label>Name</label>
                    <g:textField name="name" value="${item?.name}"/>
                </div>

                <div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
                    <label>Type</label>
                    <select id="type" name="type.id">
                        <option value="${childType?.id}" selected>${childType?.name}</option>
                    </select>
                </div>

                <div class=" ${hasErrors(bean: item, field: 'location', 'error')} ">
                    <label>Location</label>
                    <g:textField name="location" value="${item?.location}"/>
                </div>

                <div class=" ${hasErrors(bean: item, field: 'notes', 'error')} ">
                    <label>Notes</label>
                    <g:textArea name="notes" value="${item?.notes}"/>
                </div>

                <g:submitButton class="btn btn-primary" name="save" value="Save"/>
                <g:link action="showInstance" id="${instanceId}" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <div class="col-sm-6" id="barcode-display">    
            <g:render template="/item/barcodeImage" model="[barcode:'']"></g:render>
        </div>
    </div>

    <script>
        $("#nav-bench").addClass("active");
        $("#barcode-display").hide();
        function generateBarcode() {
            $.ajax({url: "/pegr/item/generateBarcodeAjax", success: function(result) {
                $("#barcode").val(result);
                $("img").attr("src", "/pegr/item/displayBarcode?barcode="+result+"&width=100&height=100&formatStr=QR");
                $("#barcode-display").show();
            }});
        }
     </script>
</div>
</body>
</html>