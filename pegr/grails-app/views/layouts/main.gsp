<!DOCTYPE html>
<html lang="en">
<head>
  <title><g:layoutTitle default="PEGR"/></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <asset:javascript src="application.js"/>
  <asset:stylesheet href="application.css"/>
  <g:layoutHead/>
</head>
<body>
	<div class="text-center">
		<h1>Platform for Eukaryotic Gene Regulation</h1>
	</div>
	<nav class="navbar" data-spy="affix" data-offset-top="400" id="affix-nav">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">PEGR</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li id="nav-metadata"><g:link controller="sample">Metadata</g:link></li>
	        <li id="nav-analysis"><a href="#">Analysis</a></li>
 	        <li id="nav-projects"><g:link controller="project">My Projects</g:link></li>
	       	<sec:ifAllGranted roles="ROLE_ADMIN"><li id="nav-admin"><g:link controller="admin">Admin</g:link></li></sec:ifAllGranted>
	      </ul>
	    <ul class="nav navbar-nav navbar-right">
		  <li><a href="#"><span class="glyphicon glyphicon-envelope"></span> Message</a></li>
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

	<div class="container-fluid text-left">
	<g:layoutBody/>
	</div>
	<footer class="container-fluid text-center">
	  <p>Contact</p>
	</footer>	
	
	<script>
		$('#affix-nav').affix({
			});
	</script>
</body>
</html>
