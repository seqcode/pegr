<div class="row">
    <div class="col-md-3">
        <h4>Basic Information</h4>
        <ul>
            <li>Platform: ${run.platform}</li>
            <li>User: ${run.user}</li>
            <li>Date: <g:formatDate format="yyyy-MM-dd" date="${run.date}"/></li>
            <li>Notes: ${run.note}</li>        
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Master Pool
            <g:if test="${run?.poolItem}">
                <g:link controller="item" action="show" params="['id':run.poolItem.id]" class="edit">Link</g:link>
                <g:if test="${run?.status==pegr.RunStatus.PREP}">
                    <g:link action="removePool" params="['runId':run.id]" class="edit confirm">Remove</g:link>
                </g:if>
            </g:if>
            <g:elseif test="${run?.status==pegr.RunStatus.PREP}">
                <g:link action="searchPool" params="['runId':run.id]" class="edit">Add</g:link>
            </g:elseif>
        </h4>
        <ul>
            <li>Library Volume (ul): ${run.runStats?.libraryVolume}</li>
            <li>Library Stock (fmol): ${run.runStats?.libraryStock}</li>
            <li>Std Dev: ${run.runStats?.libraryStdDev} <g:if test="${run.runStats?.pctLibraryStdDev}"> (${run.runStats?.pctLibraryStdDev}%)</g:if></li>
            <li>Cycles: ${run.runStats?.cycles}</li>
            <li>SR or PE: ${run.runStats?.srOrPe}</li>
            <li>Seq Ctrl: ${run.runStats?.seqCtrl}</li>
            <li>PCR Cycles: ${run.runStats?.pcrCycles}</li>
            <li>Qubit Conc. (nM): ${run.runStats?.qubitConc}</li>
            <li>qPCR Conc. (nM): ${run.runStats?.qPcrConc}</li>
            <li>Library Loaded (pM): ${run.runStats?.libraryLoadedPm} </li>
            <li>Phi-X Loaded (fmol): ${run.runStats?.phiXLoaded}</li>
            <li>Library Loaded (fmol): ${run.runStats?.libraryLoadedFmol}</li>
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Sequencing Run</h4>
        <ul>
            <li>Cluster # (K/mm<sup>2</sup>): ${run.runStats?.clusterNum}</li> 
            <li># Read PF (M): ${run.runStats?.readPf}</li>
            <li>% PF: <g:if test="${run.runStats?.pctPf}">${run.runStats?.pctPf}%</g:if></li>
            <li>% >= Q30: <g:if test="${run.runStats?.pctQ30}">${run.runStats?.pctQ30}%</g:if></li>
            <li>Qidx: ${run.runStats?.qidx}</li>
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Bioinformatics</h4>
        <ul>
            <li>Total Reads: ${run.runStats?.totalReads}</li>
            <li>Unmatched Indices: ${run.runStats?.unmatchedIndices}</li>
            <li>% Unmatched Indices: <g:if test="${run.runStats?.pctUnmatchedIndices}">${run.runStats?.pctUnmatchedIndices}%</g:if></li>
            <li>% Aligned To PhiX: <g:if test="${run.runStats?.pctAlignedToPhiX}">${run.runStats?.pctAlignedToPhiX}%</g:if></li>    
        </ul>
    </div>
</div>
