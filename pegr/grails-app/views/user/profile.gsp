<html>
<head>
    <title>Register</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2>Profile</h2>
    <div class="profile">
        <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>Basic Information </h3>        
        <div id="info">
            <ul>
                <li>Username: ${user?.username}</li>
                <li>Full name: ${user?.fullName}</li>
                <li>Email: ${user?.email}</li>
                <li>Phone: ${user?.phone}</li>
                <g:link action='editInfo' id="${user?.id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil">Edit</span></g:link>
            </ul>
        </div>
        <h3>Address</h3>
        <div id="profile-address">
            <ul>
                <g:if test="${user?.address}">
                <li>${user.address.line1}</li>
                <g:if test="${user.address.line2}">
                    <li>${user.address.line2}</li>
                </g:if>
                <li>${user.address.city}, ${user.address.state} ${user.address.postalCode}</li>
                <li>${user.address.country.toUpperCase()}</li>
                    <span>
                    <g:link action='editAddress' id="${user?.address?.id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil">Edit</span></g:link>
                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#deleteAddress"><span class="glyphicon glyphicon-trash">Delete</span></button>
<div id="deleteAddress" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <p>Are you sure that you want to delete this address?</p>
      </div>
      <div class="modal-footer">
          <g:link action='deleteAddress' id="${user.address.id}" class="btn btn-default">Yes, delete</g:link>
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
      </div>
    </div>
  </div>
</div>
            </g:if>
            <g:else>
                <g:link action='editAddress' class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil">Add</span></g:link>
            </g:else>
            </ul>
        </div>
        <h3>Password</h3>
        <ul>
        <g:link action='updatePassword' id="${user?.id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil">Change</span></g:link>
        </ul>
        <h3>API Key</h3>
        <g:if test="${user?.apiKey}">${user.apiKey}</g:if>
        <g:else><g:link controller="user" action="generateApiKey" class="btn btn-primary">Generate</g:link></g:else>
    </div>
</body>
</html>