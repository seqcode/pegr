<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
<ul class="nav nav-tabs">
    <li><g:link controller="protocolInstanceBag" action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link></li>
    <li><g:link action="index">Search</g:link></li>
    <li class="active"><g:link>List</g:link></li>   
</ul>
<div class="row">

    <div class="col-md-10 text-left">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <g:sortableColumn property="type" title="Type"></g:sortableColumn>
                        <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                        <g:sortableColumn property="location" title="Location"></g:sortableColumn>
                        <th>Notes</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${itemList}" var="item">
                        <tr>
                        <td >${item.type} </td>
                        <td><g:link action="show" id="${item.id}">${item.name}</g:link></td>
                        <td >${item.location}</td>
                        <td>${item.notes}</td>
                        </tr>
                    </g:each>              
                    <tr>
                        <td colspan="4"></td>
                    </tr>
                </tbody>
              </table>
        </div>
        
        <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="item" action="list" total="${itemCount ?: 0}" />
        </div>

    </div>
    <div class="col-md-2 sidenav text-center">
        <h4>Filter</h4>
        <ul>
            <li><g:link action="list">All</g:link></li>
        <g:each in="${pegr.ItemType.list()}">
            <li><g:link action="list" params="[typeId: it.id]">${it}</g:link></li>
        </g:each>
        </ul>
    </div>
</div>
</div>
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>