<g:if test="${purgeConfig?.lastRunTime}">
    <h4>Previous Job</h4>
    <p>On ${purgeConfig.lastRunTime}, purged alignments between ${purgeConfig.lastStartDate} and ${purgeConfig.lastEndDate} were deleted.</p>
</g:if>
<g:if test="${purgeConfig?.status == 'RUN'}">
    <h4>Current Job</h4>
    <p>The current job was started on ${purgeConfig.currentRunTime} to delete purged alignments between ${purgeConfig.currentStartDate} and ${purgeConfig.currentEndDate}.</p>
</g:if>
<g:else>
    <g:if test="${purgeConfig?.status == 'ERROR'}">
        <h4>Errored Job</h4>
        <p>The errored job was started on ${purgeConfig.currentRunTime} to delete purged alignments between ${purgeConfig.currentStartDate} and ${purgeConfig.currentEndDate}.</p>
    </g:if>
    <div>
        <label>Start Date</label>
        <g:datePicker name="startDate" value="${purgeConfig?.lastEndDate}"></g:datePicker>
    </div>
    <div>
        <label>End Date</label>
        <g:datePicker name="endDate" value="${purgeConfig?.lastEndDate}"></g:datePicker>
    </div>
</g:else> 