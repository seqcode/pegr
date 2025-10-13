<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Description</th>
                <th>Celltype/Strain</th>
                <th>Antibody</th>
                <th>Target</th>
                <th>Assay</th>
                <th>Genome Build</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}" target="_blank">${sample.id}</g:link></td>    
                    <td>${sample.naturalId}</td>
                    <td>${sample.cellSource} </td>
                    <td>${sample.antibody}</td>
                    <td>${sample.target}</td>
                    <td>${sample.assay}</td>
                    <td>${sample.requestedGenomes}</td>
                    <td><span class="sample-status-${sample.status}">${sample.status}</span></td>
                    <td><g:if test="${sampleEditAuth && removable}"><g:link action="removeSample" params="[sampleId:sample.id, projectId:project?.id]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link></g:if></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="8"></td>
            </tr>
        </tbody>
      </table>
</div>
