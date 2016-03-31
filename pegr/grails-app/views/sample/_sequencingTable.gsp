<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Read Count</th>
                <th>Adapter Count</th>
                <th>Index Mismatch</th>
                <th>FASTQ File</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${experiment.numberReads}</td>
                <td>${experiment.adapterCount}</td>
                <td>${experiment.indexMismatch}</td>
                <td>${experiment.fastqFilePath}</td>
            </tr>            
        </tbody>
      </table>
</div>