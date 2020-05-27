<div class="table-responsive">
    <table class="table table-bordered">
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
                    <td rowspan="${Math.max(1, sample.analysisWorkflowRunCount)}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link> ${sample.naturalId}</td>
                    <td rowspan="${Math.max(1, sample.analysisWorkflowRunCount)}">${sample.target}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td rowspan="${Math.max(1, experiment.analysisWorkflowRuns.size())}"><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Old ${experiment?.oldRunNum}) </g:link></td>    
                        <g:each in="${experiment.analysisWorkflowRuns}" var="analysisWorkflowRun" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${analysisWorkflowRun.genome}</td>
                            <td class="text-right">${analysisWorkflowRun.peakCallingParam}</td>
                            <td class="text-right"><g:formatNumber number="${analysisWorkflowRun.peaks}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${analysisWorkflowRun.singletons}" format="###,###,###" /></td>
                            <td class="text-right">${analysisWorkflowRun.peakPairsParam}</td>
                            <td class="text-right"><g:formatNumber number="${analysisWorkflowRun.peakPairs}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${analysisWorkflowRun.nonPairedPeaks}" format="###,###,###" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>      
        </tbody>
      </table>
</div>
