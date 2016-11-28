<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
        <style>
            .well form {
                padding-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="col-sm-10 text-left">
                <div class="well">
                    <g:form action="search">
                        <label>Username</label>
                        <input name="username">
                        <label>or Email</label>
                        <input name="email">
                        <g:submitButton name="submit" value="Search" class="edit"></g:submitButton>
                    </g:form>                    
                    <g:form action="createUser">
                        <label>Email</label>
                        <input id="email" name="email">
                        <label>Groups</label>
                        <g:select id="groupIds" name="groupIds" from="${pegr.RoleGroup.list()}" optionKey="id" multiple="multiple" style="width:300px"></g:select>
                        <input type="checkbox" name="sendEmail" checked>
                        <label>Send Email</label>
                        <g:submitButton name="save" value="Add User" class="edit submit"></g:submitButton>
                    </g:form>
                </div>
                <g:hasErrors>
                    <div class="errors">
                        <g:renderErrors bean="${cmd}" as="list"/>
                    </div>
                </g:hasErrors>
                <g:if test="${request.message}">
                    <div class="alert alert-info">${request.message}</div>
                </g:if>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <g:sortableColumn property="fullName" title="Name" params="[affiliationId: affiliationId, groupId: groupId, isEnabled: isEnabled]"></g:sortableColumn>
                                <g:sortableColumn property="username" title="Username" params="[affiliationId: affiliationId, groupId: groupId, isEnabled: isEnabled]"></g:sortableColumn>
                                <th>Affiliation</th>
                                <th>Group</th>
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
                                    <td>${it.authorities*.name.join(", ")}</td>
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
                        <g:paginate next="Next" prev="Prev" controller="userAdmin" action="index" params="[affiliationId: affiliationId, groupId: groupId, isEnabled: isEnabled]" max="25" total="${totalCount ?: 0}" />
                    </div>
                </div>
            </div>
            <div class="col-sm-2 sidenav filter text-center">
                <h5 id="all"><g:link controller="userAdmin" action="index">All</g:link></h5>
                <h4>Group</h4>
                <ul>
                    <g:each var="group" in="${pegr.RoleGroup.list()}">
                        <li id="group-${group?.id}"><g:link controller="userAdmin" action="index" params="[groupId:group.id]">${group.name}</g:link></li>
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
            $("select").select2();
            $(".confirm").confirm();
            <g:if test="${affiliationId != null}">
                $("#affiliation-${affiliationId}").addClass("active")
            </g:if>
            <g:elseif test="${groupId != null}">
                $("#group-${groupId}").addClass("active")
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
            $(".submit").on("click", function() {
                $(this).prop("disabled", true); 
                $(this).closest("form").submit();
            });
        </script>
    </body>
</html>