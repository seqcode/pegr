<table id="yeast" class="table table-bordered">
    <thead>
        <tr>
            <th>Sample ID</th>
            <th>Target</th>
            <g:each in="${qcSettings.yeast}" var="setting">
                <th class="text-right ${setting.key}">
                    ${setting.name}                                     
                </th>
            </g:each>
            <th>Recommend</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${runStatusMap.value.sampleStatusList}" var="sample">
            <tr>
                <td class="sample" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                    <g:if test="${n>0}"><tr></g:if>
                    <td class="target">${sample.target}</td>
                    <g:each in="${qcSettings.yeast}" var="setting">
                        <td class="text-right ${setting.key}">
                            <g:if test="${setting.numFormat && setting.numFormat != ''}">
                                <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                            </g:if>
                            <g:else>
                                ${alignment[setting.key]}
                            </g:else>
                    </g:each>
                </g:each>
                <td class="recommend"></td>
            </tr>
        </g:each>
    </tbody>
</table>

<script>
    $("#yeast tbody tr").each(function(){
        var v = {};
        $(this).children("td").each(function(index, elem) {
            var classes =  $(elem).attr("class").split(' ');
            for (n in classes) {
                if (classes[n] != "text-right") {
                    v[classes[n]] = $(elem).text().trim();
                }
            }            
        });
        $(this).find(".recommend").text(v.target);
        var target = v.target.toUpperCase();
        if ( target == "NOTAG" || target == "NOTARGET") {
            recommend = "";
        } else {
            if (v.dedupUniqReads < 200000) {
                if ((v.mappedReadPct > 0.5) && (v.adapterDimerPct < 0.15) && (v.duplicLevel < 0.7)) {
                    recommend="<span class='label label-danger'>Re-sequence<span>"; // dedup
                } else {
                    if (v.stress != "") {
                        recommend="Done; stress gene"; // stress
                    } else if((v.polIILevel < 0.1) && (v.fpkm < 0.1)) {
                        recommend="<span class='label label-success'>Done; low exprs<span>"; // polII & fpkm
                    } else {
                        recommend="<span class='label label-danger'>re-ChIP</span>"; // dedup & mapped& adapter& duplic
                    }
                }
            } else {
                if ((v.stamp =="Yes") || (v.multiGPS > 25) || (v.peakPairs > 50) || (v.nucleosomeEnrichment > 1.5) || (v.enrichedSegments != "")) {
                    recommend="<span class='label label-success'>Done; success</span>"; 
                } else if (v.stress != "") {
                    recommend="Done; stress gene"; //stress
                    $(this).find(".stress").addClass("bg-danger");
                } else if (v.polIILevel < 0.1 && v.exprsLevel < 0.1) {
                    recommend="Done; low exprs"; // pol II exprs
                } else {
                    recommend="Done; failed"; // stamp, multiGPS, peakPairs, nucleo   
                }
            }
        }
        $(this).find(".recommend").html(recommend);
    });
</script>