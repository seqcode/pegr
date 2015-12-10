<html>
<head>
    <title>Sign into Pegr</title>
  <asset:javascript src="application.js"/>
  <asset:stylesheet href="application.css"/>
</head>
<body>
	<div class="container-fluid text-center">
	  	<h1>Platform for Eukaryotic Gene Regulation</h1>
	</div>

	<div class="col-md-offset-4 col-md-4">
	<g:form uri="/j_spring_security_check" method="POST" class="form-horizontal" role="form">
		<div class="form-group">
			<label class="control-label col-sm-2 for="j_username">Username </label> 
			<div class="col-sm-10">
				<g:textField class="form-control" name="j_username" value="${username}" placeholder="Enter Username"/>
			</div> 
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2 for="j_password">Password </label>
			<div class="col-sm-10">
				<g:passwordField class="form-control" name="j_password" placeholder="Enter Password"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label><g:checkBox name="_spring_security_remember_me" value="true"/> Remember me</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<g:submitButton name="signIn" value="Sign in"/>
			</div>
		</div>
	</g:form>
	</div>

</body>