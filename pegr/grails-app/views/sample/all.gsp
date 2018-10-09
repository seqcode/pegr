<html>
<head>
  <title>Samples</title>
  <g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
  <meta name="layout" content="analysis"/>
  <!-- <asset:javascript src="cookie.js"/> -->
  <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"></style>
  <script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div class="container-fluid">
      <br>
      <g:render template="table" model="['sampleList':sampleList, 'checkbox':false]"></g:render>

      <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="15" total="${sampleCount ?: 0}" />
      </div>
</div>
  <script>
  $(function(){
    $(".nav-datasets").addClass("active");
  });
  </script>
</body>
</html>
