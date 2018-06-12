<div class="table-responsive">
    <table class="table table-bordered">
        <thead>
            <tr>
                <g:sortableColumn property="id" defaultOrder="desc" title="Run #"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Date"></g:sortableColumn>
                <g:sortableColumn property="status" title="Run Analysis Status"></g:sortableColumn>
                <th>Project</th>
                <th>Summary Report</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${runs}" var="run">
                <tr>
                    <td class="col-sm-2" rowspan="${Math.max(1, run.cohorts.size())}"><g:link controller="sequenceRun" action="show" id="${run.id}">${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if><br><i>${run.platform}</i></g:link></td>
                    <td class="col-sm-2" rowspan="${Math.max(1, run.cohorts.size())}"><g:formatDate format="yyyy-MM-dd" date="${run.date}"/></td>
                    <td class="col-sm-2" rowspan="${Math.max(1, run.cohorts.size())}"><span class="label">${run.status}</span><g:link controller="report" action="runStatus"  params="[runId: run.id]"><br>Details</g:link></td>
                    <g:each in="${run.cohorts}" var="cohort" status="n">
                    <g:if test="${n>0}"><tr></g:if>
                    <td class="col-sm-2"><g:link controller="project" action="show" id="${cohort.project.id}">${cohort.project}</g:link></td>
                    <td class="col-sm-2"><g:if test="${cohort.report}"><g:link controller="report" action="show" id="${cohort.report?.id}">${cohort.report?.name}</g:link> <g:link controller="report" action="print" id="${cohort.report?.id}" target="_blank"><span class="glyphicon glyphicon-print"></g:link> <g:link controller="report" action="listFiles" id="${cohort?.report?.id}" target="_blank"><span class="glyphicon glyphicon-list-alt"></g:link></g:if></td>
                    </tr>
                    </g:each>
                </tr>
            </g:each>
        </tbody>
      </table>
</div>

<script>
    $(".label").each(function() {
        if ($(this).text() == "ANALYZING") {
            $(this).addClass("label-info");
        } else if ($(this).text() == "COMPLETED") {
            $(this).addClass("label-success");
        } else if ($(this).text() == "QUEUE") {
            $(this).addClass("label-warning");
        } else if ($(this).text() == "FAILED") {
            $(this).addClass("label-danger");
        } else {
            $(this).addClass("label-default");
        }
    });
</script>
