<html>
<head>
  <title>PEGR - Analysis Status</title> 
  <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <br>
    <ul class="nav nav-tabs">
        <li id="PREP"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'PREP']">Experiment Prep</g:link></li>
        <li id="QUEUE"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'QUEUE']">Queue</g:link></li>
        <li id="RUN"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'RUN']">Analyzing</g:link></li>
        <li id="COMPLETED"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'COMPLETED']">Completed</g:link></li>
    </ul>
    <div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="runNum" defaultOrder="desc" title="Run #"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Date"></g:sortableColumn>  
                <th>Sample status</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${runs}" var="run">
                <tr>
                    <td>${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></td>
                    <td><g:formatDate format="yyyy-MM-dd" date="${run.date}"/></td>
                    <td><g:link controller="report" action="runStatus" params="[runId: run.id]">Status</g:link></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="5"></td>
            </tr>
        </tbody>
      </table>
    </div>

    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="report" action="analysisStatus" params='[requestedStatus:"${status}"]' max="25" total="${totalCount ?: 0}" />
    </div>
    <script>
        $(".nav-status").addClass("active");
        $("#${status}").addClass("active");
    </script>
</body>
</html>