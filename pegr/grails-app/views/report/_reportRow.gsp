
<td>${cohort}</td>
<td class="report">
    <g:if test="${cohort.report}">
        <g:link controller="report" action="show" id="${cohort.report.id}">Report: ${cohort.report.name}</g:link>                
    </g:if>
    <g:else>
        <button onclick="createReport(${cohort.id})" class="edit">Generate Report</button>
    </g:else>
</td>
<td class="report-status">${cohort.report?.status}</td>
<td class="report-user">${cohort.report?.user}</td>
<td class="report-date">${cohort.report?.date}</td>
<td class="report-remove"><g:if test="${cohort.report}"><a href="#" onclick="removeReport(${cohort.id})" class="confirm-remove-report"><span class="glyphicon glyphicon-trash"></span></a></g:if></td>