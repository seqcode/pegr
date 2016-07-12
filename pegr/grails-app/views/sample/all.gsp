<html>
<head>
  <title>Samples</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <g:form action="showChecked">
        <div class="row">
            <div class="pull-right">
                <g:submitButton name="View Checked Samples" class="btn btn-default"></g:submitButton>
                <g:remoteLink action="clearCheckedSampleAjax" class="btn btn-default">Clear All Checkboxes</g:remoteLink>
                <g:link action="searchForm" class="btn btn-default">Search</g:link>   
            </div>
        </div>
        <g:render template="table" model="['sampleList':sampleList]"></g:render>
    </g:form>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="15" total="${sampleCount ?: 0}" />
    </div>

    <script>
    $("#nav-metadata").addClass("active");
    </script>
</body>
</html>
