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
                <g:select name="assayId" from="${pegr.Assay.list()}" optionKey="id" value="${sample.assay.id}" class="no-tag-select2" noSelection="['':'']"></g:select>
            </div>
            <div>
                <label>Growth Media</label>
                <select name="growthMedia" class="growth-media tag-select2 textcontrol" style="width: 150px" required>
                    <option value="${sample.growthMedia}" selected>${sample.growthMedia}</option>
                </select>
            </div>

            <div>
                <label>Treatments</label>
                <span id="treatments">
                <select multiple="multiple" class="treatments tag-select2" name="treatments" style="width: 300px">
                    <g:each in="${sample.treatments}">
                        <option value="${it}" selected>${it}</option>
                    </g:each>        
                </select>
                </span>
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
                <g:select name="userId" from="${pegr.User.list()}" optionKey="id" value="${sample?.prtclInstSummary.user.id}" noSelection="['':'']" class="no-tag-select2"></g:select>
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
            var tagPlaceholder = "Select or type";
            $(".no-tag-select2").select2({
                placeholder: noTagPlaceholder
            }); 

            $.ajax({url: "/pegr/sample/fetchGrowthMediaAjax?speciesId=${sample?.cellSource?.strain?.species?.id}", success: function(medias){
                $(".growth-media").select2({
                    data: medias,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});

            $.ajax({url: "/pegr/sample/fetchTreatmentsAjax", success: function(result) {
                $(".treatments").select2({
                    data: result,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});
        });
    </script>
</body>
</html>