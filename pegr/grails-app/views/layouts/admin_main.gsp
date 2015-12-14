<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR-Admin <g:layoutTitle/></title>
  <g:layoutHead/>
</head>
<body>

<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
        <g:each in="${controllers}">
            <li><g:link controller="${it.key}">${it.value}</g:link></li>
        </g:each>		
    </div>
    <div class="col-sm-8 text-left">
    <g:layoutBody/>
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>Link</p>
      </div>
      <div class="well">
        <p>Link</p>
      </div>
    </div>
  </div>
</div>

</body>
</html>
</g:applyLayout>
 <script>
	$("#nav-admin").addClass("active");
 </script>