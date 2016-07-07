<html>
<head>
  <title>Samples</title> 
  <meta name="layout" content="main"/>
</head>
<body>
<div>
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
</div>

<g:render template="table" model="['sampleList':sampleList]"></g:render>    

<script>
$("#nav-metadata").addClass("active");
</script>
</body>
</html>
