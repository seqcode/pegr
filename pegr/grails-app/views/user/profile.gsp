<html>
<head>
    <title>Register</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2>Profile</h2>
    <div id="profile" class="content">
        <h3>Basic Information</h3>
        
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${user}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form class="form-horizontal" action="save" method="POST" role="form">   
            
            
            <div class="form-group ${hasErrors(bean: user, field: 'fullname', 'error')}">            
                <label class="control-label col-sm-3" for="fullname">Full name</label>      
                <div class="col-sm-9">
                    <g:textField class="form-control"  required="required" id="fullname" name="fullname" value="${user?.fullName}"></g:textField>
                </div>
            </div>
            <div class="form-group ${hasErrors(bean: user, field: 'email', 'error')}">
                <label class="control-label col-sm-3"  for="email">Email</label>
                <div class="col-sm-9">
                    <g:textField type="email" class="form-control" id="email" name="email" value="${user?.email}"></g:textField>
                </div>
            </div>
            <div class="form-group ${hasErrors(bean: user, field: 'phone', 'error')}">
                <label class="control-label col-sm-3" for="phone">Phone</label>
                <div class="col-sm-9">
                    <g:textField type="tel" class="form-control" id="phone" name="phone"></g:textField>
                </div>
            </div>
        </g:form>
        <h3>Password</h3>
        <h3>Affiliation</h3>
        
        <h3>Address</h3>
    

        
    </div>
</body>
</html>