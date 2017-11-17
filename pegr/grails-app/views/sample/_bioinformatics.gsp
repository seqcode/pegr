<h3>Visualization</h3>
<ol>
<g:each in="${sampleDTOs}" var="sample">
    <g:each in="${sample.experiments}" var="experiment">
        <!-- If none found print message -->
        <g:if test="${experiment.alignments.size() == 0}">
           No alignments available for ${sample.id}
        </g:if>
        <g:each in="${experiment.alignments}" var="alignment" status="nAli">
            <!-- Need to make sure all elements needed for track are avail. -->
            <g:if test="${alignment.readDbId}">
            <g:if test="${alignment.genome}">
                <li>
                <g:if test="${sample.assay == 'XO'}">
                    <a target="_blank" href="http://main.genome-browser.bx.psu.edu/cgi-bin/hgTracks?db=${alignment.genome}&hgct_customText=track%20type=readdb%20name=pegr_${alignment.readDbId}plus%20description=%22PEGR%20${sample.source}%20${sample.target}%20plus%22%20visibility=full%20bigDataUrl=${alignment.readDbId}%3BisPlusStrand%3D1%0Dtrack%20type=readdb%20name=pegr_${alignment.readDbId}minus%20description=%22PEGR%20${sample.source}%20${sample.target}%20minus%22%20visibility=full%20bigDataUrl=${alignment.readDbId}%3BisPlusStrand%3D0">Browser tracks</a>
                </g:if>
                <g:elseif test="${sample.assay == 'RNA'}">
                    <a target="_blank" href="http://main.genome-browser.bx.psu.edu/cgi-bin/hgTracks?db=${alignment.genome}&hgct_customText=track%20type=readdb%20name=pegr_${alignment.readDbId}plus%20description=%22PEGR%20${sample.source}%20${sample.target}%20plus%22%20visibility=full%20bigDataUrl=${alignment.readDbId}%3BisPlusStrand%3D1%0Dtrack%20type=readdb%20name=pegr_${alignment.readDbId}minus%20description=%22PEGR%20${sample.source}%20${sample.target}%20minus%22%20visibility=full%20bigDataUrl=${alignment.readDbId}%3BisPlusStrand%3D0">Browser tracks</a>
                </g:elseif>
                <g:else>
                    <a target="_blank" href="http://main.genome-browser.bx.psu.edu/cgi-bin/hgTracks?db=${alignment.genome}&hgct_customText=track%20type=readdb%20name=pegr_${alignment.readDbId}%20description=%22PEGR%20${sample.source}%20${sample.target}%22%20visibility=full%20bigDataUrl=${alignment.readDbId}">Browser track</a>
                </g:else>
               </li>
            </g:if>
            </g:if>
            <g:else>
               Not available
            </g:else>
        </g:each>
    </g:each>
</g:each>
</ol>
<h3>Files</h3>
<table class="table table-bordered">
    <thead>
        <th>Sequence Run</th>
        <th>Fastq Files</th>
        <th>Fastqc Reports</th>
        <th>Genome</th>
        <th>Bam Files</th>
    </thead>
    <tbody>
    <g:each in="${sampleDTOs}" var="sample">
        <g:each in="${sample.experiments}" var="experiment">
            <tr>
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
                <td><a href="${alignment.bam}">Bam</a></td>
                </tr>
                </g:each>
        </g:each>
    </g:each>
    </tbody>
</table>

<g:render template="/report/detailsAlignments" model="['sampleDTOs':sampleDTOs]"/>
