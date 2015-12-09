<g:form uri="/j_spring_security_check" method="POST">
	<fieldset class="form">
		<div class="fieldcontain required">
			<label for="j_username">Username</label>
			<g:textField name="j_username" value="${username}"/>
		</div> 
		<div class="fieldcontain required">
			<label for="j_password">Password</label>
			<g:passwordField name="j_password"/>
		</div>
	</fieldset>
	<fieldset class="buttons">
		<g:submitButton name="signIn" value="Sign in"/>
	</fieldset>
</g:form>