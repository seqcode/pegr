
<td class=" col-md-2">${cohort}</td>
<td class="report  col-md-3">
    <g:if test="${cohort.report}">
        <g:link controller="report" action="show" id="${cohort.report.id}">${cohort.report.name}</g:link>                
    </g:if>
    <g:else>
        <button onclick="createReport(${cohort.id})" class="edit">Generate Report</button>
    </g:else>
</td>
<td class="report-status col-md-2">${cohort.report?.status}</td>
<td class="report-user col-md-2">${cohort.report?.user}</td>
<td class="report-date col-md-2">${cohort.report?.date}</td>
<td class="report-remove  col-md-1"><g:if test="${cohort.report}"><a href="#" onclick="removeReport(${cohort.id})" class="confirm-remove-report"><span class="glyphicon glyphicon-trash"></span></a></g:if></td>