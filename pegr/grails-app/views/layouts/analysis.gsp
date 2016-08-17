<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Analysis <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>
<div class="container-fluid text-center">    
    <div class="row content">
        <div class="col-sm-12 text-left">
            <div class="sub-nav">
                <a href="#" class="btn btn-info">Run Status</a>
                <g:link controller="Report" action="all" class="btn btn-info">Reports</g:link>
                <a href="#" class="btn btn-info">Datasets</a>
                <a href="http://galaxy-cegr.psu.edu:8080/" class="btn btn-info">Analyze</a>
            </div>
            <g:layoutBody/>
        </div>
    </div>
</div>
    
 <script>
	$("#nav-analysis").addClass("active");
 </script>
</body>
</html>
</g:applyLayout>