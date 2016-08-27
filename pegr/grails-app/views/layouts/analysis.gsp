<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Analysis <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>   
    <div class="sub-nav">
        <g:link controller="report" action="analysisStatus" params="[requestedStatus: 'ANALYZING']" class="btn btn-info nav-status">Sequencing Status</g:link>
        <g:link controller="report" action="automatedReportList" class="btn btn-info nav-reports">Reports</g:link>
        <g:link controller="sample" action="all" class="btn btn-info nav-datasets">Datasets</g:link>
        <a href="http://galaxy-cegr.psu.edu:8080/" class="btn btn-info">Analyze</a>
    </div>
    <g:layoutBody/>
    
     <script>
        $("#nav-analysis").addClass("active");
     </script>
</body>
</html>
</g:applyLayout>