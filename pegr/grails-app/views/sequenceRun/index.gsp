<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h4>Sequencing Runs</h4>
    <g:if test="${flash.message}">
        <g:each in="${flash.message}">
            <div class="message" role="status">
                ${it}
             </div>
         </g:each>   
    </g:if>
    
    <ul class="nav nav-tabs">
        <li class="active"><g:link action="index">Processing</g:link></li>
        <li><g:link action="completedRuns">Completed</g:link></li>
        <li><g:link action="create">New</g:link></li>
        <li><g:link action="upload">Upload</g:link></li>
    </ul>
    <g:render template="/sequenceRun/table"></g:render>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" total="${runs.totalCount ?: 0}" />
    </div>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>