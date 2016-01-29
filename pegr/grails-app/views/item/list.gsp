<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">

<g:link controller="protocolInstanceBag" action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
<div class="row">

    <div class="col-md-10 text-left">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form class="fields form-inline well well-sm" role="form" action="preview" >
        <div class="form-group">
            <label for="type">Type</label>
            <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" />
        </div>        
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
        </div>
        <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search/Create"/>
    </g:form>    
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
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>