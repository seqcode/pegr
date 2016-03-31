<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample</th>
                <th>Run</th>
                <th>Genome</th>                
                <th>Peak #</th>
                <th>Peak Mean±Std</th>
                <th>Peak Media±Std</th>
                <th>Peak Pair NOS</th>
                <th>Peak Pair WIS</th>
                <th>Singleton #</th>
                <th>Median Tag Singletons</th>
                <th>Genome Coverage</th>                
                <th>Duplication Level</th>
                <th>TSS Distal</th>
                <th>TSS Proximal</th>
                <th>Repeated Regions</th>
                <th>Peak File</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${alignmentList}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${alignment.sequencingExperiment?.sequenceRun?.id}">${alignment.sequencingExperiment?.sequenceRun?.id} (Old ${alignment.sequencingExperiment?.sequenceRun?.runNum}) </g:link></td>
                    <td>${alignment.genome}</td>                    
                    <td>${alignment.peakStatistics?.peaks}</td>
                    <td>${alignment.peakStatistics?.peakMean}<g:if test="${alignment.peakStatistics?.peakMeanStd}">
                        ±${alignment.peakStatistics?.peakMeanStd}</g:if></td>
                    <td>
                        ${alignment.peakStatistics?.peakMdian}<g:if test="${alignment.peakStatistics?.peakMeanStd}">±${alignment.peakStatistics?.peakMedianStd}</g:if></td> 
                    <td>${alignment.peakStatistics?.peakPairNos}</td>
                    <td>${alignment.peakStatistics?.peakPairWis}</td>
                    <td>${alignment.peakStatistics?.singletons}</td>
                    <td>${alignment.peakStatistics?.medianTagSingletons}</td>                    
                    <td>${alignment.peakStatistics?.genomeCoverage}</td>
                    <td>${alignment.peakStatistics?.seqDuplicationLevel}</td>
                    <td>${alignment.peakStatistics?.tssDistal}</td>
                    <td>${alignment.peakStatistics?.tssProximal}</td>
                    <td>${alignment.peakStatistics?.repeatedRegions}</td>
                    <td>${alignment.peakStatistics?.peakFilePath}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>