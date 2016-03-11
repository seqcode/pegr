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
    <h2>Sequence Run #${run.runNum}  <small><span class="label label-default">${run.status}</span></small></h2>
    <div>
        <h3>Summary <g:link action="editInfo" id="${run.id}"><span class="edit">Edit</span></g:link></h3>
        <g:render template="summaryDetails"></g:render>
    </div>
    <h3>Samples <g:link action="searchSample" params="['runId':run.id]" class="edit">Add</g:link></h3>
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
                        <td>${it.sample?.sequenceIndicesDetailString}</td>
                        <td>
                            <g:hiddenField name="experimentId" value="${it.id}"></g:hiddenField>
                            <g:select multiple="multiple" name="genomes${it.id}" from="${pegr.Genome.list()}" optionKey="id" value="${it.genomes}" class="tokenize-sample tokenize"></g:select>
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
    </div>
    <div class="row well text-center">
        <g:if test="${run.status == pegr.RunStatus.PREP}">
            <g:link action="run" params="[runId: run.id]" class="btn btn-success confirm">Submit</g:link>
        </g:if>
        <g:else>
            <span class="btn btn-default">Submitted</span>
        </g:else>
    </div>
    <script>
        $("#nav-bench").addClass("active");
        $(".confirm").confirm();
        $(".tokenize").tokenize({newElements: false});
     </script>
</div>
</body>
</html>