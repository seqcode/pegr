<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
        function getHashAndQuery() {
            if(!changingHash){
                changingHash=true;
                var hash=window.location.hash.substr(1);
                var barcode = unescape(hash)
                $('#barcode').val(barcode);                
                $.ajax({ url: "/pegr/item/queryAjax?barcode=" +barcode,
                        success: function(result){
                            $('#new-item').append("<span id='item-id' style='display:none'>" + result.id + "</span>");
                            $('#new-item').append("<span>" + result.status + result.barcode + result.type + result.name +"</span>")
                            $('#new-item').addClass("message");
                        }                    
                });                
                changingHash=false;
            }else{
                //Do something with barcode here
            }
        }
    </script>
    <style>
        select {
            border: none;
        }
    </style>
</head>
<body  onhashchange="getHashAndQuery()">
<div class="container-fluid">
    <g:link action="showBag" id="${bagId}"><span class="glyphicon glyphicon-menu-left"></span> Back</g:link>

    <h4>Add Traced Sample <span class="badge" id="item-count">0</span></h4>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form class="fields" role="form" action="previewItemAndBag" >
        <g:hiddenField name="bagId" value="${bagId}"/>
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
        </div>
        <div id="new-item"></div>
        <a href="#" class="btn btn-primary" id="add-sample">Add</a>
        <a href="#" onclick="refreshHash();" class="btn btn-default">Skip</a>
        <g:submitButton class="btn btn-default" name="view" value="View"/>
        <div>
            <select name="items" id="added-items" size="20" multiple></select>
        </div>
    </g:form>                    
    <script>
        $("#nav-experiments").addClass("active");
        
        $("#add-sample").on("click", function(){
            var barcode = $("#barcode").val();
            var id = $("#item-id").text();
            var content = $("#new-item").html();
            $("#added-items").append("<option value='" + id + "' selected>" + content + "</option>");
            $("#new-item").html();
            var count = $("#added-items option").length;
            $("#item-count").text(count);
            refreshHash();
        });
     </script>
</div>
</body>
</html>