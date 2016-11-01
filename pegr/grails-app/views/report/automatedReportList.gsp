<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Summary Report</th>
                <g:sortableColumn property="run" title="Sequnece Run"></g:sortableColumn>
                <g:sortableColumn property="project" title="Project"></g:sortableColumn> 
                <th>Status</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${cohorts}" var="cohort">
                <tr>
                    <td><strong><g:link action="show" id="${cohort.report?.id}">${cohort.report?.name}</g:link></strong></td>
                    <td><g:link controller="sequenceRun" action="show" id="${cohort?.run?.id}">Run#${cohort.run?.id}</g:link></td>
                    <td><g:link controller="project" action="show" id="${cohort?.project?.id}">${cohort.project?.name}</g:link></td>
                    <td>${cohort.report?.status}</td>
                    <td>${cohort.report?.date}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="report" action="all" max="25" total="${totalCount ?: 0}" />
    </div>
     <script>
        $(".nav-reports").addClass("active");
     </script>
</body>
</html>
