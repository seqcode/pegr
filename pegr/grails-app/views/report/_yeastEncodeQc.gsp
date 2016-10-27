<table id="yeast" class="table table-bordered">
    <thead>
        <tr>
            <th>Sample ID</th>
            <th>Target</th>
            <g:each in="${qcSettings.yeast}" var="setting">
                <th class="text-right ${setting.key}" style="white-space:normal">${setting.name}</th>
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
                        <td class="text-right ${setting.key}" title="${setting.key}">              
                            <g:if test="${setting.numFormat && setting.numFormat != ''}">
                                <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                            </g:if>
                            <g:else>
                                ${alignment[setting.key]}
                            </g:else>
                        </td>
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
                    $(this).find(".dedupUniqReads").addClass("bg-danger");
                } else {
                    if (v.go != "") {
                        recommend="<span class='label label-warning'>Done; stress gene</span>"; // go
                        $(this).find(".go").addClass("bg-danger");
                    } else if((v.polIILevel < 0.1) && (v.exprsLevel < 0.1)) {
                        recommend="<span class='label label-warning'>Done; low exprs<span>"; // polII & expression
                        $(this).find(".polIILevel").addClass("bg-danger");
                        $(this).find(".exprsLevel").addClass("bg-danger");
                    } else {
                        recommend="<span class='label label-danger'>re-ChIP</span>"; // dedup & mapped& adapter& duplic
                        $(this).find(".dedupUniqReads").addClass("bg-danger");
                        $(this).find(".mappedReadPct").addClass("bg-danger");
                        $(this).find(".adapterDimerPct").addClass("bg-danger");
                        $(this).find(".duplicLevel").addClass("bg-danger");
                    }
                }
            } else {
                if ((v.stamp =="Yes") || (v.multiGPS > 25) || (v.peakPairs > 50) || (v.nucleosomeEnrichment > 1.5) || (v.enrichedSegments != "")) {
                    recommend="<span class='label label-success'>Done; success</span>"; 
                } else if (v.go != "") {
                    recommend="<span class='label label-warning'>Done; stress gene</span>"; //go
                    $(this).find(".go").addClass("bg-danger");
                } else if (v.polIILevel < 0.1 && v.exprsLevel < 0.1) {
                    recommend="<span class='label label-warning'>Done; low exprs</span>"; // pol II exprs
                    $(this).find(".polIILevel").addClass("bg-danger");
                    $(this).find(".exprsLevel").addClass("bg-danger");
                } else {
                    recommend="<span class='label label-danger'>Done; failed</span>"; // stamp, multiGPS, peakPairs, nucleo
                    $(this).find(".stamp").addClass("bg-danger");
                    $(this).find(".multiGPS").addClass("bg-danger");
                    $(this).find(".peakPairs").addClass("bg-danger");
                    $(this).find(".nucleosomeEnrichment").addClass("bg-danger");
                }
            }
        }
        $(this).find(".recommend").html(recommend);
    });
    
    $("td.memER").each(function(index, memeFig){
        var memER = $(memeFig).text().trim();
        if (memER != "") {
            $.ajax({
                url: "/pegr/report/fetchMemERDataAjax?url="+memER,
                success: function(result) {
                    $(memeFig).empty();
                    make_motif(memeFig, result);
                }
            });
        }
    });
</script>