<html>
<head>
    <title>PEGR - Analysis Status</title> 
    <meta name="layout" content="analysis"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3><g:link controller="sequenceRun" action="show" id="${run.id}">Run ${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></g:link></h3>
    <g:each in="${runStatus}">
        <div class="pull-right"><span class="label label-success"> </span> Data received; <span class="label label-danger"> </span> No data. Hover to see the step's category.</div>
        <div>
            <table>
                <caption>Read Type: ${it.key} </caption>
                <thead>
                    <tr>
                        <th>Sample</th>
                        <th>Genome</th>
                        <th>Galaxy</th>
                        <th colspan="${it.value.steps.size()}">Status</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${it.value.sampleStatusList}" var="sample">
                    <tr>
                    <td rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                    <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                        <g:if test="${n>0}"><tr></g:if>
                            <td>${alignment.genome}</td>                            
                            <td><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                            <g:each in="${alignment.status}" var="status" status="j">
                                <td class="analysis-status">
                                    <g:if test="${status}"><span title="${it.value.steps[j][1]}" class="label label-success"> </span></g:if> 
                                    <g:else><span title="${it.value.steps[j][1]}" class="label label-danger"> </span></g:else>
                                </td>
                            </g:each>
                            <td><g:link controller="report" action="deleteAlignment" params="[alignmentId:alignment.alignmentId, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-trash"</g:link></td>
                        </tr>
                    </g:each>
                    <g:if test="${sample.alignmentStatusList.size()==0}">
                        </tr>
                    </g:if>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:each>
    
    <h3>Summary Reports <g:link action="createReports" params="[runId: run.id]" class="edit">Generate</g:link></h3>
    <ul>
        <g:each in="${reports}">
            <li><g:link controller="report" action="show" id="${it.id}">${it}</g:link></li>
        </g:each>
    </ul>
    <script>
        $(".confirm").confirm({text: "All data in this alignment will be deleted. Are you sure you want to delete this alignment?"});
        $(".nav-status").addClass("active");
    </script>
</body>
</html>