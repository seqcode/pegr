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
</div>
  <script>
  $(function(){
    $(".nav-datasets").addClass("active");
  });

  $(document).ready(function(){
    $('#table_id').DataTable()({
      scrollY: '50vh',
      dom: 'Bfrtip',
      scrollCollapse: true,
      paging: true
    });
  });
  </script>
</body>
</html>
