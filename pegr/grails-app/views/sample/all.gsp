<html>
<head>
  <title>Samples</title>
  <g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
  <meta name="layout" content="analysis"/>
</head>
<body>
  <div class="container-fluid">
      <g:render template="table" model="['sampleList':sampleList, 'checkbox':false]"></g:render>

      <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="15" total="${sampleCount ?: 0}" />
      </div>
</div>
  <script>
  $(".nav-datasets").addClass("active");
  </script>
  </div>
</body>
</html>
