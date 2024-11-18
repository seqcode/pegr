<html>
<head>
    <meta name="layout" content="main">
    <asset:javascript src="exportCsv.js"/>
</head>
<body>
<div>  
    <button class="btn btn-primary" id="export-csv">Export CSV file</button>
    <button data-toggle="collapse" data-target="#select-files" aria-expanded="false" action="downloadScript" params="[reportId:reportId]" class="btn btn-primary" id="download-script">Download script</button>
    <div id="select-files" class="collapse well">
        <p>Please select the files to download.</p>
        <g:form controller="report" action="downloadScript" method="post">
            <input type="hidden" name="reportId" value="${reportId}">
            <div class="form-group">
                <input type="checkbox" name="filetypes" value="fastq"> <label>FASTQ</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="filetypes" value="raw_bam"> <label>Raw BAM</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="filetypes" value="filtered_bam"> <label>Filtered BAM</label>
            </div>
            <div class="form-group">
                <input type="checkbox" name="filetypes" value="bigwig"> <label>bigWig</label>
            </div>
            <g:submitButton name="submit" value="Submit" class="edit"></g:submitButton>
        </g:form>
    </div>
</div>    
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
            <th>BigWig</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${samples}" var="sample">
            <g:each in="${sample.experiments}" var="experiment">
                <g:each in="${experiment.alignments}" var="alignment">
                    <tr>
                        <td>${sample.id} (Source ${sample.source} ${sample.sourceId})</td>
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
                        <td>
                          <g:if test="${alignment.bigwigForwardFile}">
                            Forward: <a href="${alignment.bigwigForwardFile}">${alignment.bigwigForwardFile}</a>; 
                          </g:if>
                          <g:if test="${alignment.bigwigReverseFile}">
                            Reverse: <a href="${alignment.bigwigReverseFile}">${alignment.bigwigReverseFile}</a>
                          </g:if>
                        </td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
    </tbody>
</table>
<script>
    $("#export-csv").on("click", function () {
        var html = $("table").outerHTML;
        export_table_to_csv(html, "table.csv");
    });
    
    $("#download-script").on("click", function () {
        
        
    });
</script>
</body>
</html>

