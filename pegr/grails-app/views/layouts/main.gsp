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
                    <li><a href="#">Home</a></li>
                    <li id="nav-projects"><g:link controller="project">My Projects</g:link></li>
                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
                        <li id="nav-inventory"><g:link controller="item">Inventory</g:link></li>
                        <li id="nav-protocols"><g:link controller="protocol">Protocols</g:link></li>
                        <li id="nav-experiments"><g:link controller="protocolInstanceBag" action="processingBags">Experiments</g:link></li>
                        <li id="nav-bioinformatics"><g:link controller="bioinformatics">Bioinfomatics</g:link></li>
                        <li id="nav-analysis"><g:link controller="report" action="all">Analysis</g:link></li>
                    </sec:ifAnyGranted>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                    <a href="#" class="dropdown-toggle"  data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span>${sec.username()}<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
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