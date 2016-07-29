<h3>Fastqc Reports</h3>
<ul>
<g:each in="${sampleDTOs}" var="sample">
    <g:each in="${sample.experiments}" var="experiment">
        <li>
            Run${experiment.runId}:
             <g:if test="${experiment.alignments.last()?.fastqc.read1}">
                <a href="${experiment.alignments.last()?.fastqc.read1}">READ1</a>
            </g:if>
            <g:else>
                NONE&nbsp;
            </g:else>
             | 
            <g:if test="${experiment.alignments.last()?.fastqc.read2}">
                <a href="${experiment.alignments.last()?.fastqc.read2}">READ2</a>
            </g:if>
            <g:else>
                NONE&nbsp;
            </g:else>
        </li>
    </g:each>
</g:each>
</ul>

<g:render template="/report/detailsAlignments" model="['sampleDTOs':sampleDTOs]"/>