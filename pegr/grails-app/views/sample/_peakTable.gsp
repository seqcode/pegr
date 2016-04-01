<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Genome</th>   
                <th>Peak Count</th>
                <th>Peak Mean±Std</th>
                <th>Peak Media±Std</th>
                <th>Peak Pair NOS</th>
                <th>Peak Pair WIS</th>
                <th>Singleton Count</th>
                <th>Median Tag Singletons</th>
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
                    <td>${alignment.genome}</td> 
                    <td>${alignment.alignmentStats?.peaks}</td>
                    <td>${alignment.alignmentStats?.peakMean}<g:if test="${alignment.alignmentStats?.peakMeanStd}">
                        ±${alignment.alignmentStats?.peakMeanStd}</g:if></td>
                    <td>
                        ${alignment.alignmentStats?.peakMdian}<g:if test="${alignment.alignmentStats?.peakMeanStd}">±${alignment.alignmentStats?.peakMedianStd}</g:if></td> 
                    <td>${alignment.alignmentStats?.peakPairNos}</td>
                    <td>${alignment.alignmentStats?.peakPairWis}</td>
                    <td>${alignment.alignmentStats?.singletons}</td>
                    <td>${alignment.alignmentStats?.medianTagSingletons}</td>                    
                    <td>${alignment.alignmentStats?.seqDuplicationLevel}</td>
                    <td>${alignment.alignmentStats?.tssDistal}</td>
                    <td>${alignment.alignmentStats?.tssProximal}</td>
                    <td>${alignment.alignmentStats?.repeatedRegions}</td>
                    <td>${alignment.alignmentStats?.peakFilePath}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>