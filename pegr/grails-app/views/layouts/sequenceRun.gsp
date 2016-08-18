<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR - Experiments <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>

<div class="container-fluid text-center">    
    <div class="row content">
        <div class="col-sm-12 text-left">
            <div class="sub-nav">
                <g:link controller="protocolInstanceBag" class="btn btn-info">Work Records</g:link>
                <g:link controller="sequenceRun" class="btn btn-info active">Sequencing</g:link>
            </div>
            <g:layoutBody/>
        </div>
    </div>
</div>
    
 <script>
	$("#nav-experiments").addClass("active");
 </script>
</body>
</html>
</g:applyLayout>