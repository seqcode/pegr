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
        <g:form action="updateProtocol" class="fields">
            <g:hiddenField name="sampleId" value="${sample.id}"></g:hiddenField>
            <div>
                <label>Assay</label>
                <g:select name="assayId" from="${pegr.Assay.list()}" optionKey="id" value="${sample.assay}" class="no-tag-select2" noSelection="['':'']"></g:select>
            </div>
            <div>
                <label>Resin</label>
                <input name="resin" value="${notes['Resin']}">
            </div>
            <div>
                <label>PCR Cycle</label>
                <input name="pcr" value="${notes['PCR Cycle']}">
            </div>
            <div>
                <label>Technician</label>
                <g:select name="userId" from="${pegr.User.list()}" optionKey="id" value="${sample?.prtclInstSummary.user}" noSelection="['':'']" class="no-tag-select2"></g:select>
            </div>
            <div>
                <label>Date</label>
                <g:datePicker precision="day" name="endTime" value="${sample?.prtclInstSummary.endTime}"/>
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