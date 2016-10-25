<h4>Yeast Encode Quality Control</h4>
<table>
    <thead>
        <tr>
            <th>Sample ID</th>
            <th>Target</th>
            <th>Note</th>
            <g:each in="${qcSettings.yeast}" var="setting">
                <th class="text-right col-${setting.key} group-qc">
                    ${setting.name}
                    <ul style="font-weight: normal">
                        <g:if test="${setting.min}"><li>min: <g:formatNumber number="${setting.min}" format="${setting.numFormat}" /></li></g:if>
                        <g:if test="${setting.max}"><li>max: <g:formatNumber number="${setting.max}" format="${setting.numFormat}" /></li></g:if>
                        <g:if test="${setting.reference_min}"><li>min: ${setting.reference_min}</li></g:if>
                        <g:if test="${setting.reference_max}"><li>min: ${setting.reference_max}</li></g:if>
                    </ul>                                        
                </th>
            </g:each>
            <th>Recommendations</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${runStatusMap.value.sampleStatusList}" var="sample">
            <tr>
                <td class="col-sample group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                <td class="col-target group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.target}</td>
                <g:each in="${qcSettings.yeast}" var="setting">
                    <td class="text-right col-${setting.key} group-qc <g:if test='${
                                   (setting.min != null && alignment[setting.key] < setting.min)
                                   || (setting.max != null && alignment[setting.key] > setting.max)
                                   || (setting.reference_min != null && alignment.hasProperty(setting.reference_min) && alignment[setting.key] < alignment[setting.reference_min] * setting.reference_min_ratio)
                                   || (setting.reference_max != null && alignment.hasProperty(setting.reference_max) && alignment[setting.key] > alignment[setting.reference_max] * setting.reference_max_ratio)
                                   }'>bg-danger</g:if>"> 
                        <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                    </td>
                </g:each>
                <td></td>
            </tr>
        </g:each>
    </tbody>
</table>

<script>
    if target.upper()=="NOTAG" or target.upper()=="NOTARGET":
    recommend=""
else:
    if dedupUniqReads <0.2:
        if mappedReadPct >0.5 and adapterDimerPct<0.15 and duplicLevel <0.7:
            recommend="Re-sequence"
        else:
            if stress != None:
                recommend="Done; stress gene"
            elif polII <0.1 and fpkm<0.1:
                recommend="Done; low exprs"
            else:
                recommend="re-ChIP"
    else:
        if (stamp =="Yes") or (multiGPS > 25) or (peakPairs >50) or (nucleosomeEnrichment>1.5) or (enrichedSegments):
            recommend="Done; success"
        elif stress != None:
            recommend="Done; stress gene"
        elif polIILevel <0.1 and exprsLevel<0.1:
            recommend="Done; low exprs"
        else:
            recommend="Done; failed"
</script>