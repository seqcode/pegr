<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th>Avg. Insert Size</th>
                <th>Mapped Read Count</th>
                <th>Uniquely Mapped Read Count</th>
                <th>Deduplicated Read Count</th>
                <th>Bam File</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${alignmentList}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${alignment.sequencingExperiment?.sequenceRun?.id}">${alignment.sequencingExperiment?.sequenceRun?.id} (Old ${alignment.sequencingExperiment?.sequenceRun?.runNum}) </g:link></td>
                    <td>${alignment.genome}</td>
                    <td>${alignment.avgInsertSize}</td>
                    <td>${alignment.mappedReadCount}</td>
                    <td>${alignment.uniqueMappedReadCount}</td>
                    <td>${alignment.dedupReadCount}</td>
                    <td>${alignment.bamFilePath}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="7"></td>
            </tr>
        </tbody>
      </table>
</div>