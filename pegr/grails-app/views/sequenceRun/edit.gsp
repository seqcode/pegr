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
    <div>
        <h3>Summary <g:link action="editInfo" params="[runId:run.id]"><span class="edit">Edit</span></g:link></h3>
        <g:render template="summaryDetails"></g:render>
    </div>
    <h3>Samples</h3>
    <g:if test="${run?.status==pegr.RunStatus.PREP}">
        Add <button type="button" class="edit" data-toggle="modal" data-target="#add-samples-by-id">by sample ID</button>
        <g:if test="${run?.poolItem == null}">
            or <g:link action="searchPool" params="['runId':run.id]" class="edit">by Master Pool</g:link>
        </g:if>
        <g:link action="removePool" params="['runId':run.id]" class="edit confirm pull-right">Remove All</g:link>
        
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
                                <label>Sample IDs </label>
                                <input name="sampleIds" type="text" class="form-control">
                                <br/>
                                <p>e.g. 1-10, 11, 15</p>
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
    
    <g:form action="updateGenomes" role="form" method="post">
        <g:hiddenField name="runId" value="${run.id}"></g:hiddenField>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Sample ID</th>
                    <th>Strain</th>
                    <th>Antibody</th>
                    <th>Index</th>
                    <th>Genome Build <g:submitButton class="edit" name="save" value="Save"></g:submitButton></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${run.experiments}">
                    <tr>
                        <td><g:link controller="sample" action="show" id="${it.sample.id}">${it.sample?.id}</g:link></td>
                        <td>${it.sample?.cellSource?.strain}</td>
                        <td>${it.sample?.antibody}</td>
                        <td>${it.sample?.sequenceIndicesString}</td>
                        <td>
                            <g:hiddenField name="experimentId" value="${it.id}"></g:hiddenField>
                            <g:select multiple="multiple" name="genomes${it.id}" from="${pegr.Genome.list()}" optionKey="id" value="${it.genomes}" class="select2"></g:select>
                        </td>                        
                        <td>
                            <g:link action="removeExperiment" params="[experimentId:it.id, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link>
                        </td>
                    </tr>
                </g:each>              
                <tr>
                    <td colspan="6"></td>
                </tr>
            </tbody>
          </table>
    </g:form>
    <div class="message">
        Difference between sacCer and sacCer_cegr: sacCer is the UCSC version which uses Roman numerals; sacCer_cegr is the Pugh lab version which uses numbers. Pugh lab samples should normally use sacCer_cegr.
    </div>
    </div>
    <div class="row well text-center">
        <g:if test="${run.status == pegr.RunStatus.PREP}">
            <g:link action="previewRun" params="[runId: run.id]" class="btn btn-success">Submit</g:link>

        </g:if>
        <g:else>
            <span class="btn btn-default">Submitted</span>
        </g:else>
    </div>
    <script>
        $("#nav-bench").addClass("active");
        $(".confirm").confirm();
        $(".select2").select2();
     </script>
</div>
</body>
</html>