<!DOCTYPE html>
<html lang="en">
<head>
  <title>PEGR</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <asset:javascript src="application.js"/>
  <asset:stylesheet href="application.css"/>
  <g:layoutHead/>
</head>
<body>
<div class="container-fluid text-center">
  	<h1>Platform for Eukaryotic Gene Regulation</h1>
</div>
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
        <li><g:link controller='dashboard' action='index'/>Site</g:link></li>
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
		<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
			<g:if test="${ c.getStaticPropertyValue('showInAdmin', Boolean)}">
				<p><g:link controller="${c.logicalPropertyName}">${c.logicalPropertyName}</g:link></p>
			</g:if>
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

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>
