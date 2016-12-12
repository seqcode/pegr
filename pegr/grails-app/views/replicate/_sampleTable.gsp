<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Description</th>
                <th>Strain</th>
                <th>Antibody</th>
                <th>Target</th>
                <th>Assay</th>
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
                    <td><g:if test="${authorized}"><g:link action="removeSample" params="[setId: set.id, sampleId:sample.id]" class="confirm"><span class="glyphicon glyphicon-remove"></span></g:link></g:if></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="8"></td>
            </tr>
        </tbody>
      </table>
</div>