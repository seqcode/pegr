<html>
<head>
    <title>Item</title> 
    <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:render template="/cellSource/head"></g:render>
    <ul class="nav nav-tabs">
        <li><g:link action="list">Cell Stock</g:link></li>
        <li class="active"><g:link action="listBatches">Batches</g:link></li>
    </ul>

    <div class="container-fluid">
        <g:link controller="cellSource" action="listBatches"><span class="glyphicon glyphicon-th-list"></span> List</g:link>
        <g:form controller="cellSource" action="saveItems">
        <h4>Batch ${batch} 
            <span id="print" class="btn btn-default pull-right">Print Barcodes</span>
            <g:if test="${batch.cellSources.every{it.item == null}}">
                <g:submitButton class="btn btn-default pull-right" name="save" value="Save"></g:submitButton>
                <span id="assign-barcodes" class="confirm btn btn-default pull-right">Assign Barcodes to All</span> 
            </g:if>
        </h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Species</th>
                    <th>Parent</th>
                    <th>Strain</th>                    
                    <th>Barcode</th>
                    <th>Type</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${batch.cellSources}" var="cellSource">
                    <tr>
                        <input type="hidden" name="cellSourceId" value="${cellSource.id}" class="cellSourceId">
                        <td>${cellSource.strain?.species}</td>
                        <td>${cellSource.strain?.parent}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td class="barcode item">
                            <g:if test="${cellSource.item?.barcode}">
                                <span class="value">${cellSource.item?.barcode}</span>
                            </g:if>
                            <g:else>
                                <input name="barcode">
                            </g:else>
                        </td>
                        <td class="type item">
                            <g:if test="${cellSource.item?.type}">
                                <span class="value">${cellSource.item?.type}</span>
                            </g:if>
                            <g:else>
                                <g:select name="type" from="${pegr.ItemType.list()}" optionKey="id" noSelection="${['':'']}"></g:select> <span class="badge broadcast-type">All</span>
                            </g:else>                            
                        </td>
                        <td class="location item">
                            <g:if test="${cellSource.item?.location}">
                                <span class="value">${cellSource.item?.location}</span>                                
                            </g:if>
                            <g:else>
                                <input name="location">
                            </g:else>
                        </td>
                    </tr>
                </g:each>
            </tbody>
          </table>
        </g:form>
    </div>
    <script>
        $(".item-${categoryId}").addClass("active");
        $("select").select2();
        $("#assign-barcodes").on("click", function() {
            $("#assign-barcodes").confirm();
            $.ajax({
                url: "/pegr/cellSource/batchGenerateBarcodeAjax/"+${batch.id},
                success: function(result){
                    $("#save").show();
                    $(".barcode input").each(function(index){
                        $(this).val(result[index]);
                    })
                }
            });
        });
        
        $(".broadcast-type").on("click", function(){
            var type = $(this).parent().find("select").val();
            $(".type").val(type);
        });
    </script>
</body>
</html>