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
            <g:select name="strain" from="${strains}" style="width:200px" value="${strainName}"></g:select>
            <g:submitButton name="submit" value="Search" class="btn btn-primary"></g:submitButton>
        </g:form>
    </div>
    <div class="container-fluid">
        <div class="col-sm-10 text-left">
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
                        <td><g:if test="${cellSource.item?.active}">ACTIVE</g:if><g:elseif test="${cellSource.item?.active != null}">INACTIVE</g:elseif></td>
                    </tr>
                </g:each>
            </tbody>
          </table>
        <div class="pagination">
            <g:paginate next="Next" prev="Prev" action="list" params="[categoryId:categoryId, active:active]" total="${cellSources.totalCount ?: 0}" />
        </div>
        </div>
        <div class="col-sm-2 well text-center">
        <g:form action="list" params="[categoryId:categoryId]" method="post">
            <div class="form-group">
                <input type="radio" name="active" value="active" <g:if test="${active=='active'}">checked</g:if>> Active items
            </div>
            <div class="form-group">
                <input type="radio" name="active" value="inactive" <g:if test="${active=='inactive'}">checked</g:if>> Inactive items
            </div>
            <div class="form-group">
                <input type="radio" name="active" value="noBarcode" <g:if test="${active=='noBarcode'}">checked</g:if>> No barcode
            </div>
            <g:submitButton class="btn btn-primary" name="submit" value="Filter"></g:submitButton>
            <button onclick="$('input:radio').prop('checked', false);" class="btn btn-default">Clear</button>
        </g:form>
        </div>
    </div>
    <script>
        $("select").select2();
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>