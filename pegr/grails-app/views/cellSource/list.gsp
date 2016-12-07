<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:link controller="item" action="generateBarcodeList" class="edit pull-right" target="_blank">Generate Barcode List</g:link>
    <g:link controller="cellSource" action="batchCreate" class="edit pull-right">Batch Create</g:link>
    
    <button type="button" class="edit pull-right" data-toggle="modal" data-target="#myModal">Upload CSV</button>

    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                <h3>Upload CSV File</h3>
                <g:uploadForm action="importCellStock" >
                    <div class="form-group">
                        <label>Lines from</label>
                        <g:textField name="startLine"></g:textField>
                        <label>To</label>
                        <g:textField name="endLine"></g:textField>
                    </div>

                    <div class="form-group">
                        <input type="file" name="file"/> (only csv files)
                    </div>
                    <div class="form-group">
                        <g:submitButton name="upload" value="Upload" class="btn btn-primary"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </g:uploadForm>
                </div>
            </div>
        </div>
    </div>
    
    <ul class="nav nav-tabs">
        <li class="active"><g:link action="list">Cell Stock</g:link></li>
        <li><g:link action="listBatches">Batches</g:link></li>
    </ul>
    <div>
        <g:form controller="cellSource" action="list">
            <label>Strain</label> 
            <g:select name="strain" from="${strains}" style="width:200px"></g:select>
            <g:submitButton name="submit" value="Search" class="btn btn-primary"></g:submitButton>
        </g:form>
    </div>
    <div class="container-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Species</th>
                    <th>Parent</th>
                    <th>Strain</th>                    
                    <th>Barcode</th>
                    <th>Type</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${cellSources}" var="cellSource">
                    <tr>
                        <input type="hidden" name="cellSourceId" value="${cellSource.id}" class="cellSourceId">
                        <td>${cellSource.strain?.species}</td>
                        <td>${cellSource.strain?.parent}</td>
                        <td><g:link controller="cellSource" action="show" params="[id:cellSource.id]">${cellSource.strain}</g:link></td>
                        <td class="barcode item"><span class="value">${cellSource.item?.barcode}</span></td>
                        <td class="type item"><span class="value">${cellSource.item?.type}</span></td>
                        <td class="location item"><span class="value">${cellSource.item?.location}</span></td>
                    </tr>
                </g:each>
            </tbody>
          </table>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" action="list" total="${cellSources.totalCount ?: 0}" />
    </div>
    <script>
        $("select").select2();
        $(".item-${categoryId}").addClass("active");
    </script>
</body>
</html>