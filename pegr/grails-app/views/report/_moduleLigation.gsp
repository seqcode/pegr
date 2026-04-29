<h3>Ligation</h3>
<div class="table-responsive">
  <table class="table table-bordered">
    <thead>
        <tr>
            <th>PEGR id</th>
            <th>Sequence Run</th>
            <th>Genome</th>
            <th class="text-right">Trans</th>
            <th class="text-right">Cis &gt;=1kb</th>
            <th class="text-right">Cis &gt;=2kb</th>
            <th class="text-right">Cis &gt;=4kb</th>
            <th class="text-right">Cis &gt;=10kb</th>
            <th class="text-right">Cis &gt;=20kb</th>
            <th class="text-right">Cis &gt;=40kb</th>
            <th class="text-right">Total Dedup</th>
            <th>Ligation plot</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${sampleList}" var="sample">
            <tr>
                <td rowspan="${Math.max(1, sample.alignmentCount)}"><g:link controller="sample" action="show" id="${sample?.id}">${sample?.id}</g:link> ${sample.naturalId}</td>
                <g:each in="${sample.experiments}" var="experiment" status="nExp">
                    <g:if test="${nExp>0}"><tr></g:if>
                    <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:link controller="sequenceRun" action="show" id="${experiment?.runId}">${experiment?.runId} (Run Name: ${experiment?.RunName})</g:link></td>
                    <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                        <g:if test="${nAli>0}"><tr></g:if>
                        <td>${alignment.genome}</td>
                        <td class="text-right"><g:formatNumber number="${alignment.trans}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis1kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis2kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis4kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis10kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis20kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.cis40kb}" format="###,###,###" /></td>
                        <td class="text-right"><g:formatNumber number="${alignment.totalDedup}" format="###,###,###" /></td>
                        <td class="ligation-plot">
                          <span class="hidden">${alignment.ligationPlot}</span>
                          <g:if test="${alignment.ligationPlot}">
                            <div class="ligation-plot-fig" style="width:400px; height:300px;"></div>
                          </g:if>
                        </td>
                        </tr>
                    </g:each>
                </g:each>
        </g:each>
    </tbody>
  </table>
</div>
