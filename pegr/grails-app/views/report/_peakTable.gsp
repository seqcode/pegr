<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Target</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Peak Calling (parameters)</th>
                <th class="text-right">All Peaks</th>
                <th class="text-right">Singletons</th>
                <th class="text-right">Peak-pairs (parameters)</th>
                <th class="text-right"># of Peak-pairs</th>
                <th class="text-right">Non-paired peaks</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td rowspan="${Math.max(1, sample.experiments.size())}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link></td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}">${sample.target}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Old ${experiment?.oldRunNum}) </g:link></td>    
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${alignment.genome}</td>
                            <td class="text-right">${alignment.peakCallingParam}</td>
                            <td class="text-right"><g:formatNumber number="${alignment.peaks}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.singletons}" format="###,###,###" /></td>
                            <td class="text-right">${alignment.peakPairsParam}</td>
                            <td class="text-right"><g:formatNumber number="${alignment.peakPairs}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.nonPairedPeaks}" format="###,###,###" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>      
            <tr>
                <td colspan="10"></td>
            </tr>
        </tbody>
      </table>
</div>
