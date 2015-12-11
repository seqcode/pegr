<<g:applyLayout name="main">
<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR-Admin <g:layoutTitle/></title>
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
        <li class="active"><a href="#">Admin</a></li>
        <li><g:link controller='dashboard' action='index'>Site</g:link></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
		  <li class="dropdown">
		    <a class="dropdown-toggle"  data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User
		    <span class="caret"></span></a>
		    <ul class="dropdown-menu" style="min-width:100px">
		      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
		      <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		    </ul>
		  </li>
      </ul>
    </div>
  </div>
</nav>
  
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