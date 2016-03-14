<div class="row">
    <div class="col-md-3">
        <h4>Basic Information</h4>
        <ul>
            <li>Platform: ${run.platform}</li>
            <li>User: ${run.user}</li>
            <li>Date: <g:formatDate format="yyyy-MM-dd" date="${run.date}"/></li>
            <g:if test="${run.note}"><li>Notes: ${run.note}</li></g:if>            
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Master Pool
            <g:if test="${run?.poolItem}">
                <g:link controller="item" action="show" params="['id':run.poolItem.id]" class="edit">Link</g:link>
            </g:if>
            <g:else>
                <g:link action="searchPool" params="['runId':run.id]" class="edit">Add</g:link>
            </g:else>
        </h4>
        <ul>
            <g:if test="${run.runStats?.libraryVolume}"><li>Library Volume (ul): ${run.runStats?.libraryVolume}</li></g:if>
            <g:if test="${run.runStats?.libraryStock}"><li>Library Stock (fmol): ${run.runStats?.libraryStock}</li></g:if>
            <g:if test="${run.runStats?.libraryStdDev}"><li>Std Dev: ${run.runStats?.libraryStdDev}</li></g:if>
            <g:if test="${run.runStats?.pctLibraryStdDev}"><li>% Std Dev: ${run.runStats?.pctLibraryStdDev}%</li></g:if>
            <g:if test="${run.runStats?.cycles}"><li>Cycles: ${run.runStats?.cycles}</li></g:if>
            <g:if test="${run.runStats?.srOrPe}"><li>SR or PE: ${run.runStats?.srOrPe}</li></g:if>
            <g:if test="${run.runStats?.seqCtrl}"><li>Seq Ctrl: ${run.runStats?.seqCtrl}</li></g:if>
            <g:if test="${run.runStats?.pcrCycles}"><li>PCR Cycles: ${run.runStats?.pcrCycles}</li></g:if>
            <g:if test="${run.runStats?.qubitConc}"><li>Qubit Conc. (nM): ${run.runStats?.qubitConc}</li></g:if>
            <g:if test="${run.runStats?.qPcrConc}"><li>qPCR Conc. (nM): ${run.runStats?.qPcrConc}</li></g:if>
            <g:if test="${run.runStats?.libraryLoadedPm}"><li>Library Loaded (pM): ${run.runStats?.libraryLoadedPm} </li></g:if>
            <g:if test="${run.runStats?.phiXLoaded}"><li>Phi-X Loaded (fmol): ${run.runStats?.phiXLoaded}</li></g:if>
            <g:if test="${run.runStats?.libraryLoadedFmol}"><li>Library Loaded (fmol): ${run.runStats?.libraryLoadedFmol}</li></g:if>
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Sequencing Run</h4>
        <ul>
            <g:if test="${run.runStats?.clusterNum}"><li>Cluster # (K/mm<sup>2</sup>): ${run.runStats?.clusterNum}</li></g:if>     
            <g:if test="${run.runStats?.readPf}"><li># Read PF (M): ${run.runStats?.readPf}</li></g:if>
            <g:if test="${run.runStats?.pctPf}"><li>% PF: ${run.runStats?.pctPf}%</li></g:if>
            <g:if test="${run.runStats?.pctQ30}"><li>% >= Q30: ${run.runStats?.pctQ30}%</li></g:if>
            <g:if test="${run.runStats?.qidx}"><li>Qidx: ${run.runStats?.qidx}</li></g:if>
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Bioinformatics</h4>
        <ul>
            <g:if test="${run.runStats?.totalReads}"><li>Total Reads: ${run.runStats?.totalReads}</li></g:if>
            <g:if test="${run.runStats?.unmatchedIndices}"><li>Unmatched Indices: ${run.runStats?.unmatchedIndices}</li></g:if>
            <g:if test="${run.runStats?.pctUnmatchedIndices}"><li>% Unmatched Indices: ${run.runStats?.pctUnmatchedIndices}%</li></g:if>
            <g:if test="${run.runStats?.pctAlignedToPhiX}"><li>% Aligned To PhiX: ${run.runStats?.pctAlignedToPhiX}%</li></g:if>      
        </ul>
    </div>
</div>
