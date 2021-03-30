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
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>

    <div class="pagination">
        <g:paginate next="Next" prev="Prev" action="list" total="${objectCount ?: 0}" />
    </div>
    <script>
        $("select").select2();
        $(".item-${currentCategory.id}").addClass("active");
    </script>
</body>
</html>