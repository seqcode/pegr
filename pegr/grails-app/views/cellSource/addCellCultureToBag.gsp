<html>
<head>
  <title>Add cell culture</title> 
  <meta name="layout" content="main"/>
<script type="text/javascript" >
    var changingHash=false;
</script>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <ul class="nav nav-pills">
        <li><g:link class="home"><g:message code="default.home.label"/></g:link></li>
    </ul>
    <h4>Add Strain</h4>
    <form class="fields" role="form" id="search-form" method="post">
        <g:hiddenField name="bagId" value="${bagId}"/>
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
        </div>
        <g:submitToRemote class="btn btn-primary" value="Search"
            url="[action: 'searchAjax']"
            update="itemPreview" onSuccess="clearSearchForm(data)"/>
        <button class="btn btn-default" onClick="clearItemPreview(); return false;">Cancel</button>
    </form>
    <div id="itemPreview"></div>                      

    <script>
        $("#nav-cell-culture").addClass("active");
     </script>
</div>
</body>
</html>