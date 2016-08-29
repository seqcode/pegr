<div class="table-responsive">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>N-term TAG</th>
                <th>Target</th>
                <th>C-term TAG</th>
                <th>Detected Epitope tag</th>
                <th>Count</th>
                <th>Additional Epitope detected</th>
                <th>FastQC</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td rowspan="${Math.max(1, sample.experiments.size())}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link></td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}">${sample?.nTermTag}</td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}">${sample?.target}</td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}">${sample?.cTermTag}</td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}"></td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}"></td>
                    <td rowspan="${Math.max(1, sample.experiments.size())}"></td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>
                        <td>
                            Run${experiment.runId}:
                            <g:if test="${experiment.fastqc?.read1}">
                                <a href="${experiment.fastqc?.read1}">READ1</a>
                            </g:if>
                            <g:else>
                                NONE&nbsp;
                            </g:else>
                             | 
                            <g:if test="${experiment.fastqc?.read2}">
                                <a href="${experiment.fastqc?.read2}">READ2</a>
                            </g:if>
                            <g:else>
                                NONE&nbsp;
                            </g:else>
                        </td>
                        </tr>
                    </g:each>
            </g:each>
        </tbody>
      </table>
</div>
