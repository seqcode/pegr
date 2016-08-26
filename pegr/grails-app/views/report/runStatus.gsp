<html>
<head>
    <title>PEGR - Analysis Status</title> 
    <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3><g:link controller="sequenceRun" action="show" id="${run.id}">Run ${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></g:link><small><span class="label label-default">${run.status}</span></small> </h3>
    <g:each in="${runStatus}">
        <div class="pull-right"><span class="label label-success"> </span> Data received; <span class="label label-danger"> </span> No data. Hover to see the step's category.</div>
        <div>
            <table>
                <caption>Pipeline: ${it.key.name}, versin: ${it.key.pipelineVersion} (workflow ID: ${it.key.workflowId}) </caption>
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
                    <td>${sample.cohort}</td>
                    <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                        <g:if test="${n>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                            <td>${alignment.date}</td>
                            <g:each in="${alignment.status}" var="status" status="j">
                                <td class="analysis-status">
                                    <g:if test="${status}"><span title="${it.value.steps[j][1]}" data-toggle="popover" data-placement="top" class="label label-success"> </span></g:if> 
                                    <g:else><span title="${it.value.steps[j][1]}" class="label label-danger"> </span></g:else>
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
    
    <h3>Sequencing Cohorts <g:link action="createReports" params="[runId: run.id]" class="edit">Generate Reports</g:link></h3>
    <ul>
        <g:each in="${run.cohorts}">
            <li>${it}<g:if test="${it.report}"><g:link controller="report" action="show" id="${it.report.id}">Report: ${it.report.name} </g:link></g:if></li>
        </g:each>
    </ul>
    <script>
        $(".confirm").confirm({text: "All data in this alignment will be deleted. Are you sure you want to delete this alignment?"});
        $(".nav-status").addClass("active");
                
        function completeRun() {
            $.ajax({url: "/pegr/report/completeRunAjax/${run.id}", success: function(result) {
                $("#runStatus").html(result)
            }});
        }
    </script>
</body>
</html>