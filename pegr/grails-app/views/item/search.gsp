<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <g:render template="searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h4>${currentCategory?.name}</h4>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <g:sortableColumn property="type" title="Type"></g:sortableColumn>
                    <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                    <g:sortableColumn property="barcode" title="Barcode"></g:sortableColumn>
                    <g:sortableColumn property="location" title="Location"></g:sortableColumn>
                    <g:sortableColumn property="lastUpdated" title="Last Updated"></g:sortableColumn>
                    <th>Notes</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${itemList}" var="item">
                    <tr>
                        <td>${item.type}</td>
                        <td><g:link action="show" params="[type:'Item', id:item.id]">${item.name}</g:link></td>
                        <td>${item.barcode}</td>
                        <td >${item.location}</td>
                        <td>${item.lastUpdated}</td>
                        <td>${item.notes}</td>
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="item" action="search" max="25" params="${params}" total="${itemCount ?: 0}" />
    </div>
    <script>
        $("select").select2();
    </script>
</body>
</html>