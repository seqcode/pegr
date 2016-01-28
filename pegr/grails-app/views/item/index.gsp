<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<ul class="nav nav-tabs">
    <li><g:link controller="protocolInstanceBag" action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link></li>
    <li class="active"><a href="#">Search</a></li>
    <li><g:link action="list">List</g:link></li>   
</ul>
    <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form class="fields" role="form" action="preview" >
        <g:render template="/item/search"></g:render>
        <g:submitButton class="btn btn-primary" name="search" value="Search Or Create"/>
    </g:form>    
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>