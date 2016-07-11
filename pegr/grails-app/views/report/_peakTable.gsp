<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Target</th>
                <th>Peak Calling (parameters)</th>
                <th>All Peaks</th>
                <th>Singletons</th>
                <th>Peak-pairs (parameters)</th>
                <th># of Peak-pairs</th>
                <th>Non-paired peaks</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${alignments}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td>${alignment.sequencingExperiment?.sample?.target}</td>
                    <td></td>
                    <td>${alignment.analysis.numberOfPeaks}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
