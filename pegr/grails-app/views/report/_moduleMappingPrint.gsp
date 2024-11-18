<section>
    <h4>Mapping Statistics （Read 1）</h4>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Read Count (R1)</th>
                <th class="text-right">Uniquely Mapped Count (R1)</th>
                <th class="text-right">% Uniquely Mapped Count (R1)</th>
                <th class="text-right">Deduplicated Count (R1)</th>
                <th class="text-right">% Deduplicated Count (R1)</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample" status="n">
                <tr>
                    <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.id}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>  
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${experiment?.runId}</td>
                            <td>${alignment.genome}</td>
                            <td class="text-right"><g:formatNumber number="${experiment.totalReads}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReads}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct}" format="#0.0%" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReads}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.deduplicatedPct}" format="#0.0%" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>              
        </tbody>
    </table>
</section>
<section>
    <h4>Mapping Statistics (Read 2)</h4>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Sequence Run</th>
                <th>Genome</th>
                <th class="text-right">Read Count (R2)</th>
                <th class="text-right">Uniquely Mapped Count (R2)</th>
                <th class="text-right">% Uniquely Mapped Count (R2)</th>
                <th class="text-right">Deduplicated Count (R2)</th>
                <th class="text-right">% Deduplicated Count (R2)</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample" status="n">
                <tr>
                    <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.id}</td>
                    <g:each in="${sample.experiments}" var="experiment" status="nExp">
                        <g:if test="${nExp>0}"><tr></g:if>  
                        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                            <g:if test="${nAli>0}"><tr></g:if>
                            <td>${experiment?.runId}</td>
                            <td>${alignment.genome}</td>
                            <td class="text-right"><g:formatNumber number="${experiment.totalReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct2}" format="#0.0%" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReadsR2}" format="###,###,###" /></td>
                            <td class="text-right"><g:formatNumber number="${alignment.deduplicatedPct2}" format="#0.0%" /></td>
                            </tr>
                        </g:each>
                    </g:each>
            </g:each>              
        </tbody>
    </table>
</section>