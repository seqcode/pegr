<html>
<head>
    <title>Profile-Address</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2>Profile</h2>
    <div class="profile">
        <h3>Address </h3>   
        <g:if test="${request.message}">
			<div class="message" role="status">${request.message}</div>
        </g:if>
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${address}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="editAddress" method="POST" role="form" useToken="true">
            <div class="form-group ${hasErrors(bean: address, field: 'line1', 'error')}">
                <label for="line1">Street Address</label>
                <g:textField class="form-control" id="line1" name="line1" value="${address?.line1}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: address, field: 'line2', 'error')}">
                <g:textField class="form-control" id="line2" name="line2" value="${address?.line2}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: address, field: 'city', 'error')}">
                <label for="city">City</label>
                <g:textField class="form-control" id="city" name="city" value="${address?.city}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: address, field: 'state', 'error')}">
                <label for="state">State</label>
                <g:textField class="form-control" id="state" name="state" value="${address?.state}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: address, field: 'postalCode', 'error')}">
                <label for="postalCode">Postal Code</label>
                <g:textField class="form-control" id="postalCode" name="postalCode" value="${address?.postalCode}"></g:textField>
            </div>
            <div class="form-group ${hasErrors(bean: address, field: 'country', 'error')}">
                <label for="country">Country</label>
                <g:countrySelect name="country" value="${address?.country}"
                 noSelection="['':'-Choose-']"/>
            </div>
            <span>
                <g:submitButton name="update" value="Update" class="btn btn-primary"/>                   <g:link action='profile' class="btn btn-primary ">Cancel</g:link>
            </span>
        </g:form>
    </div>
</body>
</html>