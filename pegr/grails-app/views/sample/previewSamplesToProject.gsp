<html>
<head>
  <title>PEGR - Samples</title> 
  <meta name="layout" content="main"/>
</head>
<body>
  <div class="container-fluid">
    <h3>Add Samples to Project</h3>
    <g:form action="saveSamplesToProject">
      <strong>Samples</strong>
      <ul>
        <g:each in="${samples}" var="sample">
        <li>${sample.id} ${sample.naturalId}</li>
        </g:each>
      </ul>
      <div class="form-group">
        <label>Project</label>
        <g:select name="projectId" from="${pegr.Project.list().sort {it.name}}" optionKey="id" class="select form-control"/>
      </div>
      <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
      <g:link action="all" class="btn btn-default">Cancel</g:link>
    </g:form>  
  </div>
  <script>
    $(function(){
      $("#nav-samples").addClass("active");
      $(".select").select2({
        width: '100%',
        tags: false
      });
    });
  </script>
</body>
        



