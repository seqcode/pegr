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
            <g:each in="${alignmentList}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${alignment.sequencingExperiment?.sequenceRun?.id}">${alignment.sequencingExperiment?.sequenceRun?.id} (Old ${alignment.sequencingExperiment?.sequenceRun?.runNum}) </g:link></td>
                    <td>${alignment.genome}</td>
                    <td class="text-right">100%</td>
                    <td class="text-right"><g:formatNumber number="${alignment.mappedReads?.div(alignment.sequencingExperiment?.totalReads)}" format="##.#%" /></td>
                    <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReads?.div(alignment.sequencingExperiment?.totalReads)}" format="##.#%" /></td>
                    <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReads?.div(alignment.sequencingExperiment?.totalReads)}" format="##.#%" /></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
