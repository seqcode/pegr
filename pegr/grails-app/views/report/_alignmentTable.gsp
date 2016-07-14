<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Read Count</th>
                <th class="text-right">Mapped Read Count</th>
                <th class="text-right">Uniquely Mapped Count</th>
                <th class="text-right">Deduplicated Count</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Old ${experiment?.oldRunNum}) </g:link></td>
                    <td>${alignment.genome}</td>
                    <td class="text-right"><g:formatNumber number="${alignment.sequencingExperiment?.totalReads}" format="###,###,###" /></td>
                    <td class="text-right"><g:formatNumber number="${alignment.mappedReads}" format="###,###,###" /></td>
                    <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReads}" format="###,###,###" /></td>
                    <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReads}" format="###,###,###" /></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
