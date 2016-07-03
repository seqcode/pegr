<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="col-sm-10 text-left">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <g:sortableColumn property="fullName" title="Name" params="[affiliationId: affiliationId, roleId: roleId, isEnabled: isEnabled]"></g:sortableColumn>
                                <g:sortableColumn property="username" title="Username" params="[affiliationId: affiliationId, roleId: roleId, isEnabled: isEnabled]"></g:sortableColumn>
                                <th>Affiliation</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${users}">
                                <tr>
                                    <td>${it.fullName}</td>
                                    <td>${it.username}</td>
                                    <td>${it.affiliation}</td>
                                    <td>${it.authorities.join(", ")}</td>
                                    <td>
                                        <g:if test="${it.enabled}">
                                            <span class="label label-primary">Active</span>
                                        </g:if>
                                        <g:else>
                                            <span class="label label-default">Inactive</span>
                                        </g:else>
                                    </td>
                                    <td><g:link action="edit" params="[userId:it.id]">Edit</g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <g:paginate next="Next" prev="Prev" controller="userAdmin" action="index" params="[affiliationId: affiliationId, roleId: roleId, isEnabled: isEnabled]" max="25" total="${totalCount ?: 0}" />
                    </div>
                </div>
            </div>
            <div class="col-sm-2 sidenav filter text-center">
                <h5 id="all"><g:link controller="userAdmin" action="index">All</g:link></h5>
                <h4>Role</h4>
                <ul>
                    <g:each var="role" in="${pegr.Role.list()}">
                        <li id="role-${role?.id}"><g:link controller="userAdmin" action="index" params="[roleId: role.id]">${role.authority}</g:link></li>
                    </g:each>
                </ul>
                <h4>Affiliation</h4>
                <ul>
                    <g:each in="${affiliations}" var="aff">
                        <li id="affiliation-${aff?.id}"><g:link controller="userAdmin" action="index" params="[affiliationId: aff?.id]">${aff?.name}</g:link></li>
                    </g:each>
                </ul>
                <h4>Status</h4>
                <ul>
                    <li id="active"><g:link controller="userAdmin" action="index" params="[isEnabled: true]">Active</g:link></li>
                    <li id="inactive"><g:link controller="userAdmin" action="index" params="[isEnabled: false]">Inactive</g:link></li>
                </ul>
            </div>
            <br/>
        </div>
        <script>
            <g:if test="${affiliationId != null}">
                $("#affiliation-${affiliationId}").addClass("active")
            </g:if>
            <g:elseif test="${roleId != null}">
                $("#role-${roleId}").addClass("active")
            </g:elseif>
            <g:elseif test="${isEnabled == 'true'}">
                $("#active").addClass("active")
            </g:elseif>
            <g:elseif test="${isEnabled == 'false'}">
                $("#inactive").addClass("active")
            </g:elseif>
            <g:else>
                $("#all").addClass("active")
            </g:else>
        </script>
    </body>
</html>