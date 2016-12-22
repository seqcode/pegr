<button type="button" class="edit" data-toggle="modal" data-target="#printModal">Print</button>
<div id="printModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">                
                <g:form controller="protocolInstanceBag" action="showAllTracedSampleBarcodes" class="fields">
                    <g:hiddenField name="instanceId" value="${protocolInstance?.id}"></g:hiddenField>
                    <div>
                        <label>Print</label>
                        <select name="type">
                            <option value="Children">Children</option>
                            <option value="Parents">Parents</option>                            
                        </select>
                    </div>
                    <div>
                        <label>Start Row</label>
                        <input name="row" value="1" size="2">
                        <label>Column</label>
                        <input name="column" value="1" size="2">
                    </div>                    
                    <g:submitButton name="print" value="Print" class="btn btn-primary"></g:submitButton>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </g:form>
            </div>
        </div>
    </div>
</div>