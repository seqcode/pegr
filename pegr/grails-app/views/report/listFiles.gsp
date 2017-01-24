<html>
<head>
    <meta name="layout" content="main">
    <asset:javascript src="exportCsv.js"/>
</head>
<body>
<button class="btn btn-primary">Export CSV file</button>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Sample ID</th>
            <th>Description</th>
            <th>Fastq</th>
            <th>Bam</th>
            <th>Scidx</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${samples}" var="sample">
            <g:each in="${sample.experiments}" var="experiment">
                <g:each in="${experiment.alignments}" var="alignment">
                    <tr>
                        <td>${sample.id} (Old ${sample.source} ${sample.sourceId})</td>
                        <td>${sample.naturalId}</td>
                        <td><g:each in="${experiment.fastq}" var="fastq">${fastq.key}: <a href="${fastq.value}">${fastq.value}</a>; </g:each></td>
                        <td><a href="${alignment.bam}">${alignment.bam}</a></td>
                        <td><a href="${alignment.scidx}">${alignment.scidx}</a></td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
    </tbody>
</table>
<script>
    $("button").on("click", function () {
        var html = $("table").outerHTML;
        export_table_to_csv(html, "table.csv");
    });
</script>
</body>
</html>

