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
                if (barcode && barcode != "") {
                    $.ajax({ url: "/pegr/item/queryAjax?barcode=" +barcode,
                        success: function(result){
                            if (result) {
                                $("#add-sample").removeClass("disabled");
                                $('#new-item').append("<span id='item-id' style='display:none'>" + result.id + "</span>");
                                var status
                                if (result.status == "GOOD") {
                                    status = "<span class='label label-success'>GOOD</span>"
                                } else {
                                    status = "<span class='label label-danger'>BAD</span>"
                                }
                                $('#new-item').append("<li>" + status + " " + result.type + " " + result.name +"</li>")
                            } else {
                                $('#new-item').text("Item not found!");
                            }
                            $('#new-item').addClass("message");
                        }                    
                    }); 
                }
                changingHash=false;
            }else{
                //Do something with barcode here
            }
        }
    </script>
    <style>
        select {
            display: none;
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
        <a href="#" class="btn btn-primary disabled" id="add-sample">Add</a>
        <a href="#" onclick="refreshHash();$('#new-item').empty();" class="btn btn-default">Skip</a>
        <g:submitButton class="btn btn-default" name="view" value="Finish and View"/>
        <div>
            <select name="items" id="added-items" size="20" multiple></select>
        </div>
    </g:form>
    <ul id="item-list"></ul>
    <script>
        $("#nav-experiments").addClass("active");
        
        $("#add-sample").on("click", function(){
            if ($("#item-id")) {
                var id = $("#item-id").text();            
                $("#added-items").append("<option value='" + id + "' selected></option>");
                $("#new-item li").appendTo("#item-list");            
                var count = $("#added-items option").length;
                $("#item-count").text(count);
            }
            $("#new-item").empty();
            $("#barcode").val("");
            $("#add-sample").addClass("disabled");
        });
     </script>
</div>
</body>
</html>