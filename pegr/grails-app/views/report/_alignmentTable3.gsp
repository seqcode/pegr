<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th>Adapter Dimer Count</th>
                <th>Average Insertion Size (PE)</th>
                <th>Standard Dev (PE)</th>
                <th>Genome Coverage</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${alignmentList}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${alignment.sequencingExperiment?.sequenceRun?.id}">${alignment.sequencingExperiment?.sequenceRun?.id} (Old ${alignment.sequencingExperiment?.sequenceRun?.runNum}) </g:link></td>
                    <td>${alignment.genome}</td>
                    <td><g:formatNumber number="${alignment.sequencingExperiment?.adapterDimerCount}" format="###,###,###" /></td>
                    <td></td>
                    <td></td>
                    <td>${alignment.genomeCoverage}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
