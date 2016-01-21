<g:applyLayout name="base">
<!DOCTYPE html>
<html lang="en">
<head>
  <g:layoutHead/>
</head>
<body  onhashchange="${pageProperty(name:'body.onhashchange')}">	
	<nav class="navbar">
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
	        <li id="nav-projects"><g:link controller="project">My Projects</g:link></li>
  	        <li id="nav-cell-culture"><g:link controller="cellCulture" action="processingBags">Bench</g:link></li>
	       	<sec:ifAllGranted roles="ROLE_ADMIN"><li id="nav-admin"><g:link controller="admin">Admin</g:link></li></sec:ifAllGranted>
	      </ul>
	    <ul class="nav navbar-nav navbar-right">
		  <li><a href="#"><span class="glyphicon glyphicon-envelope"></span> Message</a></li>
		  <li class="dropdown">
		    <a class="dropdown-toggle"  data-toggle="dropdown">
		    	<span class="glyphicon glyphicon-user"></span>${sec.username()}<span class="caret"></span>
		    </a>
		    <ul class="dropdown-menu" style="min-width:100px">
                <li><g:link controller="user" action="profile"><span class="glyphicon glyphicon-user"></span> Profile</g:link></li>
		      <li><g:remoteLink class="logout" controller="logout" method="post" asynchronous="false" onSuccess="location.reload()"><span class="glyphicon glyphicon-log-out"></span> Logout</g:remoteLink></li>
		    </ul>
		  </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<div class="container-fluid text-left" id="main-content">
	<g:layoutBody/>
	</div>	
</body>
</html>
</g:applyLayout>