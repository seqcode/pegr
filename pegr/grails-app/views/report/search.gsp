<html>
<head>
  <title>Samples</title> 
  <meta name="layout" content="main"/>
</head>
<body>
<div>
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
    <a href="#" class="btn btn-default">View Checked Samples</a>
<a href="#" class="btn btn-default">Clear All Checkboxes</a>   
    <g:link action="searchForm" class="btn btn-default pull-right">Search</g:link>
</div>
<g:render template="table" model="['sampleList':sampleList]"></g:render>
<div class="pagination">
    <g:paginate next="Next" prev="Prev" controller="sample" action="search" max="15" total="${sampleList.totalCount ?: 0}"  params="${params}"/>
</div>

<script>
$("#nav-metadata").addClass("active");
</script>
</body>
</html>
