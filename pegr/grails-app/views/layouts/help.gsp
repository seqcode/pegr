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
                <g:link controller="help" class="btn btn-info help-experiments">Using PEGR</g:link>
                <g:link controller="help" action="assayHelp" class="btn btn-info help-assay">Assay</g:link>
                <g:link controller="help" action="sequenceIndexHelp" class="btn btn-info help-index">Index</g:link>
                <g:link controller="help" action="genomesHelp" class="btn btn-info help-genomes">Genome Build</g:link>
                <g:link controller="help" action="featuresHelp" class="btn btn-info help-features">Reference Features</g:link>
                <g:link controller="help" action="bioinformaticsApiHelp" class="btn btn-info help-api">Bioinformatics API</g:link>
            </div>
            <g:layoutBody/>
        </div>
    </div>
</div>
    
 <script>
	$("#nav-guide").addClass("active");
 </script>
</body>
</html>
</g:applyLayout>