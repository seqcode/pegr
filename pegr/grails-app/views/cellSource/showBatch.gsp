<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:link controller="item" action="batchAddBarcode" class="confirm edit pull-right">Assign Barcodes</g:link>
    
    <ul class="nav nav-tabs">
        <li><g:link action="list">Cell Stock</g:link></li>
        <li class="active"><g:link action="listBatches">Batches</g:link></li>
    </ul>
    <div class="container-fluid">
        <h4>Batch ${batch}</h4>
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
                        <td class="barcode item"><span class="value">${cellSource.item?.barcode}</span></td>
                        <td class="type item"><span class="value">${cellSource.item?.type}</span></td>
                        <td class="location item"><span class="value">${cellSource.item?.location}</span></td>
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>
    <script>
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>