<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <g:render template="/item/searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:link controller="item" action="generateBarcodeList" class="edit pull-right" target="_blank">Generate Barcode List</g:link>
    <g:link controller="cellSource" action="batchCreate" class="edit pull-right">Batch Create</g:link>
    <h4>Cell Stock</h4>
    <div class="table-responsive">
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
                        <td>${cellSource.strain?.species}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td><g:if test="${cellSource.item?.barcode}">${cellSource.item?.barcode}</g:if><g:else><span class="glyphicon glyphicon-qrcode"></span></g:else></td>
                        <td>${cellSource.item?.type}</td>
                        <td >${cellSource.item?.location}</td>
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
    </script>
</body>
</html>