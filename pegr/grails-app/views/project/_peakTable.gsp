<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample</th>
                <th>Run</th>
                <th>Genome</th>   
                <th>Peak Count</th>
                <th>Peak Mean±Std</th>
                <th>Peak Median±Std</th>
                <th>Peak Pair NOS</th>
                <th>Peak Pair WIS</th>
                <th>Singleton Count</th>
                <th>Median Tag Singletons</th>
                <th>Duplication Level</th>
                <th>TSS Distal</th>
                <th>TSS Proximal</th>
                <th>Repeated Regions</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${alignmentList}" var="alignment">
                <tr>
                    <td><g:link controller="sample" action="show" id="${alignment.sequencingExperiment?.sample?.id}">${alignment.sequencingExperiment?.sample?.id}</g:link></td>    
                    <td><g:link controller="sequenceRun" action="show" id="${alignment.sequencingExperiment?.sequenceRun?.id}">${alignment.sequencingExperiment?.sequenceRun?.id} (Old ${alignment.sequencingExperiment?.sequenceRun?.runNum}) </g:link></td>
                    <td>${alignment.genome}</td> 
                    <td>${alignment.alignmentStats?.peaks}</td>
                    <td>${alignment.alignmentStats?.peakMean}<g:if test="${alignment.alignmentStats?.peakMeanStd}">
                        ±${alignment.alignmentStats?.peakMeanStd}</g:if></td>
                    <td>
                        ${alignment.alignmentStats?.peakMedian}<g:if test="${alignment.alignmentStats?.peakMedianStd}">±${alignment.alignmentStats?.peakMedianStd}</g:if></td> 
                    <td>${alignment.alignmentStats?.peakPairNos}</td>
                    <td>${alignment.alignmentStats?.peakPairWis}</td>
                    <td>${alignment.alignmentStats?.singletons}</td>
                    <td>${alignment.alignmentStats?.medianTagSingletons}</td>                    
                    <td>${alignment.alignmentStats?.seqDuplicationLevel}</td>
                    <td>${alignment.alignmentStats?.tssDistal}</td>
                    <td>${alignment.alignmentStats?.tssProximal}</td>
                    <td>${alignment.alignmentStats?.repeatedRegions}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>