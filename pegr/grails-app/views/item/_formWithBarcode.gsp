<div class="row">
    <div class="col-sm-6 fields">
        <div class=" ${hasErrors(bean: item, field: 'active', 'error')} ">
            <label>Active</label>
            <g:textField name="active" value="${item?.active}"/>
        </div>
        <div class=" ${hasErrors(bean: item, field: 'name', 'error')} ">
            <label>Name</label>
            <g:textField name="name" value="${item?.name}"/>
        </div>

        <div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
            <label>Type</label>
            <select id="type" name="type.id">
                <option value="${item?.type?.id}" selected>${item?.type?.name}</option>
                <g:each in="${itemTypeOptions}">
                    <option value="${it.id}">${it.name}</option>
                </g:each>
            </select>
        </div>

        <div class=" ${hasErrors(bean: item, field: 'barcode', 'error')} ">
            <label>Barcode</label>
            <g:textField id="barcode" name="barcode" value="${item?.barcode}"/>
        <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
            <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
            <a href="#" onclick="generateBarcode()" class="btn btn-default">Generate</a>
        </div>

        <div class=" ${hasErrors(bean: item, field: 'location', 'error')} ">
            <label>Location</label>
            <g:textField name="location" value="${item?.location}"/>
        </div>

        <div class=" ${hasErrors(bean: item, field: 'notes', 'error')} ">
            <label>Notes</label>
            <g:textArea name="notes" value="${item?.notes}"/>
        </div>
        
        <g:each in="${item?.type?.fieldList}" var="name">
            <div>
                <label>${name}</label>
                <g:if test="${item?.fieldMap?.containsKey(name)}">
                    <input name="${name}" value="${item?.fieldMap[name]}">
                </g:if>
                <g:else>
                    <input name="${name}">
                </g:else>
            </div>
        </g:each>
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