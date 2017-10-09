<g:link class="edit pull-right" controller="report" action="decisionTree" params="[type:'YEP_QC']" id="_blank">Decision Tree</g:link>
<table id="yep" class="table table-bordered">
    <thead>
        <tr>
            <th rowspan="2">ID</th>
            <th rowspan="2">File Type</th>
            <th rowspan="2">URL </th>
            <g:each in="${headers.yep}" var="header">
                <th class="text-right" style="white-space:normal" rowspan="${header.rowspan}" colspan="${header.colspan}">${header.name}</th>
            </g:each>

        <tr>
            <g:each in="${subheaders.yep}" var="subheader">
                <th class="text-right" style="white-space:normal">${subheader}</th>
            </g:each>
        </tr>
    </thead>


    <tbody>
        <g:each in="${runStatusMap.value.sampleStatusList}" var="sample">
            <tr>
                <td class="id" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">
                    <g:each in = "${sample.alignmentStatusList.datasets_id}" var ="set"> 
                            <g:each in="${set}" var ="id" status ="index">
                                <tr> <td>${id}</td> 

                                    <td>
                                    ${sample.alignmentStatusList.datasets_type.get(0).get(index)}
                                    </td> 
                                
                                    <td>
                                    <a href = "http://galaxy-cegr.psu.edu:${sample.alignmentStatusList.datasets_url.get(0).get(index)}=True"/>
                                    http://galaxy-cegr.psu.edu:${sample.alignmentStatusList.datasets_url.get(0).get(index)}=True</a>
                                    </td> 
                                

                                </tr>
                            </g:each>
                    </g:each>
           
                </td>
            </tr>
        </g:each>
    </tbody>
</table>

<script>
    

    
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