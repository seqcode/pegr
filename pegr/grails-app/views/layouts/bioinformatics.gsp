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
                <g:link controller="bioinformatics" class="btn btn-info active">Genomes</g:link>
                <a href="#" class="btn btn-info">Features</a>
                <a href="https://github.com/seqcode/cegr-tools" target="_blank" class="btn btn-info">Scripts</a>
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