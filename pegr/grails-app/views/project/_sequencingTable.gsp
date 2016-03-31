<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Read Count</th>
                <th>Adapter Count</th>
                <th>Index Mismatch</th>
                <th>FASTQ File</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${experimentList}" var="experiment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${experiment.sample?.id}">${experiment.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${experiment.sequenceRun?.id}">${experiment.sequenceRun?.id} (Old ${experiment.sequenceRun?.runNum})</g:link></td>
                    <td>${experiment.numberReads}</td>
                    <td>${experiment.adapterCount}</td>
                    <td>${experiment.indexMismatch}</td>
                    <td>${experiment.fastqFilePath}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="7"></td>
            </tr>
        </tbody>
      </table>
</div>