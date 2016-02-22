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
    <h2>Search Sample</h2>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#searchBarcode">Barcode</a></li>
        <li><a data-toggle="tab" href="#searchId">Sample ID</a></li>
    </ul>

    <div class="tab-content">
        <div id="searchBarcode" class="tab-pane fade in active">
            <g:form class="fields" role="form" action="searchSampleByBarcode" >
                <g:hiddenField name="runId" value="${runId}"></g:hiddenField>
                <div class="form-group">
                    <label for="type">Type</label>
                    <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" />
                </div>        
                <div class="form-group">
                    <label for="barcode">Barcode</label>
                     <g:textField id="barcode" name="barcode" />
                    <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
                </div>
                <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search"/>
                <g:link action="edit" id="${runId}" class="btn btn-default">Cancel</g:link>
            </g:form>    
        </div>
        <div id="searchId" class="tab-pane fade">
            <g:form class="fields" role="form" action="searchSampleById" >
                <g:hiddenField name="runId" value="${runId}"></g:hiddenField>
                <div class="form-group">
                    <label for="sampleId">Sample ID</label>
                    <g:textField id="sampleId" name="sampleId" />
                </div>  
                <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search"/>
                <g:link action="edit" id="${runId}" class="btn btn-default">Cancel</g:link>
            </g:form>    
        </div>   
    </div>        

    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>