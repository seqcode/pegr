<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
</head>
<body>
    <div class="container-fluid">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>Edit Sample ${sample?.id}</h3>
        <g:form action="updateOther" class="fields">
            <g:hiddenField name="sampleId" value="${sample.id}"></g:hiddenField>
            <div>
                <label>Sample natural ID</label>
                <input name="naturalId" size="40" value="${sample.naturalId}">
            </div>
            <div>
                <label>Index <a href="#" onclick="window.open('/pegr/help/sequenceIndexHelp#sequence-index', 'Help: Sample Submission', 'width=600,height=400' )"><span class="glyphicon glyphicon-question-sign"></span></a> </label>
                <g:textField name="indices" style="width:200px" value="${sample.sequenceIndicesString}"></g:textField>
                
            </div>
            <div>
                <label>Chromosome (ug)</label>
                <input name="chromosomeAmount" class="isnumber" value="${sample.chromosomeAmount}">
            </div>
            <div>
                <label>Avail. Cell# per aliquot (M)</label>
                <input name="cellNumber" class="isnumber" value="${sample.cellNumber}">
            </div>
            <div>
                <label>Volume per aliquot (ul)</label>
                <input name="volume" class="isnumber" value="${sample.volume}">
            </div>
            <div>
                <label>Requested tags (M)</label>
                <input name="requestedTagNumber" class="isnumber" value="${sample.requestedTagNumber}">
            </div>
            <div>
                <label>Reference Genomes</label>
                <g:select multiple="multiple" name="requestedGenomes" from="${genomes}" value="${sample.requestedGenomes}" class="genomes no-tag-select2" style="width: 150px">
                </g:select>
            </div>
            <div>
                <label>Send data to</label>
                <g:select class="sendTo no-tag-select2" name="sendToId" from="${pegr.User.list()}" optionKey="id" style="width: 150px" value="${sample.sendDataTo}"></g:select>
            </div>
            <div>
                <label>Publication reference</label>
                <input name="publicationReference" value="${sample.publicationReference}">
            </div>
            <div>
                <label>Notes</label>
                <textarea name="note" cols="60" rows="4">${sample.note}</textarea>
            </div>
            <div>
                <label>GEO Accession</label>
                <input name="geoAccession" value="${sample.geoAccession}">
            </div>
            <div>
                <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                <g:link action="edit" params="[sampleId: sample.id]" class="btn btn-default">Cancel</g:link>
            </div>
        </g:form>
    </div>
    <script>
        $(document).ready(function(){
            $("#nav-metadata").addClass("active");        
            $("form").validate();
            var noTagPlaceholder = "Select...";
            $(".no-tag-select2").select2({
                placeholder: noTagPlaceholder
            }); 
        });
    </script>
</body>
</html>