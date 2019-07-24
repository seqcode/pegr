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
        <g:form action="updateTarget" class="fields">
            <g:hiddenField name="sampleId" value="${sample.id}"></g:hiddenField>
            <div>
                <label>Target type</label>
                <g:select name="type" from="${pegr.TargetType.list()}" optionKey="name" value="${target?.targetType?.name}" noSelection="['':'']" class="tag-select2"></g:select>
            </div>
            <div>
                <label>Target</label>
                <select id="target" name="target" style="width: 150px">
                    <option value="${target?.name}" selected>${target?.name}</option>
                </select>
            </div>
            <div>
                <label>C-Tag</label>
                <select id="cterm" name="cterm" style="width: 150px">
                    <option value="${target?.cTermTag}" selected>${target?.cTermTag}</option>
                </select>
            </div>
            <div>
                <label>N-Tag</label>
                <select id="nterm" name="nterm" style="width: 150px">
                    <option value="${target?.nTermTag}" selected>${target?.nTermTag}</option>
                </select>
            </div>
            <div>
                <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                <g:link action="edit" params="[sampleId: sample.id]" class="btn btn-default">Cancel</g:link>
            </div>
        </g:form>
    </div>
    <script>    
        var tagPlaceholder = "Select or type...";

        $(document).ready(function(){
            $("#nav-metadata").addClass("active");
            $(".tag-select2").select2({
                tags: true,
                placeholder: tagPlaceholder
            });
            $.ajax({url: "/pegr/antibody/fetchTargetAjax", success: function(result){
                $("#target").select2({
                    data: result.targets,
                    tags: true,
                    placeholder: tagPlaceholder
                });
                $("#nterm").select2({
                    data: result.nterms,
                    tags: true,
                    placeholder: tagPlaceholder
                });
                $("#cterm").select2({
                    data: result.cterms,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});
        });
    </script>
</body>
</html>
