<h3>Files</h3>
<table class="table table-bordered">
    <thead>
        <th>Sample ID</th>
        <th>Sequence Run</th>
        <th>Fastq Files</th>
        <th>Fastqc Reports</th>
        <th>Genome</th>
        <th>Bam Files</th>
        <th>BigWig Files</th>
    </thead>
    <tbody>
    <g:each in="${sampleDTOs}" var="sample">
        <g:each in="${sample.experiments}" var="experiment">
            <tr>
                <td rowspan="${Math.max(1,experiment.alignments.size())}"><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link> ${sample.naturalId}</td>
                <td rowspan="${Math.max(1,experiment.alignments.size())}">Run${experiment.runId}</td>
                <td rowspan="${Math.max(1,experiment.alignments.size())}">
                <g:if test="${experiment.fastq?.read1}">
                    <a href="${experiment.fastq.read1}">READ1</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                 | 
                <g:if test="${experiment.fastq?.read2}">
                    <a href="${experiment.fastq.read2}">READ2</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                </td>
                <td rowspan="${Math.max(1,experiment.alignments.size())}">
                <g:if test="${experiment.fastqc?.read1}">
                    <a href="${experiment.fastqc.read1}">READ1</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                 | 
                <g:if test="${experiment.fastqc?.read2}">
                    <a href="${experiment.fastqc.read2}">READ2</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                </td>
                <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                <g:if test="${nAli>0}"><tr></g:if>
                <td>${alignment.genome}</td>
                <td>
                <g:if test="${alignment.bam}">
                    <a href="${alignment.bam}">Bam</a>
                </g:if>
                </td>
                <td>
                <g:if test="${alignment.bigwigForwardFile}">
                    <a href="${alignment.bigwigForwardFile}">Forward</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                 | 
                <g:if test="${alignment.bigwigReverseFile}">
                    <a href="${alignment.bigwigReverseFile}">Reverse</a>
                </g:if>
                <g:else>
                    NONE&nbsp;
                </g:else>
                </td>
                </tr>
                </g:each>
        </g:each>
    </g:each>
    </tbody>
</table>