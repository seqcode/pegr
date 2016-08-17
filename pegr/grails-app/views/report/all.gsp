<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <br>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Automated</a></li>
        <li><g:link controller="sample" action="searchForm">Search</g:link></a></li>
    </ul>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Summary Report</th>
                <g:sortableColumn property="run.id" title="Sequnece Run"></g:sortableColumn>
                <g:sortableColumn property="project.id" title="Project"></g:sortableColumn>                
            </tr>
        </thead>
        <tbody>
            <g:each in="${reports}" var="report">
                <tr>
                    <td><strong><g:link action="show" id="${report.id}">${report}</g:link></strong></td>
                    <td><g:link controller="sequenceRun" action="show" id="${report.run.id}">Run#${report.run.id}</g:link></td>
                    <td><g:link controller="project" action="show" id="${report.project.id}">${report.project.name}</g:link></td>                    
                </tr>
            </g:each>              
            <tr>
                <td colspan="4"></td>
            </tr>
        </tbody>
      </table>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="report" action="all" max="25" total="${totalCount ?: 0}" />
    </div>
     <script>
        $("#nav-reports").addClass("active");
     </script>
</body>
</html>
