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
                    <th>Parent</th>
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
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" action="list" total="${cellSources.totalCount ?: 0}" />
    </div>
    <script>
        $("select").select2();
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>