<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="sequencing"/>
</head>
<body>
<div class="container-fluid">
    <h4>Sequencing Runs</h4>
    <ul class="nav nav-tabs">
        <li><g:link action="index">Processing</g:link></li>
        <li class="active"><a href="#">Completed</a></li>
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