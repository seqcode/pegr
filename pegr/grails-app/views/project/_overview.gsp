<table class="table-bordered">
    <thead>
        <tr>
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
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}"><g:link controller="project" action="show" id="${project.id}">${project.name}</g:link> </td>
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}">${project.description}</td>
            <td class="col-sm-3" rowspan="${Math.max(project.cohorts.size(), 1)}"><g:formatDate format="yyyy-MM-dd" date="${project.dateCreated}"/></td>
            <g:if test="${!project.cohorts?.size()}">
                <td></td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${project.cohorts}" var="cohort" status="n">
                    <g:if test="${n>0}"><tr></g:if>
                    <td>${cohort.name} <g:if test="${cohort.report}"><g:link controller="report" action="show" id="${cohort.report.id}" class="edit">Report</g:link></g:if></td>
                    <!-- ADDED COLUMN DATA FOR COHORT RUN STATUS -->
                    <td><span class="label">${cohort.run.status}</span></td>
                    </tr>
                </g:each>
            </g:else>
        </g:each>
    </tbody>
</table>
