<html>
<head>
    <title>PEGR - Analysis Status</title> 
    <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3>
        <g:link controller="sequenceRun" action="show" id="${run.id}">Run ${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></g:link>
        <small>
            <span id="run-status-show" class="label label-default">${run.status}</span> 
            <span id="run-status-select" style="display:none">
                <g:select name="runStatus" from="${pegr.RunStatus}" value="${run.status}"></g:select>
                <button id="run-status-save" class="btn btn-primary">Save</button>
                <button id="run-status-cancel" class="btn btn-default">Cancel</button>
            </span>
        </small> 
    </h3>
    <g:each in="${runStatus}">
        <div class="pull-right"><span class="label label-success"> </span> Data received; <span class="label label-danger"> </span> No data. Click to see the step's category.</div>
        <div>
            <table class="table">
                <caption><h4>Pipeline: ${it.key.name}, versin: ${it.key.pipelineVersion} (workflow ID: ${it.key.workflowId}) <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link controller="pipelineAdmin" action="show" id="${it.key.id}" class="edit">Manage</g:link></sec:ifAnyGranted></h4></caption>
                <thead>
                    <tr>
                        <th>Sample</th>
                        <th>Cohort</th>
                        <th>Genome</th>
                        <th>Galaxy History</th>
                        <th>Date</th>                        
                        <th colspan="${it.value.steps.size()}">Status</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${it.value.sampleStatusList}" var="sample">
                    <tr>
                    <td rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                    <td rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.cohort}</td>
                    <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                        <g:if test="${n>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                            <td>${alignment.date}</td>
                            <g:each in="${alignment.status}" var="status" status="j">
                                <td class="analysis-status">
                                    <g:if test="${status}"><span data-toggle="popover" data-content="${it.value.steps[j][1]}" data-placement="top" class="label label-success"> </span></g:if> 
                                    <g:else><span data-toggle="popover" data-content="${it.value.steps[j][1]}" data-placement="top" class="label label-danger"> </span></g:else>
                                </td>
                            </g:each>
                            <td><g:link controller="report" action="deleteAlignment" params="[alignmentId:alignment.alignmentId, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-trash"</g:link></td>
                        </tr>
                    </g:each>
                    <g:if test="${sample.alignmentStatusList.size()==0}">
                        </tr>
                    </g:if>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:each>
    
    <h3>Reports</h3>
    <table class="table">
        <thead>
            <th>Sequencing Cohort</th>
            <th>Report</th>
            <th>Status</th>
            <th>Date</th>
            <th></th>
        </thead>
        <tbody>
        <g:each in="${run.cohorts}" var="cohort">
            <tr id="cohort-${cohort.id}">
                <g:render template="/report/reportRow" model="[cohort: cohort]"></g:render>
            </tr>
        </g:each>
        </tbody>
    </table>
    <br>
    <script>
        $(".confirm").confirm({text: "All data in this alignment will be deleted. Are you sure you want to delete this alignment?"});
        $(".nav-status").addClass("active");
        $('[data-toggle="popover"]').popover(); 
        
        $("#run-status-show").click(function(){
            $("#run-status-show").hide();
            $("#run-status-select").show();
        });
        
        $("#run-status-save").click(function(){
            var status = $("#run-status-select option:selected").text();
            $.ajax({ url: "/pegr/report/updateRunStatusAjax?runId=${run.id}&status=" + status,
                success: function(result) {
                    $("#run-status-show").text(result);
                    $("#run-status-select").val(result);
                    $("#run-status-show").show();
                    $("#run-status-select").hide();
                }                
            });
        });
        
        $("#run-status-cancel").click(function(){
            $("#run-status-show").show();
            $("#run-status-select").hide();
        });
        
        function createReport(cohortId) {
            $.ajax({ url: "/pegr/report/createReportForCohortAjax?cohortId=" + cohortId, success: function(result) {
                $("#cohort-"+cohortId).html(result);
            }});
            $('.confirm-remove-report').confirm({text: "Are you sure you want to delete this report?"});
        }
        
        function removeReport(cohortId) {
            $.confirm({
                text: "Are you sure you want to delete this report?",
                confirm: function() {
                    $.ajax({ url: "/pegr/report/deleteReportForCohortAjax?cohortId=" + cohortId, success: function(result) {
                        $("#cohort-"+cohortId).html(result);
                    }});  
                },
                cancel: function() {
                    // nothing to do
                }
            });
        }
    </script>
</body>
</html>