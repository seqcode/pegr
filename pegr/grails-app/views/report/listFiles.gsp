<html>
<head>
    <meta name="layout" content="main">
</head>
<body>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Sample ID</th>
            <th>Description</th>
            <th>Fastq</th>
            <th>Bam</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${samples}" var="sample">
        <tr>
            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.id} (Old ${sample.source} ${sample.sourceId})</td>
            <td rowspan="${Math.max(1, sample.alignmentCount)}"> ${sample.naturalId}</td>
            <g:each in="${sample.experiments}" var="experiment" status="nExp">
                <g:if test="${nExp>0}"><tr></g:if>
                <td rowspan="${Math.max(1, experiment.alignments.size())}"><g:each in="${experiment.fastqFile}" var="fastq">${fastq.key}: <a href="${fastq.value}">${fastq.value}</a><br></g:each></td>
                <g:each in="${experiment.alignments}" var="alignment">
                    <td><a href="${alignment.bamFile}">${alignment.bamFile}</a></td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
    </tbody>
</table>

</body>
</html>

