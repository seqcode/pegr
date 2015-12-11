<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR-Dashboard</title>
  <g:layoutHead/>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
      </ul>
    <ul class="nav navbar-nav navbar-right">
	<sec:ifAllGranted roles="ROLE_ADMIN"><li><g:link controller="admin">Admin</g:link></li></sec:ifAllGranted>
	  <li class="dropdown">
	    <a class="dropdown-toggle"  data-toggle="dropdown">
	    	<span class="glyphicon glyphicon-user"></span> User<span class="caret"></span>
	    </a>
	    <ul class="dropdown-menu" style="min-width:100px">
	      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
	      <li><g:remoteLink class="logout" controller="logout" method="post" asynchronous="false" onSuccess="location.reload()"><span class="glyphicon glyphicon-log-out"></span> Logout</g:remoteLink></li>
	    </ul>
	  </li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="#">Project</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
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

