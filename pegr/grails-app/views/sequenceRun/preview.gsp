<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h4>Add Sample</h4>
    <g:render template="/sample/details" model="['sample': sample]"></g:render>
    <g:if test="${bag}">
        <p>This sample is associated with bag <g:link controller="ProtocolInstanceBag" action="showBag" id="${bag.id}" target="_blank">${bag.name}</g:link></p>
    </g:if>
    <g:link action="addSample" params="[sampleId: sample.id, runId: runId]" class="btn btn-primary">Add Sample Only</g:link>
    <g:if test="${bag}">
        <g:link action="addBag" params="[bagId: bag.id, runId: runId]" class="btn btn-primary">Add Entire Bag</g:link>
    </g:if>
    <g:link action="searchSample" iparams="[runId: runId]" class="btn btn-primary">Cancel</g:link>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>