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
    <sec:ifAnyGranted roles="ROLE_ADMIN">
        <g:link controller="report" action="manage" class="edit pull-right">Manage</g:link>
    </sec:ifAnyGranted>
    <ul class="nav nav-tabs">
        <li id="ANALYZING"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'ANALYZING']">Analyzing</g:link></li>
        <li id="COMPLETED"><g:link controller="report" action="analysisStatus" params="[requestedStatus: 'COMPLETED']">Completed</g:link></li>
    </ul>
    <div class="table-responsive">
    <table class="table table-bordered">
        <thead>
            <tr>
                <g:sortableColumn property="runNum" defaultOrder="desc" title="Run #"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Sequencing Date"></g:sortableColumn>  
                <th>Platform</th>
                <th>Directory</th>
                <th>Run status</th>
                <th>Sample Status</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${runs}" var="run">
                <tr>
                    <td>${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></td>
                    <td><g:formatDate format="yyyy-MM-dd" date="${run.date}"/></td>
                    <td>${run.platform}</td>
                    <td>${run.directoryName}</td>
                    <td>${run.status}</td>
                    <td><g:link controller="report" action="runStatus" params="[runId: run.id]">Sample Status</g:link></td>
                </tr>
            </g:each>              
        </tbody>
      </table>
    </div>

    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="report" action="analysisStatus" params='[requestedStatus:"${status}"]' max="25" total="${totalCount ?: 0}" />
    </div>
    <script>        
        $(function(){
            $(".nav-status").addClass("active");
            $("#${status}").addClass("active");
        });
    </script>
</body>
</html>