<div class="table-responsive">
    <table class="table  table-bordered">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Adapter Dimer Count</th>
                <th class="text-right">Average Insertion Size (PE)</th>
                <th class="text-right">Standard Dev (PE)</th>
                <th class="text-right">Genome Coverage</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td rowspan="${Math.max(1, sample.alignmentCount)}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link> ${sample.naturalId}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Run Name: ${experiment?.runName}) </g:link></td>    
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td class="text-right"><g:formatNumber number="${experiment.adapterDimerCount}" format="###,###,###" /></td>
                            <td class="text-right">${alignment.avgInsertSize}
                                <g:if test="${alignment.peHistogram}"><a href="${alignment.peHistogram}" target="_blank"><span class="glyphicon glyphicon-picture"></span></a></g:if></td>
                            <td class="text-right">${alignment.stdInsertSize}</td>
                            <td class="text-right"><g:formatNumber number="${alignment.genomeCoverage}" format="##.#%" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>                      
        </tbody>
      </table>
</div>
