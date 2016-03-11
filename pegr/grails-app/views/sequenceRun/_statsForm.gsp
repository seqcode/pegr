<div class="row">
    <div class="col-sm-4 fields">
        <h4>Basic Information</h4>
        <g:render template="basicForm"></g:render>
    </div>
    <div class="col-sm-4 fields">
        <h4>Master Pool</h4>
        <div>
            <label>Librarry Volume (ul)</label>
            <g:textField name="libraryVolume" value="${run?.runStats?.libraryVolume}"></g:textField>
        </div>
        <div>
            <label>Library Stock (fmol)</label>
            <g:textField name="libraryStock" value="${run?.runStats?.libraryStock}"></g:textField>
        </div>
        <div>
            <label>Std Dev</label>
            <g:textField name="libraryStdDev" value="${run?.runStats?.libraryStdDev}"></g:textField>
        </div>
        <div>
            <label>% Std Dev</label>
            <g:textField name="pctLibraryStdDev" value="${run?.runStats?.pctLibraryStdDev}"></g:textField>%
        </div>
        <div>
            <label>qPCR Date</label>
            <g:datePicker precision="day" name="qPcrDate" default="${run?.runStats?.qPcrDate}"/>
        </div>
        <div>
            <label>qPCR Technician</label>
            <g:select name="qPcrTechnician.id" from="${pegr.User.list(sort:'username')}" optionKey="id" value="${run?.runStats?.qPcrTechnician?.id}" noSelection="['null': '']"/>
        </div>
        <div>
            <label>Cycles</label>
            <g:textField name="cycles" value="${run?.runStats?.cycles}"></g:textField>
        </div>
        <div>
            <label>SR or PE</label>
            <g:select name="srOrPe" from="${['SR', 'PE']}" value="${run?.runStats?.srOrPe}"></g:select>
        </div>
        <div>
            <label>Seq Ctrl</label>
            <g:textField name="seqCtrl" value="${run?.runStats?.seqCtrl}"></g:textField>
        </div>
        <div>
            <label>PCR Cycle</label>
            <g:textField name="pcrCycles" value="${run?.runStats?.pcrCycles}"></g:textField>
        </div>
        <div>
            <label>Qubit Conc. (nM)</label>
            <g:textField name="qubitConc" value="${run?.runStats?.qubitConc}"></g:textField>
        </div>
        <div>
            <label>qPCR Conc. (nM)</label>
            <g:textField name="qPcrConc" value="${run?.runStats?.qPcrConc}"></g:textField>
        </div>
        <div>
            <label>Library Loaded (pM)</label>
            <g:textField name="libraryLoadedPm" value="${run?.runStats?.libraryLoadedPm}"></g:textField>
        </div>
        <div>
            <label>Phi-X Loaded (fmol)</label>
            <g:textField name="phiXLoaded" value="${run?.runStats?.phiXLoaded}"></g:textField>
        </div>
        <div>
            <label>Library Loaded (fmol)</label>
            <g:textField name="libraryLoadedFmol" value="${run?.runStats?.libraryLoadedFmol}"></g:textField>
        </div>
    </div>
    <div class="col-sm-4 fields">
        <h4>Sequencing Run</h4>
        <div>
            <label>Cluster # (K/mm<sup>2</sup>)</label>
            <g:textField name="clusterNum" value="${run?.runStats?.clusterNum}"></g:textField>
        </div>
        <div>
            <label># Read PF (M)</label>
            <g:textField name="readPf" value="${run?.runStats?.readPf}"></g:textField>
        </div>
        <div>
            <label>% PF</label>
            <g:textField name="pctPf" value="${run?.runStats?.pctPf}"></g:textField>%
        </div>
        <div>
            <label>% >= Q30</label>
            <g:textField name="pctQ30" value="${run?.runStats?.pctQ30}"></g:textField>%
        </div>
        <div>
            <label>Qidx</label>
            <g:textField name="qidx" value="${run?.runStats?.qidx}"></g:textField>
        </div>
        </br>
        <h4>Bioinformatics</h4>
        <div>
            <label>Total Reads</label>
            <g:textField name="totalReads" value="${run?.runStats?.totalReads}"></g:textField>
        </div>
        <div>
            <label>Unmatched Indices</label>
            <g:textField name="unmatchedIndices" value="${run?.runStats?.unmatchedIndices}"></g:textField>
        </div>
        <div>
            <label>% Unmatched Indices</label>
            <g:textField name="pctUnmatchedIndices" value="${run?.runStats?.pctUnmatchedIndices}"></g:textField>%
        </div>
        <div>
            <label>% Aligned To PhiX</label>
            <g:textField name="pctAlignedToPhiX" value="${run?.runStats?.pctAlignedToPhiX}"></g:textField>%
        </div>
    </div>
</div>