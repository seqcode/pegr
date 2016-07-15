<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Target</th>
                <th>Antibody</th>
                <th>Celltype/Strain</th>
                <th>Mutation</th>
                <th>Growth Media</th>
                <th>Treatments</th>
                <th>Assay</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link></td>    
                    <td>${sample.target}</td>
                    <td>${sample.antibody}</td>
                    <td>${sample.strain} </td>
                    <td>${sample.geneticModification}</td>
                    <td>${sample.growthMedia}</td>
                    <td>${sample.treatments}</td>
                    <td>${sample.assay}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="8"></td>
            </tr>
        </tbody>
      </table>
</div>