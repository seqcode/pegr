<g:applyLayout name="base">
<!DOCTYPE html>
<html lang="en">
<head>
  <g:layoutHead/>
</head>
<body onhashchange="${pageProperty(name:'body.onhashchange')}">	
	<nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
              </button>
              <a class="navbar-brand"><img src="/pegr/assets/PEGR_Logo.png" height="100%"/></a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li id="nav-projects"><g:link controller="project">Projects</g:link></li>
                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
                        <li id="nav-inventory"><g:link controller="item">Inventory</g:link></li>
                        <li id="nav-protocols"><g:link controller="protocol" action="labProtocols">Protocols</g:link></li>
                        <li id="nav-experiments"><g:link controller="protocolInstanceBag" action="list">Experiments</g:link></li>
                        <li id="nav-analysis"><g:link controller="sequenceRun" action="index">Sequencing Reports</g:link></li>
                        <li id="nav-bioinformatics"><g:link controller="bioinformatics">Bioinformatics</g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li id="nav-admin"><g:link controller="admin" action="index">Admin</g:link></li>
                    </sec:ifAnyGranted>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                    <a href="#" class="dropdown-toggle"  data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span> ${sec.username()}<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" style="background-color:#2c5986">
                        <li><g:link controller="user" action="profile"><span class="glyphicon glyphicon-user"></span> Profile</g:link></li>
                        <li><g:link controller="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</g:link></li>
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