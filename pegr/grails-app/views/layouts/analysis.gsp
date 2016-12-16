<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Analysis <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>   
    <div class="sub-nav">
        <g:link controller="sequenceRun" action="index" class="btn btn-info nav-runs">Sequencing Runs</g:link>
        <g:link controller="sample" action="all" class="btn btn-info nav-datasets">Samples</g:link>
        <a href="http://galaxy-cegr.psu.edu:8080/" class="btn btn-info external" target="_blank">Galaxy</a>
    </div>
    <g:layoutBody/>
    
     <script>
        $("#nav-analysis").addClass("active");
     </script>
</body>
</html>
</g:applyLayout>