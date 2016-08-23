<html>
    <head>
        <title>Workbench</title> 
        <meta name="layout" content="main"/>
    </head>
    <body>
        <div class="container-fluid">
            <h3>Edit Read Type and Positions for Sequence Run #${run?.id}</h3>
            <g:form action="updateRead" class="fields">
                <g:hiddenField name="runId" value="${run?.id}"></g:hiddenField>
                <div>
                    <label>Read Type</label>
                    <g:select name="readType" from="${pegr.ReadType.list()}" value="${read?.readType.shortName}" optionKey="shortName" id="read-type" onchange="readTypeChange()"></g:select>
                    <label>Index Type</label>
                    <g:select name="indexType" from="${['single', 'duo']}" value="${indexType}" id="index-type" onchange="indexTypeChange()"></g:select>
                </div>
                
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Start</th>
                            <th>End</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Read 1</td>
                            <td><input name="rd1Start" value="${read?.rd1?.getAt(0)}"></td>
                            <td><input name="rd1End" value="${read?.rd1?.getAt(1)}"></td>
                        </tr>
                        <tr class="single-index">
                            <td>Index </td>
                            <td><input name="indexStart" value="${read?.index?.getAt(0)}"></td>
                            <td><input name="indexEnd" value="${read?.index?.getAt(1)}"></td>
                        </tr>
                        <tr class="duo-index">
                            <td>Index 1</td>
                            <td><input name="index1Start" value="${read?.index1?.getAt(0)}"></td>
                            <td><input name="index1End" value="${read?.index1?.getAt(1)}"></td>
                        </tr>
                        <tr class="duo-index">
                            <td>Index 2</td>
                            <td><input name="index2Start" value="${read?.index2?.getAt(0)}"></td>
                            <td><input name="index2End" value="${read?.index2?.getAt(1)}"></td>
                        </tr>
                        <tr id="pair-read">
                            <td>Read 2</td>
                            <td><input name="rd2Start" value="${read?.rd2?.getAt(0)}"></td>
                            <td><input name="rd2End" value="${read?.rd2?.getAt(1)}"></td>
                        </tr>
                    </tbody>
                </table>            
                <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                <g:link action="show" id="${run?.id}" class="btn btn-default">Cancel</g:link>
            </g:form>            
        </div>
        <script>
            $("#nav-bench").addClass("active");
            indexTypeChange();
            readTypeChange();
            function indexTypeChange() {
                if ($("#index-type").val() == "single") {
                    $(".duo-index").hide();
                    $(".single-index").show();
                } else {
                    $(".duo-index").show();
                    $(".single-index").hide();
                }
            }
            function readTypeChange() {
                if ($("#read-type").val() == "SR") {
                    $("#pair-read").hide();
                } else {
                    $("#pair-read").show();
                }
            }
        </script>
    </body>
</html>