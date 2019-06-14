<table class="table-bordered">
    <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Description</th>
            <th>Date Created</th>
            <th>Sequencing Cohorts</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${projects}" var="project">
        <tr>
            <td rowspan="${Math.max(project.cohorts.size(), 1)}"><g:if test="${checkbox}"><input type="checkbox" name="checkedProject" class="checkbox" value="${project.id}" onchange="toggleChecked(this)" <g:if test="${project.id in session.checkedProject}">checked</g:if>></g:if></td>
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}"><g:link controller="project" action="show" id="${project.id}">${project.name}</g:link> </td>
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}">${project.description}</td>
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}"><g:formatDate format="yyyy-MM-dd" date="${project.dateCreated}"/></td>
            <g:if test="${!project.cohorts?.size()}">
                <td></td>
                <td></td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${project.cohorts}" var="cohort" status="n">
                    <g:if test="${n>0}"><tr></g:if>
                    <td>${cohort.name} <g:if test="${cohort.report}"><g:link controller="report" action="show" id="${cohort.report.id}" class="edit">Report</g:link></g:if></td>
                    <!-- ADDED COLUMN DATA FOR COHORT RUN STATUS -->
                    <td><span class="label" style="background-color:#7FB88C">${cohort.run.status}</span></td>
                    </tr>
                </g:each>
            </g:else>
        </g:each>
    </tbody>
</table>
