<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">

<div class="row">
    <div class="col-md-10 text-left">
        <g:render template="/item/searchBar"></g:render>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h4>Antibody</h4>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title="ID"></g:sortableColumn>
                        <g:sortableColumn property="catalogNumber" title="Catalog"></g:sortableColumn>
                        <g:sortableColumn property="lotNumber" title="Lot"></g:sortableColumn>
                        <g:sortableColumn property="abHost" title="Host"></g:sortableColumn>
                        <g:sortableColumn property="immunogene" title="Immunogene"></g:sortableColumn>
                        <g:sortableColumn property="clonal" title="Clonal"></g:sortableColumn>
                        <g:sortableColumn property="igType" title="Ig Type"></g:sortableColumn>
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
                    <tr>
                        <td colspan="4"></td>
                    </tr>
                </tbody>
              </table>
        </div>

        <div class="pagination">
            <g:paginate next="Next" prev="Prev" action="list" total="${objectCount ?: 0}" />
        </div>
    </div>
    <g:render template="/item/filter"></g:render>
</div>
<script>
    $("#nav-bench").addClass("active");
    $("select").select2();
</script>
</body>
</html>