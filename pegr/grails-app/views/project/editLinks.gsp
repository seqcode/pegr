<html>
<head>
    <title>My Projects</title> 
    <meta name="layout" content="main"/>
    <style>
        .dropdown-menu {
            background-color: white;
        }
    </style>
</head>
<body>
  <h3>Project: ${project.name} </h3>
  <h4>Linkouts <button id="add-row" class="btn btn-primary">Add</button></h4>  
  <g:form action="updateLinks" method="post">
  <g:hiddenField name="projectId" value="${project.id}"></g:hiddenField>
  <g:hiddenField id="links" name="links"></g:hiddenField>
  <table id="link-table">
    <thead>
      <th>Name</th>
      <th>Hyperlink</th>
      <th></th>
    </thead>
    <tbody>
      <g:each in="${links}" var="link">
      <tr>
        <td><input class="name" name="name" value="${link.name}"></td>
        <td><input class="url" name="url" value="${link.url}"></td>
        <td><a class="delete-row" href="#">X</a></td>
      </tr>
      </g:each>
    </tbody>
  </table>
  <div>
    <g:submitButton name="save" class="btn btn-primary" value="Save"></g:submitButton>
    <g:link action="show" params="[id:project.id]" class="btn btn-default">Cancel</g:link>
  </div>
  </g:form>
  <script type="application/javascript">
    $("#add-row").click(function(){
      $("tbody").append('<tr><td><input class="name" name="name"></td><td><input class="url" name="url"></td><td><a class="delete-row" href="#">X</a></td></tr>');
    });
      
    $("form").submit(function(event) {
        // get json
        var links = [];
        $("tbody tr").each(function(i){
            links.push({
                "name": $(this).find(".name").val(),
                "url": $(this).find(".url").val()
            });
        });
        $("#links").val(JSON.stringify(links));
    });
      
    $("tbody").on('click', '.delete-row', function(){
        $(this).closest('tr').remove();
    });
  </script>
</body>
</html>