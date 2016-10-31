<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
    <style>
        .btn {
            padding: 0 5px;
            margin-left: 5px;
        }
    </style>
</head>
<body onhashchange="getHash()">
    <g:render template="/item/searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:link controller="item" action="generateBarcodeList" class="edit pull-right" target="_blank">Generate Barcode List</g:link>
    <g:link controller="cellSource" action="batchCreate" class="edit pull-right">Batch Create</g:link>
    <h4>Cell Stock</h4>
    <div>
        <g:form controller="cellSource" action="list">
            <g:select name="strain" from="${strains}"></g:select>
            <g:submitButton name="submit" value="Search" class="btn btn-primary"></g:submitButton>
        </g:form>
    </div>
    <div class="container-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Species</th>
                    <th>Strain</th>                    
                    <th>Barcode</th>
                    <th>Type</th>
                    <th>Location</th>     
                </tr>
            </thead>
            <tbody>
                <g:each in="${cellSources}" var="cellSource">
                    <tr>
                        <input type="hidden" name="cellSourceId" value="${cellSource.id}" class="cellSourceId">
                        <td>${cellSource.strain?.species}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td class="barcode item"><span class="value">${cellSource.item?.barcode}</span></td>
                        <td class="type item"><span class="value">${cellSource.item?.type}</span></td>
                        <td class="location item"><span class="value">${cellSource.item?.location}</span></td>
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" action="list" total="${totalCount ?: 0}" />
    </div>
    <script>
        $("select").select2();
        $(".item-${categoryId}").addClass("active");
        var s0 = "NONE";
        var types;
        $.ajax({ url: "/pegr/cellSource/fetchItemTypesAjax", success: 
            function(result) {
                types = result;
            }            
        });
        
        $(".value").each(function(){
            if ($(this).text() == "") {
                $(this).text(s0);
            }
        });
        
        $("td.item").on("click", ".value", function(){
            $(".error").remove();
            var tr = $(this).closest("tr");
            // barcode
            var $barcode = tr.find(".barcode");
            var barcodeValue = $barcode.text();
            if (barcodeValue == s0) {
                barcodeValue = "";
            }
            var editBarcode = "<span class='input'><input name='barcode' id='barcode' value='" + barcodeValue + "'><span onclick='getScan();' type='button'><span class='glyphicon glyphicon-qrcode'></span></span></span>";
            appendEdit($barcode.find(".value"), editBarcode);
            
            // type
            createSelect(tr, ".type", types);
            
            // location
            var $location = tr.find(".location");
            var locationValue = $location.text();
            if (locationValue == s0) locationValue = "";
            var editLocation = "<input class='input' name='location' value='" + locationValue + "'>";
            $location.find(".value").hide();
            $location.append(editLocation);
        });
        
        $("td").on("click", ".cancel", function() {
            $(".error").remove();
            var tr = $(this).closest("tr");            
            toggleTd(tr);
        });
        
        $("td").on("click", ".save", function(){
            var tr = $(this).closest("tr");
            var cellSourceId = tr.find(".cellSourceId").val();
            var type = tr.find(".type select").val();
            var typeText = tr.find(".type option:selected").text();
            var barcode = tr.find(".barcode input").val();
            var location = tr.find(".location input").val();
            $.ajax({
                type: "POST",
                url: "/pegr/cellSource/updateItemAjax",
                data: {
                    cellSourceId: cellSourceId,
                    type: type,
                    barcode: barcode,
                    location: location
                },
                success: function(result) {
                    if (result != "") {
                        var message = "<div class='error'>" + result.message +"<div>";
                        tr.find(".barcode").append(message);
                    } else {
                        if (barcode == null || barcode == "") {
                            barcode = s0;
                            type = s0;
                            location = s0;
                        }
                        if (type == null || type == "") type = s0;
                        if (location == null || location == "") location = s0;
                        tr.find(".type .value").text(typeText);
                        tr.find(".barcode .value").text(barcode);
                        tr.find(".location .value").text(location);

                        toggleTd(tr);
                    }
                }
            });
        });
    </script>
</body>
</html>