<html>
    <head>
        <title>PEGR Admin - User</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
        <meta name="layout" content="admin"/>
        <style>
            .well form {
                padding-bottom: 10px;
            }
        </style>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <ul class="nav nav-pills">
                <li><g:link action="exportCsv">Export CSV</g:link></li>
		    </ul>
            <div class="col-sm-10 text-left">
                <g:hasErrors>
                    <div class="errors">
                        <g:renderErrors bean="${cmd}" as="list"/>
                    </div>
                </g:hasErrors>
                <g:if test="${request.message}">
                    <div class="alert alert-info">${request.message}</div>
                </g:if>
                <g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
                </g:if>
                <div class="well">
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
                <div style="margin-bottom: 12px">                    
                    <button data-toggle="collapse" data-target="#merge" aria-expanded="false" class="btn btn-default">Merge Users <span class="expand_caret caret"></span></button>
                    <div id="merge" class="collapse well">
                        <p>Please provide usernames below. Multiple usernames can be provided in the "From" field and should be delimited by comma ",".</p>
                        <g:form action="mergeUsers">
                        <div class="form-group">
                            <label>From</label>
                            <input name="fromUsernamesStr" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>To</label>
                            <input name="toUsername" class="form-control">
                        </div>
                            <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                        </g:form>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Affiliation</th>
                            <th>Group</th>
                            <th>Status</th>
                            <th>Edit</th>
                            <th>Reset password</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${users}">
                            <tr>
                                <td>${it.fullName}</td>
                                <td>${it.username}</td>
                                <td>${it.email}</td>
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
                                <td><g:link action="updateUserPassword" params="[userId:it.id]">Reset password</g:link></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>

            <div class="col-sm-2 sidenav filter text-center">
                <h5 id="all"><g:link controller="userAdmin" action="index">All</g:link></h5>
                <g:form action="index">
                <h4>Group</h4>
                  <table>
                    <g:each var="group" in="${pegr.RoleGroup.list()}">
                        <tr>
                          <td>
                            ${group.name}
                          </td>
                          <td>
                            <input type="checkbox" id="group" name="groupLoad" value="${group.name}" <g:if test="${selectedGroupList.contains(group.name)}">checked</g:if>>
                          </td>
                        </tr>
                    </g:each>
                  </table>
                <h4>Affiliation</h4>
                  <table>
                    <g:each in="${affiliations}" var="aff">
                      <g:if test="${aff?.name}">
                        <tr>
                          <td>
                            ${aff?.name}
                          </td>
                          <td>
                            <input type="checkbox" id="affiliation" name="affiliateLoad" value="${aff?.name}" <g:if test="${selectedAffiliateList.contains(aff.name)}">checked</g:if>>
                          </td>
                        </tr>
                      </g:if>
                    </g:each>
                  </table>
                <h4>Status</h4>
                <table>
                    <tr>
                      <td>
                        Active
                      </td>
                      <td>
                        <input type="checkbox" id="activity" name="activityLoad" value="Active" <g:if test="${selectedActivityList.contains('Active')}">checked</g:if>>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        Inactive
                      </td>
                      <td>
                        <input type="checkbox" id="activity" name="activityLoad" value="Inactive" <g:if test="${selectedActivityList.contains('Inactive')}">checked</g:if>>
                      </td>
                    </tr>
                </table>
                <g:submitButton name="submit" value="Filter"  class="btn btn-warning btn-lg">></g:submitButton>
                <div class="spacer"></div>
                </g:form>
            </div>
            <br/>
        </div>
        <script>
          $(document).ready(function() {
              $('#example').DataTable({
                scrollY: '50vh',
                scrollCollapse: true,
                paging: true
              });
          });
        </script>
    </body>
</html>
