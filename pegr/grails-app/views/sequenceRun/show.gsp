<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
    
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
        <g:submitButton class="btn btn-primary btn-sm" name="search" value="Search"/>
    </g:form>    
    
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <g:sortableColumn property="id" title="ID"></g:sortableColumn>
                    <g:sortableColumn property="sample" title="Sample"></g:sortableColumn>
                    <th>Genome Build</th>
                    <th>Index</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${sampleList}">
                    <tr>
                    <td><g:link action="show" id="${it.id}">${it.id}</g:link></td>
                    <td>${it}</td>
                    <td>${it.genomes}</td>
                    <td>${it.indices}</td>
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
    
    <script>
        $("#nav-bench").addClass("active");
        $(".confirm").confirm();
     </script>
</div>
</body>
</html>