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
                <g:sortableColumn property="cohort.run" title="Sequnece Run"></g:sortableColumn>
                <g:sortableColumn property="cohort.project" title="Project"></g:sortableColumn> 
                <g:sortableColumn property="status" title="Status"></g:sortableColumn> 
                <g:sortableColumn property="date" title="Date"></g:sortableColumn> 
            </tr>
        </thead>
        <tbody>
            <g:each in="${reports}" var="report">
                <tr>
                    <td><strong><g:link action="show" id="${report.id}">${report.name}</g:link></strong></td>
                    <td><g:link controller="sequenceRun" action="show" id="${report.cohort?.run?.id}">Run#${report.cohort?.run?.id}</g:link></td>
                    <td><g:link controller="project" action="show" id="${report?.cohort?.project?.id}">${report.cohort?.project?.name}</g:link></td>     
                    <td>${report.status}</td>
                    <td>${report.date}</td>
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
