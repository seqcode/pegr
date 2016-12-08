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
                The CSV file should have columns (Genus, Species, Parent Strain, Strain).
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