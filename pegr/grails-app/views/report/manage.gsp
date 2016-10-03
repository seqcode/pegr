<html>
    <head>
        <title>PEGR - Analysis Status</title> 
        <meta name="layout" content="analysis"/>
    </head>
    <body>
        <br>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#qc-settings">Quality Control Settings</a></li>
            <li><a data-toggle="tab" href="#purge-alignments">Delete Purged Alignments</a></li>
        </ul>
        <div class="tab-content">
        <div id="qc-settings" class="tab-pane fade in active">            
            <button id="add" class="pull-right edit">Add Row</button>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:form controller="report" action="saveQcSettings">
            <table>            
                <thead>
                    <tr>
                        <th>key</th>
                        <th>name</th>
                        <th>format</th>
                        <th>min</th>
                        <th>max</th>
                        <th>reference min</th>
                        <th>reference max</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qcSettings}">
                        <tr>
                            <td><input name="key" value="${it.key}"></input></td>
                            <td><input name="name" value="${it.name}"></input></td>
                            <td><input name="numFormat" value="${it.numFormat}"></input></td>
                            <td><input name="min" value="${it.min}"></input></td>
                            <td><input name="max" value="${it.max}"></input></td>
                            <td><input name="reference_min" value="${it.reference_min}"></input></td>
                            <td><input name="reference_max" value="${it.reference_max}"></input></td>
                        </tr>
                    </g:each>                
                </tbody>
            </table>
            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
            <g:link controller="report" action="analysisStatus" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <div id="purge-alignments"  class="tab-pane fade">           
            <g:if test="${purgeConfig?.lastRunTime}">
                <p>On ${purgeConfig.lastRunTime}, purged alignments between ${purgeConfig.lastStartDate} and ${purgeConfig.lastEndConfig} were deleted.</p>
            </g:if>            
            <g:form controller="report" action="deletePurgedAlignments" class="fields">
                <div>
                    <label>Start Date</label>
                    <g:datePicker name="startDate" value="${purgeConfig?.lastEndDate}"></g:datePicker>
                </div>
                <div>
                    <label>End Date</label>
                    <g:datePicker name="endDate" value="${purgeConfig?.lastEndDate}"></g:datePicker>
                </div>
                <g:submitButton name="submit" value="Submit" class="btn btn-primary"></g:submitButton>
                <g:link controller="report" action="analysisStatus" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        </div>
        <script>
            $(function() {
               $(".nav-status").addClass("active");
            });
            
            $("#add").click(function() {
                var $orig = $("tr").last();
                // copy the row
                $('tbody').append(
                    $('<tr/>')
                        .append(
                            $orig.children().clone(true))
                );
            });
        </script>
    </body>
</html>