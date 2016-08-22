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
        <h4>Read Type: ${it.key}</h4>
        <div>
            <table >
                <thead>
                    <tr>
                        <th>Sample</th>
                        <th>Genome</th>
                        <th>Galaxy</th>
                        <g:each in="${it.value.steps}" var="step"><th></th></g:each>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${it.value.sampleStatusList}" var="sample">
                    <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                        <tr>
                            <td><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                            <td>${alignment.genome}</td>                            
                            <td><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}">${alignment.historyId}</a></td>
                            <g:each in="${alignment.status}" var="status">
                                <td class="analysis-status" style="width:10px">
                                    <g:if test="${status}"><span class="label label-success"> </span></g:if> 
                                    <g:else><span class="label label-danger"> </span></g:else>
                                </td>
                            </g:each>
                        </tr>
                    </g:each>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:each>
</body>
<html