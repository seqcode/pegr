<html>
<head>
  <title>Samples</title> 
  <meta name="layout" content="analysis"/>
</head>
<body>
    <div class="container-fluid">
        <div style="padding-top: 10px">
            <g:link action="searchForm" class="btn btn-info">Search</g:link>   
        </div>
        
        <g:render template="table" model="['sampleList':sampleList, 'checkbox':false]"></g:render>

        <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="15" total="${sampleCount ?: 0}" />
        </div>
    </div>
    <script>
        $(".nav-datasets").addClass("active");
    </script>
</body>
</html>
