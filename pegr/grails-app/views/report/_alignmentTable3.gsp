<div class="table-responsive">
    <table class="table  table-bordered">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Average Insertion Size (PE)</th>
                <th class="text-right">StdDev Insertion Size (PE)</th>
                <th class="text-right">Median Insertion Size (PE)</th>
                <th class="text-right">Mode Insertion Size (PE)</th>
                <th>Insert Size Histogram (PE)</th>
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
                            <td class="text-right">${alignment.avgInsertSize}</td>
                            <td class="text-right">${alignment.stdDevInsertSize}</td>
                            <td class="text-right">${alignment.medianInsertSize}</td>
                            <td class="text-right">${alignment.modeInsertSize}</td>
                            <td class="peHistogram" style="width:320px">
                                <g:if test="${alignment.peHistogram}">
                                <g:link controller="report" action="peHistogram" params="[url: alignment.peHistogram]" target="_blank" class="pull-right"><span class="glyphicon glyphicon-fullscreen" style="z-index: 100"></span></g:link>
                                <i class="fa fa-spinner fa-spin"></i>
                                <span class="peHistogram-url" hidden="hidden">${alignment.peHistogram}</span>
                                <div class="peHistogram-fig"></div>
                                </g:if>
                            </td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>                      
        </tbody>
      </table>
</div>
