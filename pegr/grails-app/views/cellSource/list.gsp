<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:render template="/cellSource/head"></g:render>
    
    <ul class="nav nav-tabs">
        <li class="active"><g:link action="list">Cell Stock</g:link></li>
        <li><g:link action="listBatches">Batches</g:link></li>
    </ul>
    <div>
        <g:form controller="cellSource" action="list">
            <label>Strain</label> 
            <g:select name="strain" from="${strains}" style="width:200px"></g:select>
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
                    <th>Mutation</th>
                    <th>Biological Source ID</th>
                    <th>Barcode</th>
                    <th>Type</th>
                    <th>Location</th>
                    <th>Active</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${cellSources}" var="cellSource">
                    <tr>
                        <input type="hidden" name="cellSourceId" value="${cellSource.id}" class="cellSourceId">
                        <td>${cellSource.strain?.species}</td>
                        <td>${cellSource.strain?.parent}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td>${cellSource.strain?.geneticModification}</td>
                        <td>${cellSource.biologicalSourceId}</td>
                        <td class="barcode item"><span class="value">${cellSource.item?.barcode}</span></td>
                        <td class="type item"><span class="value">${cellSource.item?.type}</span></td>
                        <td class="location item"><span class="value">${cellSource.item?.location}</span></td>
                        <td>${cellSource.item?.active}</td>
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