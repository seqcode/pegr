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
