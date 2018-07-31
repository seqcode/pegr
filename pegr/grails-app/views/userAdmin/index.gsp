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

                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Affiliation</th>
                            <th>Group</th>
                            <th>Status</th>
                            <th>Edit</th>
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
            </div>

            <div class="col-sm-2 sidenav filter text-center">
                <h5 id="all"><g:link controller="userAdmin" action="index">All</g:link></h5>
                <h4>Group</h4>
                  <table>
                    <g:each var="group" in="${pegr.RoleGroup.list()}">
                        <tr>
                          <td>
                            ${group.name}
                          </td>
                          <td>
                            <input type="checkbox" id="group" value="${group.name}">
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
                            <input type="checkbox" id="affiliation" value="${aff?.name}">
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
                        <input type="checkbox" id="activity" value="Active">
                      </td>
                    </tr>
                    <tr>
                      <td>
                        Inactive
                      </td>
                      <td>
                        <input type="checkbox" id="activity" value="Inactive">
                      </td>
                    </tr>
                </table>
                <button type="button" id="filter" class="btn btn-warning btn-lg"><i class="fab fa-empire"></i>  Filter Table   <i class="fab fa-empire"></i></button>
                <div class="spacer"></div>
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


          // User Filter JQuery and AJAX | git:pjchaffin
          $('#filter').click(function(){

              // initialize 3 lists for groups, activities, and affiliations
              let groups = [];
              let activities = [];
              let affiliations = [];

              // loop through all group id checkboxes and logic for checked, push to list if so
              $('input[id="group"]').each(function(){
                if (this.checked){
                  groups.push(this.value);
                 }
              });

              // loop through all activity id checkboxes and logic for checked, push to list if so
              $('input[id="activity"]').each(function(){
                if (this.checked){
                  activities.push(this.value);
                 }
              });

              // loop through all affiliation id checkboxes and logic for checked, push to list if so
              $('input[id="affiliation"]').each(function(){
                if (this.checked){
                  affiliations.push(this.value);
                 }
              });

              // action redirect pushing these lists to page logic
              // redirect(controller: "userAdmin", action: "index", params: [groupLoad: groups, activityLoad: activities, affiliateLoad: affliatiations]);
              $.ajax({
                url:"${createLink(controller: 'UserAdmin', action: 'index')}",
                type:"GET",
                data: {"groupLoad": JSON.stringify(groups), "affiliateLoad": JSON.stringify(affiliations), "activityLoad": JSON.stringify(activities)},
                success : function(result){
                  $("html").html(result);
                },
                error : function(e) {
                  console.info("Error" + e);
                }
              });
          });

        </script>
    </body>
</html>
