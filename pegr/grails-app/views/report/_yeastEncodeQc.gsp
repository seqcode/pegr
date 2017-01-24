<g:link class="edit pull-right" controller="report" action="decisionTree" params="[type:'YEAST_QC']" target="_blank">Decision Tree</g:link>
<table id="yeast" class="table table-bordered">
    <thead>
        <tr>
            <th rowspan="2">Sample ID</th>
            <th rowspan="2">Target</th>
            <g:each in="${headers.yeast}" var="header">
                <th class="text-right" style="white-space:normal" rowspan="${header.rowspan}" colspan="${header.colspan}">${header.name}</th>
            </g:each>
            <th rowspan="2">Recommend<br><span class="edit" id="calculate-recommend">Calculate</span><i id="recommend-spin" class="fa fa-spinner fa-spin"></i></th>
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
                <td class="sample" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link> ${sample.naturalId}</td>
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
                    <td class="recommend" data-sample-id="${sample.sampleId}"><span class="value" style='padding:.2em .5em;fond-weight:bold;line-height:1;white-space:nowrap;display:inline;color: white;'>${alignment.recommend}</span></td>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>

<script>
    $("#recommend-spin").hide();
    var colors;
    $.ajax({
            url:"/pegr/report/getDecisionTreeAjax?type=YEAST_QC",
            success: function(result) {
                var treeData = result.treeData;
                colors = result.colors;
                $(".recommend").each(function() {
                    var text = $(this).text();
                    if (text) {
                        $(this).find("span").css('background-color', colors[text])
                    }
                });
            }
     });
    
    $("#calculate-recommend").on("click", function(){
        $(this).hide();
        $("#recommend-spin").show();
        var data = [];
        $.ajax({
            url:"/pegr/report/getDecisionTreeAjax?type=YEAST_QC",
            success: function(result) {
                var treeData = result.treeData;
                var colors = result.colors;
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
                        recommend = getDecision(v, treeData);
                    }
                    $(this).find(".recommend").html("<span style='padding:.2em .5em;fond-weight:bold;line-height:1;white-space:nowrap;display:inline;color: white; background-color:" + colors[recommend.name] + "'>" + recommend.name + "</span>");
                    if (recommend.marks) {
                        for ( n in recommend.marks){
                            $(this).find("."+recommend.marks[n]).css("background-color", "#f2dede");
                        }
                    }
                    var sampleId = Number($(this).find(".recommend").attr("data-sample-id"));
                    data.push({ sampleId: sampleId, name: "recommend", value: recommend.name});
                });
                $("#recommend-spin").hide();
                $("#calculate-recommend").show();
                // save the recommedations
                $.ajax({
                    method: "POST",
                    url: "/pegr/sample/updateListAjax",
                    data: {"sampleList": JSON.stringify(data)}
                });
        }});
    });
    
    $(".recommend .value").on("click", function() {
        var value = $(this).text();
        var edit = "<select class='input' name='recommend'><option>Done; success</option><option>Done; stress gene</option><option>Done; low exprs</option><option>Done; failed</option><option>Re-sequence</option><option>re-ChIP</option></select>";
        appendEdit(this, edit);
    });
    
    $(".recommend").on("click", ".cancel", function(){
        var parent = $(this).parent();
        toggleTd(parent);
    });

    $(".recommend").on("click", ".save", function(){
        var parent = $(this).parent();
        var sampleId = parent.attr("data-sample-id");
        var recommend = parent.find(".input").val();
        // save the recommedations
        $.ajax({
            method: "POST",
            url: "/pegr/sample/updateAjax",
            data: {sampleId: sampleId, name: "recommend", value: recommend},
            success: function() {
                var $value = parent.find(".value");
                $value.text(recommend);
                $value.css('background-color', colors[recommend]);
                toggleTd(parent);
            }
        });
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
    
    function getDecision(v, node) {
        if (node.name) {
            return node
        } else {
            var result = decide(v, node);
            var flag = result ? "yes" : "no";
            var child
            for  (var j =0; j < node.children.length; j++) {
                if (node.children[j].flag == flag) {
                    child = node.children[j];
                    break
                }
            }
            return getDecision(v, child);
        }
        
    }
    
    function decide(v, node) {
        var result
        if (node.logic == "AND") {
            result = true
            for (i=0; i<node.conditions.length; ++i) {
                if (!test(node.conditions[i], v)) {
                    result = false
                    break
                }
            }
        } else if (node.logic == "OR") {
            result = false
            for (i=0; i<node.conditions.length; ++i) {
                if (test(node.conditions[i], v)) {
                    result = true
                    break
                }
            }
        } else {
            result = test(node.conditions[0], v);
        }
        return result
    }
    
    function test(c, v) {
        var result = false;
        var value = v[c.p];
        if (c.op == ">") {
            if (value != null) {
                value = parseFloat(value.replace(/,/g, ""));
                result = (value > c.v);
            } 
        } else if (c.op == "<") {
            if (value != null) {
                value = parseFloat(value.replace(/,/g, ""));
                result = (value < c.v);
            }
        } else if (c.op == "=") {
            result = (value == c.v);
        } else {
            result = (value != "");
        } 
        return result;
    }
</script>