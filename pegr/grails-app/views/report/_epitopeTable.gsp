<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>N-term TAG</th>
                <th>Target</th>
                <th>C-term TAG</th>
                <th>Detected Epitope tag</th>
                <th>Count</th>
                <th>Additional Epitope detected</th>
                <th>FastQC</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link></td>    
                    <td>${sample.target?.nTermTag}</td>
                    <td>${sample.target}</td>
                    <td>${sample.target?.cTermTag}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>READ1 | READ2</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
