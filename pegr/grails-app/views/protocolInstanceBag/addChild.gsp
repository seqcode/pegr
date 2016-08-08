<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div>
    <h4>Add Child for <g:link controller="item" action="show" id="${sample?.item?.id}" target="_blank">${sample?.item?.name}</g:link></h4>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form action="addChild" class="fields" role="form" method="POST" useToken="true">
        <g:hiddenField name="instanceId" value="${instanceId}"/>
        <g:hiddenField name="sampleId" value="${sample.id}"/>
        <g:hiddenField name="split" value="${split}"/>
        <g:render template="childForm"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link action="showInstance" id="${instanceId}" class="btn btn-default">Cancel</g:link>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>