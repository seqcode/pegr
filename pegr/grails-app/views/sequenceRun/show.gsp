<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Sequencing Run List</g:link>   
    <div id="message" >
        <g:if test="${flash.message}">
             <div class="message" role="status">
                ${flash.message}
            </div>
        </g:if>
    </div>
    <h2>Sequence Run #${run.id}  <small><g:if test="${run.runNum}">(Old No.${run.runNum})</g:if> <span class="label label-default">${run.status}</span></small></h2>
    <h3>Summary <g:if test="${run?.status!=pegr.RunStatus.COMPLETED}"> <g:link action="editInfo" params="[runId:run.id]"><span class="edit">Edit</span></g:link></g:if></h3>
    <g:render template="summaryDetails"></g:render>    
    <h3>Samples <g:if test="${run.status == pegr.RunStatus.PREP}"><g:link controller="sample" action="batchEdit" params="[runId: run.id]" class="edit" target="_blank">Edit</g:link></g:if> 
    <g:if test="${run?.status==pegr.RunStatus.PREP}">
        <button type="button" class="edit" data-toggle="modal" data-target="#add-samples-by-id">Add Sample</button>
        <g:if test="${run?.poolItem == null}">
            <g:link action="searchPool" params="['runId':run.id]" class="edit">Add Master Pool</g:link>
        </g:if>
        <g:else>
            <g:link action="removePool" params="['runId':run.id]" class="edit confirm">Remove Pool</g:link>
        </g:else>
        <div id="add-samples-by-id" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <g:form action="addSamplesById">
                        <div class="modal-header">
                            <h3 class="modal-title">Add Samples to Sequence Run #${run.id}</h3>
                        </div>
                        <div class="modal-body">
                            <g:hiddenField name="runId" value="${run.id}"></g:hiddenField>
                            <small class="form-group">
                                <g:render template="/sample/inputSampleIds"></g:render>
                            </small>
                        </div>
                        <div class="modal-footer">
                            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </g:if>
    </h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <th class="remove-sample"></th>
                <th>Sample ID</th>
                <th>Strain</th>
                <th>Antibody</th>
                <th>Index</th>
                <th>Index ID</th>
                <th>Genome Build</th>
                <th>Cohort</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${run.experiments}">
                <tr>
                    <td class="remove-sample">
                        <g:if test="${run?.status==pegr.RunStatus.PREP}">
                            <g:link action="removeExperiment" params="[experimentId:it.id, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                        </g:if>
                    </td>
                    <td><g:link controller="sample" action="show" id="${it.sample.id}">${it.sample?.id}</g:link></td>
                    <td>${it.sample?.cellSource?.strain}</td>
                    <td>${it.sample?.antibody}</td>
                    <td>${it.sample?.sequenceIndicesString}</td>
                    <td>${it.sample?.sequenceIndicesIdString}</td>
                    <td>${it.sample?.requestedGenomes}</td>
                    <td>${it.cohort}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="5"></td>
            </tr>
        </tbody>
    </table>
    <g:if test="${run?.experiments.size()}">
        <h3>Read and Index Positions <g:link action="editRead" params="[runId:run?.id]" class="edit">Edit</g:link></h3>
        <h5>Read Type: ${read?.readType?.name}</h5>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th>Start</th>
                    <th>End</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Read 1</td>
                    <td>${read?.rd1?.getAt(0)}</td>
                    <td>${read?.rd1?.getAt(1)}</td>
                </tr>
                <g:if test="${read?.containsKey('index')}">
                    <tr>
                        <td>Index </td>
                        <td>${read?.index?.getAt(0)}</td>
                        <td>${read?.index?.getAt(1)}</td>
                    </tr>
                </g:if>
                <g:else>
                    <tr>
                        <td>Index 1</td>
                        <td>${read?.index1?.getAt(0)}</td>
                        <td>${read?.index1?.getAt(1)}</td>
                    </tr>
                    <tr>
                        <td>Index 2</td>
                        <td>${read?.index2?.getAt(0)}</td>
                        <td>${read?.index2?.getAt(1)}</td>
                    </tr>
                </g:else>
                <g:if test="${read?.rd2}">                
                    <tr>
                        <td>Read 2</td>
                        <td>${read?.rd2?.getAt(0)}</td>
                        <td>${read?.rd2?.getAt(1)}</td>
                    </tr>
                </g:if>
            </tbody>
        </table>
    </g:if>
    <div class="row well text-center">
        <g:if test="${run.status == pegr.RunStatus.PREP}">
            <g:link action="previewRun" params="[runId: run.id]" class="btn btn-success">Submit</g:link>
        </g:if>
        <g:else>
            <span class="btn btn-default">Submitted</span>
        </g:else>
    </div>
    <script>
        $("#nav-experiments").addClass("active");
        $(".confirm").confirm();
     </script>
</div>
</body>
</html>