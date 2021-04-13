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
    <div class="col-sm-10 text-left">
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <g:sortableColumn property="type" title="Type" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="name" title="Name" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="barcode" title="Barcode" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <g:sortableColumn property="location" title="Location" params="[categoryId:currentCategory.id]"></g:sortableColumn>
                    <th>Notes</th>
                    <th>Active</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${itemList}" var="item">
                    <tr>
                        <td>${item.type}</td>
                        <td><g:link action="show" params="[type:'Item', id:item.id]">${item.name}</g:link></td>
                        <td>${item.barcode}</td>
                        <td >${item.location}</td>
                        <td>${item.notes}</td>
                        <td><g:if test="${item.active}">ACTIVE</g:if><g:elseif test="${item.active != null}">INACTIVE</g:elseif></td>
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" action="list" params="[categoryId:currentCategory.id, active:active]" total="${itemCount ?: 0}" />
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
            <g:submitButton class="btn btn-primary" name="submit" value="Filter"></g:submitButton>
            <button onclick="$('input:radio').prop('checked', false);" class="btn btn-default">Clear</button>
        </g:form>
    </div>
    <script>
        $("select").select2();
        $(".item-${currentCategory.id}").addClass("active");
    </script>
</body>
</html>