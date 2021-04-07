<html>
<head>
  <title>Cell Source</title> 
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
    <h4>Antibody</h4>
    <div class="table-responsive">
        <div class="col-sm-10 text-left">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <g:sortableColumn property="id" title="ID" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="catalogNumber" title="Catalog" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="lotNumber" title="Lot" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="abHost" title="Host" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="immunogene" title="Immunogene" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="clonal" title="Clonal" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="igType" title="Ig Type" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <th>Barcode</th>
                    <th>Location</th>
                    <th>Active</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${objectList}" var="object">
                    <tr>
                        <td><g:link controller="antibody" action="show" id="${object.id}">${object.id}</g:link></td>
                        <td>${object.catalogNumber}</td>
                        <td>${object.lotNumber}</td>
                        <td>${object?.abHost}</td>
                        <td>${object.immunogene}</td>
                        <td>${object.clonal}</td>
                        <td>${object?.igType}</td>
                        <td>${object?.item?.barcode}</td>
                        <td>${object?.item?.location}</td>
                        <td>${object?.item?.active}</td>
                    </tr>
                </g:each>
            </tbody>
          </table>
          <div class="pagination">
            <g:paginate next="Next" prev="Prev" action="list" params="[active:active]" total="${objectCount ?: 0}" />
          </div>
        </div>
        <div class="col-sm-2 well text-center">
        <g:form action="list" params="[categoryId:currentCategory.id]" method="post">
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
        $(".item-${currentCategory.id}").addClass("active");
    </script>
</body>
</html>