<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:form class="fields" role="form" action="updateParent" method="post">
        <g:hiddenField name="itemId" value="${itemId}"></g:hiddenField>
        <div class="form-group">
            <label>Parent Type</label>
            <g:select name="parentTypeId" from="${pegr.ItemType.list()}" 
              optionKey="id" oprtionValue="name" noSelection="${['null':'--Choose--']}"/>
        </div>

        <div class="form-group"> 
            <label>Parent Barcode</label>
            <g:textField id="barcode" name="parentBarcode"/>
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
        </div>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${itemId}">Cancel</g:link>
    </g:form>    
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>