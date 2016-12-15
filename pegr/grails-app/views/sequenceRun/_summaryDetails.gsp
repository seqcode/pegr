<div class="row">
    <div class="col-md-3">
        <h4>Basic Information</h4>
        <ul>
            <li>Platform: ${run.platform}</li>
            <li>Directory: ${run.directoryName}</li>
            <li>User: ${run.user}</li>
            <li>Date: <g:formatDate format="yyyy-MM-dd" date="${run.date}"/></li>
            <li>Notes: ${run.note}</li>        
        </ul>
    </div>
    <div class="col-md-3">
        <h4>Master Pool</h4>
        <ul>
            <li>Library Volume (ul): ${run.runStats?.libraryVolume}</li>
            <li>Library Stock (fmol): ${run.runStats?.libraryStock}</li>
            <li>Std Dev: ${run.runStats?.libraryStdDev} <g:if test="${run.runStats?.pctLibraryStdDev}"> (<g:formatNumber number="${run.runStats?.pctLibraryStdDev}" format="#0.##%"></g:formatNumber>)</g:if></li>
            <li>Cycles: ${run.runStats?.cycles}</li>
            <li>SR or PE: ${run.runStats?.srOrPe}</li>
            <li>Seq Ctrl: ${run.runStats?.seqCtrl}</li>
            <li>PCR Cycles: ${run.runStats?.pcrCycles}</li>
            <li>Qubit Conc. (nM): ${run.runStats?.qubitConc}</li>
            <li>qPCR Conc. (nM): ${run.runStats?.qPcrConc}</li>
            <li>Library Loaded (pM): ${run.runStats?.libraryLoadedPm} </li>
            <li>Phi-X Loaded (fmol): ${run.runStats?.phiXLoaded}</li>
            <li>Library Loaded (fmol): ${run.runStats?.libraryLoadedFmol}</li>
            <li>Notes: ${run.runStats?.notes}</li>
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
            <li>Total Reads: <g:formatNumber number="${run.runStats?.totalReads}" format="###,###,##0" /> </li>
            <li>Unmatched Indices:  <g:formatNumber number="${run.runStats?.unmatchedIndices}" format="###,###,##0"></g:formatNumber></li>
            <li>Unmatched Indices:  <g:formatNumber number="${run.runStats?.pctUnmatchedIndices}" format="#0.##%"></g:formatNumber></li>
            <li>Aligned To PhiX: <g:if test="${run.runStats?.pctAlignedToPhiX}">${run.runStats?.pctAlignedToPhiX}</g:if></li>    
        </ul>
    </div>
</div>
