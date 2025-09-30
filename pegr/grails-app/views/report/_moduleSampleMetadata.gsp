<h3>Samples</h3>
<p>The number of Samples: ${sampleDTOs?.size()}</p>
<div class="table-responsive">
    <table class="table table-bordered">
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
                <th>Status</th>
                <th>Note</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link> ${sample.naturalId}</td>    
                    <td>${sample.target}</td>
                    <td>${sample.antibody}</td>
                    <td>${sample.strain} </td>
                    <td>${sample.geneticModification}</td>
                    <td>${sample.growthMedia}</td>
                    <td>${sample.treatments}</td>
                    <td>${sample.assay}</td>
                    <td>${sample.status}</td>
                    <td>${sample.note}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>

