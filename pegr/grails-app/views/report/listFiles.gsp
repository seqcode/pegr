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
            <th>Target</th>
            <th>Antibody</th>
            <th>Celltype/Strain</th>
            <th>Mutation</th>
            <th>Growth Media</th>
            <th>Treatments</th>
            <th>Assay</th>
            <th>Genome</th>
            <th>Fastq</th>
            <th>Raw Bam</th>
            <th>Filtered Bam</th>
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
                        <td>${sample.target}</td>
                        <td>${sample.antibody}</td>
                        <td>${sample.strain} </td>
                        <td>${sample.geneticModification}</td>
                        <td>${sample.growthMedia}</td>
                        <td>${sample.treatments}</td>
                        <td>${sample.assay}</td>
                        <td>${alignment.genome}</td>
                        <td><g:each in="${experiment.fastq}" var="fastq">${fastq.key}: <a href="${fastq.value}">${fastq.value}</a>; </g:each></td>
                        <td><a href="${alignment.bamRaw}">${alignment.bamRaw}</a> </td>
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

