<div class="row">
    <div class="col-md-3">
        <h4>Basic Information</h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Platform</td><td> ${run.platform}</td></tr>
            <tr><td>User</td><td> ${run.user}</td></tr>
            <tr><td>Date</td><td> <g:formatDate format="yyyy-MM-dd" date="${run.date}"/></td></tr>
            <tr><td>Directory</td><td> ${run.directoryName}</td></tr>
            <tr><td>FC ID</td><td> ${run.fcId}</td></tr>
            <tr><td>Lane</td><td> ${run.lane}</td></tr>
            <tr><td>Notes</td><td> ${run.note}</td></tr>        
            </tbody></table>
    </div>
    <div class="col-md-3">
        <h4>Master Pool</h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Library Volume (ul)</td><td> ${run.runStats?.libraryVolume}</td></tr>
            <tr><td>Library Stock (fmol)</td><td> ${run.runStats?.libraryStock}</td></tr>
            <tr><td>Std Dev</td><td> ${run.runStats?.libraryStdDev} <g:if test="${run.runStats?.pctLibraryStdDev}"> (<g:formatNumber number="${run.runStats?.pctLibraryStdDev}" format="#0.##%"></g:formatNumber>)</g:if></td></tr>
            <tr><td>Cycles</td><td> ${run.runStats?.cycles}</td></tr>
            <tr><td>SR or PE</td><td> ${run.runStats?.srOrPe}</td></tr>
            <tr><td>Seq Ctrl</td><td> ${run.runStats?.seqCtrl}</td></tr>
            <tr><td>PCR Cycles</td><td> ${run.runStats?.pcrCycles}</td></tr>
            <tr><td>Qubit Conc. (nM)</td><td> ${run.runStats?.qubitConc}</td></tr>
            <tr><td>qPCR Conc. (nM)</td><td> ${run.runStats?.qPcrConc}</td></tr>
            <tr><td>Library Loaded (pM)</td><td> ${run.runStats?.libraryLoadedPm} </td></tr>
            <tr><td>Phi-X Loaded (fmol)</td><td> ${run.runStats?.phiXLoaded}</td></tr>
            <tr><td>Library Loaded (fmol)</td><td> ${run.runStats?.libraryLoadedFmol}</td></tr>
            <tr><td>Notes</td><td> ${run.runStats?.notes}</td></tr>
            </tbody></table>
    </div>
    <div class="col-md-3">
        <h4>Sequencing Run</h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Cluster # (K/mm<sup>2</sup>)</td><td> ${run.runStats?.clusterNum}</td></tr> 
            <tr><td># Read PF (M)</td><td> ${run.runStats?.readPf}</td></tr>
            <tr><td>% PF</td><td> <g:if test="${run.runStats?.pctPf}">${run.runStats?.pctPf}%</g:if></td></tr>
            <tr><td>% >= Q30</td><td> <g:if test="${run.runStats?.pctQ30}">${run.runStats?.pctQ30}%</g:if></td></tr>
            <tr><td>Qidx</td><td> ${run.runStats?.qidx}</td></tr>
            </tbody></table>
    </div>
    <div class="col-md-3">
        <h4>Bioinformatics</h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Total Reads</td><td> <g:formatNumber number="${run.runStats?.totalReads}" format="###,###,##0" /> </td></tr>
            <tr><td>Unmatched Indices</td><td>  <g:formatNumber number="${run.runStats?.unmatchedIndices}" format="###,###,##0"></g:formatNumber></td></tr>
            <tr><td>Unmatched Indices</td><td>  <g:formatNumber number="${run.runStats?.pctUnmatchedIndices}" format="#0.##%"></g:formatNumber></td></tr>
            <tr><td>Aligned To PhiX</td><td> <g:if test="${run.runStats?.pctAlignedToPhiX}">${run.runStats?.pctAlignedToPhiX}</g:if></td></tr>    
            </tbody></table>
    </div>
</div>
