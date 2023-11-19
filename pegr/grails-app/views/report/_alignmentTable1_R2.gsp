<div class="table-responsive">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Read Count (R2)</th>
                <th class="text-right">Adapter Dimer Count (R2)</th>
                <th class="text-right">Mapped Read Count (R2)</th>
                <th class="text-right">Uniquely Mapped Count (R2)</th>
                <th class="text-right">Deduplicated Count (R2)</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td rowspan="${Math.max(1, sample.alignmentCount)}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link> ${sample.naturalId}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Run Name ${experiment?.runName}) </g:link></td>    
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td class="text-right"><g:formatNumber number="${experiment.totalReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${experiment.adapterDimerCountR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.mappedReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReadsR2}" format="###,###,###" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>              
        </tbody>
      </table>
</div>
