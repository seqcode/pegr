<button class="edit pull-right" onclick="window.open('/pegr/report/yeastDecisionTree', 'Yeast Decision Tree', 'width=600,height=400')">Decision Tree</button>
<table id="yeast" class="table table-bordered">
    <thead>
        <tr>
            <th rowspan="2">Sample ID</th>
            <th rowspan="2">Target</th>
            <g:each in="${headers.yeast}" var="header">
                <th class="text-right" style="white-space:normal" rowspan="${header.rowspan}" colspan="${header.colspan}">${header.name}</th>
            </g:each>
            <th rowspan="2">Recommend</th>
        </tr>
        <tr>
            <g:each in="${subheaders.yeast}" var="subheader">
                <th class="text-right" style="white-space:normal">${subheader}</th>
            </g:each>
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
                            <g:if test="${setting.key == 'memER'}">
                                <span style="display:none">${alignment[setting.key]}</span>
                            </g:if>
                            <g:elseif test="${setting.numFormat && setting.numFormat != ''}">
                                <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                            </g:elseif>
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
            if (parseFloat(v.dedupUniquelyMappedReads.replace(/,/g, "")) < 200000) {
                if ((parseFloat(v.mappedPct) > 0.5) && (parseFloat(v.adapterDimerPct) < 0.15) && (parseFloat(v.duplicationLevel) < 0.7)) {
                    recommend="<span class='label label-danger'>Re-sequence<span>"; // dedup
                    $(this).find(".dedupUniquelyMappedReads").addClass("bg-danger");
                } else {
                    if (v.go != "") {
                        recommend="<span class='label label-warning'>Done; stress gene</span>"; // go
                        $(this).find(".go").addClass("bg-danger");
                    } else if((parseFloat(v.polIILevel) < 0.1) && (parseFloat(v.exprsLevel) < 0.1)) {
                        recommend="<span class='label label-warning'>Done; low exprs<span>"; // polII & expression
                        $(this).find(".polIILevel").addClass("bg-danger");
                        $(this).find(".exprsLevel").addClass("bg-danger");
                    } else {
                        recommend="<span class='label label-danger'>re-ChIP</span>"; // dedup & mapped& adapter& duplic
                        $(this).find(".dedupUniquelyMappedReads").addClass("bg-danger");
                        $(this).find(".mappedPct").addClass("bg-danger");
                        $(this).find(".adapterDimerPct").addClass("bg-danger");
                        $(this).find(".duplicationLevel").addClass("bg-danger");
                    }
                }
            } else {
                if ((v.stamp =="Yes") || (parseFloat(v.multiGPS) > 25) || (parseFloat(v.sigPeakPairs.replace(/,/g, "")) > 50) || (parseFloat(v.nucleosomeEnrichment) > 1.5) || (v.enrichedSegments != "")) {
                    recommend="<span class='label label-success'>Done; success</span>"; 
                } else if (v.go != "") {
                    recommend="<span class='label label-warning'>Done; stress gene</span>"; //go
                    $(this).find(".go").addClass("bg-danger");
                } else if (parseFloat(v.polIILevel) < 0.1 && parseFloat(v.exprsLevel) < 0.1) {
                    recommend="<span class='label label-warning'>Done; low exprs</span>"; // pol II exprs
                    $(this).find(".polIILevel").addClass("bg-danger");
                    $(this).find(".exprsLevel").addClass("bg-danger");
                } else {
                    recommend="<span class='label label-danger'>Done; failed</span>"; // stamp, multiGPS, peakPairs, nucleo
                    $(this).find(".stamp").addClass("bg-danger");
                    $(this).find(".multiGPS").addClass("bg-danger");
                    $(this).find(".sigPeakPairs").addClass("bg-danger");
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
                    make_motif(memeFig, result);
                }
            });
        }
    });
</script>