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
        <g:link controller="cellSource" action="deleteBatch" id="${batch.id}" class="confirm"><span class="glyphicon glyphicon-trash"></span> Delete</g:link>
        <span class="pull-right" style="font-weight: normal"><g:form action="printBatchBarcode"><input name="id" value="${batch.id}" hidden="hidden"> Start row <input name="row" value="1" size="2"> column <input name="col" value="1" size="2"> <g:submitButton name="print" class="edit" value="Print Barcodes"></g:submitButton></g:form></span>
        <g:form controller="cellSource" action="saveItems">
        <g:hiddenField name="batchId" value="${batch.id}"></g:hiddenField>
        <h4>Batch: ${batch} 
            <g:if test="${batch.cellSources.every{it.item == null}}">
                <g:submitButton class="btn btn-primary pull-right" name="save" value="Save"></g:submitButton>
                <span id="assign-barcodes" class="confirm btn btn-default pull-right">Assign Barcodes to All</span>
            </g:if>
        </h4>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Species</th>
                    <th>Parent</th>
                    <th>Strain</th>   
                    <th>Mutation</th>
                    <th>Biological Source ID</th>
                    <th>Name</th>
                    <th>Barcode</th>
                    <th>Type</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${batch.cellSources}" var="cellSource" status="n">
                    <tr>
                        <input type="hidden" name="cellSourceId" value="${cellSource.id}" class="cellSourceId">
                        <td>${cellSource.strain?.species}</td>
                        <td>${cellSource.strain?.parent}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td>${cellSource.strain?.geneticModification}</td>
                        <td>${cellSource.biologicalSourceId}</td>
                        <td>
                            <g:if test="${cellSource.item}">
                                <span class="value">${cellSource.item.name}</span>
                            </g:if>
                            <g:else>
                                <input name="items[${n}].name" value="${cellSource.strain}">
                            </g:else>
                        </td>
                        <td class="barcode item">
                            <g:if test="${cellSource.item}">
                                <span class="value">${cellSource.item?.barcode}</span>
                            </g:if>
                            <g:else>
                                <input name="items[${n}].barcode">
                            </g:else>
                        </td>
                        <td class="type item">
                            <g:if test="${cellSource.item}">
                                <span class="value">${cellSource.item?.type}</span>
                            </g:if>
                            <g:else>
                                <g:select name="items[${n}].type" from="${pegr.ItemType.list()}" optionKey="id" noSelection="${['':'']}"></g:select> <span class="badge broadcast-type">All</span>
                            </g:else>                            
                        </td>
                        <td class="location item">
                            <g:if test="${cellSource.item}">
                                <span class="value">${cellSource.item?.location}</span>                                
                            </g:if>
                            <g:else>
                                <input name="items[${n}].location">
                            </g:else>
                        </td>
                    </tr>
                </g:each>
            </tbody>
          </table>
        </g:form>
    </div>
    <script>
        $(".confirm").confirm();
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
            $(".type select").val(type).trigger("change");
        });
    </script>
</body>
</html>