<html>
<head>
    <title>Profile-Basic Information</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2>Profile</h2>
    <div class="profile">
        <h3>Basic Information </h3>   
        <g:if test="${request.message}">
			<div class="message" role="status">${request.message}</div>
        </g:if>
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${user}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="editInfo" method="POST" role="form" useToken="true">
            <div class="form-group ${hasErrors(bean: user, field: 'fullName', 'error')}">
                <label for="fullName">Full name</label>
                <g:textField class="form-control" id="fullName" name="fullName" value="${user?.fullName}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: user, field: 'email', 'error')}">
                <label for="email">Email</label>
                <g:textField type="email" class="form-control" id="email" name="email" value="${user?.email}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: user, field: 'phone', 'error')}">
                <label for="phone">Phone</label>
                <g:textField type="tel" class="form-control" id="phone" name="phone"></g:textField>
            </div>
            <span>
                <g:submitButton name="update" value="Update" class="btn btn-primary"/> <g:link action='profile' class="btn btn-primary ">Cancel</g:link>
            </span>
        </g:form>
    </div>
</body>
</html>