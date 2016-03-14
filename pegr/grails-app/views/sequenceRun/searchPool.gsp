<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Sequencing Run List</g:link>   
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>   
    <h2>Search Pool</h2>    
    <g:form class="fields" role="form" action="searchPool" method="post">
        <g:hiddenField name="runId" value="${runId}"></g:hiddenField>
        <div class="form-group">
            <label for="type">Type</label>
            <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.where{category==pegr.ItemTypeCategory.SAMPLE_POOL}.list(sort:'name')}" noSelection="['null': '-- choose --']" />
        </div>        
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
        </div>
        <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search"/>
        <g:link action="edit" id="${runId}" class="btn btn-default">Cancel</g:link>
    </g:form>    

    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>