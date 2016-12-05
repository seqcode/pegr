<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Bioinformatics <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>

<div class="container-fluid text-center">    
    <div class="row content">
        <div class="col-sm-12 text-left">
            <div class="sub-nav">
                <g:link controller="bioinformatics" class="btn btn-info genome">Genome Build</g:link>
                <g:link controller="bioinformatics" action="features" class="btn btn-info">Reference Features</g:link>
                <a href="https://github.com/seqcode/cegr-tools" target="_blank" class="btn btn-info external">Scripts</a>
                <g:link controller="api" action="help" class="btn btn-info api">API</g:link>
            </div>
            <g:layoutBody/>
        </div>
    </div>
</div>
    
 <script>
	$("#nav-bioinformatics").addClass("active");
 </script>
</body>
</html>
</g:applyLayout>