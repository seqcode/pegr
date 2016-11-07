<g:link class="edit pull-right" controller="report" action="decisionTree" params="[type:'YEAST_QC']" target="_blank">Decision Tree</g:link>
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
        $(this).find(".recommend").html("<span style='padding:.2em .5em;fond-weight:bold;line-height:1;white-space:nowrap;display:inline;color: white; background-color:" + colors[recommend] + "'>" + recommend + "</span>");
        
    });
}});
    
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
            return node.name
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