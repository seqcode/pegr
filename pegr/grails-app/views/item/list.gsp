<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">

<div class="row">
    <div class="col-md-10 text-left">
        <g:render template="searchBar"></g:render>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h4>${currentType?.name}</h4>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                        <g:sortableColumn property="barcode" title="Barcode"></g:sortableColumn>
                        <g:sortableColumn property="location" title="Location"></g:sortableColumn>
                        <th>Notes</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${objectList}" var="object">
                        <tr>
                        <td><g:link action="show" params="[type:'Item', id:object.id]">${object.name}</g:link></td>
                            <td>${object.barcode}</td>
                            <td >${object.location}</td>
                            <td>${object.notes}</td>
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
    <g:render template="filter"></g:render>
</div>
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>