
<td class="col-md-2">${cohort}</td>
<td class="report-notes col-md-1">
    <div class="dropdown">
        <span class="dropdown-toggle" type="button" data-toggle="dropdown">
            <g:if test="${cohort.notes}"><span class="glyphicon glyphicon-file"></span></g:if>
            <g:else>+</g:else>
        </span>
        <div class="dropdown-menu">
            <input class="cohort-id" type="hidden" name="cohortId" value="${cohort.id}">
            <input class="orig-notes" type="hidden" name="origNotes" value="${cohort.notes}">
            <textarea name="notes" class="notes">${cohort.notes}</textarea>
            <button class="pull-right btn btn-default cancel-notes">Cancel</button>
            <button class="pull-right btn btn-primary save-notes">Save</button>
        </div>
    </div>
</td>
<td class="report  col-md-4">
    <g:if test="${cohort.report}">
        <g:link controller="report" action="show" id="${cohort.report.id}">${cohort.report.name}</g:link>                
    </g:if>
    <g:else>
        <button onclick="createReport(${cohort.id})" class="edit">Generate Report</button>
    </g:else>
</td>
<td class="report-status col-md-2">${cohort.report?.status}</td>
<td class="report-date col-md-2">${cohort.report?.date}</td>
<td class="report-remove  col-md-1"><g:if test="${cohort.report}"><a href="#" onclick="removeReport(${cohort.id})" class="confirm-remove-report"><span class="glyphicon glyphicon-trash"></span></a></g:if></td>
