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
                    <td rowspan="${Math.max(1, sample.experiments.size())}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link></td>    
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:link controller="sequenceRun" action="show" id="${experiment.runId}">${experiment.runId} (Old ${experiment.oldRunNum}) </g:link></td>
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td class="text-right">100%</td>
                            <td class="text-right"><g:formatNumber number="${alignment.mappedReadPct}" format="##.#%" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct}" format="##.#%" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.deduplicatedPct}" format="##.#%" /></td>
                            </tr>
                        </g:each>                    
                    </g:each>                    
            </g:each>              
            <tr>
                <td colspan="7"></td>
            </tr>
        </tbody>
      </table>
</div>
