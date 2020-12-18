<html>
<head>
  <title>Samples</title>
  <meta name="layout" content="main"/>
  <!-- <asset:javascript src="cookie.js"/> -->
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
  <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div class="container-fluid">
      <span class="pull-right"><g:link controller="sample" action="searchForm" class="btn btn-info nav-search-sample">Search</g:link></span>
      <h2>Samples</h2>      
      <g:render template="table" model="['sampleList':sampleList, 'checkbox':false]"></g:render>
      <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="50" total="${sampleList.totalCount ?: 0}" />
      </div>
</div>
  <script>
  $(function(){
    $("#nav-samples").addClass("active");
  });
  </script>
</body>
</html>
