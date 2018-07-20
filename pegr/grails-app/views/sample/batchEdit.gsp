<html>
<head>
    <title>PEGR - Experiments</title>
    <meta name="layout" content="main"/>
    <style>
        .index .value {
            white-space: nowrap;
        }
        .btn {
            padding: 0 5px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <h4>Samples</h4>
        <p><span class="glyphicon glyphicon-minus-sign"></span> Hide the column;<span id="column-toggle"> <span class="glyphicon glyphicon-plus-sign"></span> Show all columns </span></p>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="sampleId">Sample ID</th>
                    <th class="naturalId">Natural ID</th>
                    <th class="barcode">Barcode</th>
                    <th class="strain">Strain</th>
                    <th class="genotype">Genotype</th>
                    <th class="antibody">Antibody</th>
                    <th class="target-type">Target Type</th>
                    <th class="target">Target</th>
                    <th class="cterm">C-Term</th>
                    <th class="nterm">N-Term</th>
                    <th class="index" colspan="2">Index</th>
                    <th class="genomes">Reference Genome(s)</th>
                    <th class="growthMedia">Growth Media</th>
                    <th class="treatments">Treatments</th>
                    <th class="chromosomeAmount">Chrom.(ug)</th>
                    <th class="cellNumber">Cell#(M)</th>
                    <th class="volume">Volume(ul)</th>
                    <th class="requestedTagNumber">Requested Tags(M)</th>
                    <th class="send">Send data to</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${samples}" var="sample" status="n">
                    <tr>
                        <input type="hidden" class="sampleId" name="sampleId" value="${sample.id}">
                        <input type="hidden" class="speciesId" name="speciesId" value="${sample.cellSource?.strain?.species?.id}">
                        <td class="sampleId"><g:link controller="sample" action="show" id="${sample.id}" class="sample-id">${sample.id}</g:link></td>
                        <td class="naturalId"><span class="value">${sample.naturalId}</span></td>
                        <td class="barcode">${sample.item?.barcode}</td>
                        <td class="strain">${sample.cellSource?.strain?.name}</td>
                        <td class="genotype">${sample.cellSource?.strain?.genotype}</td>
                        <td class="antibody">${sample.antibody}</td>
                        <td class="group-target target-type"><span class="value">${sample.target?.targetType}</span></td>
                        <td class="group-target target"><span class="value">${sample.target?.name}</span></td>
                        <td class="group-target cterm"><span class="value">${sample.target?.cTermTag}</span></td>
                        <td class="group-target nterm"><span class="value">${sample.target?.nTermTag}</span></td>
                        <td class="index index-sequence"><span class="value">${sample.sequenceIndicesString}</span></td>
                        <td class="index index-id"><span class="value">${sample.sequenceIndicesIdString}</span></td>
                        <td class="genomes"><span class="value">${sample.requestedGenomes}</span></td>
                        <td class="growthMedia"><span class="value">${sample.growthMedia}</span></td>
                        <td class="treatments"><span class="value">${sample.treatments.join(", ")}</span></td>
                        <td class="group-input chromosomeAmount"><span class="value">${sample.chromosomeAmount}</span></td>
                        <td class="group-input cellNumber"><span class="value">${sample.cellNumber}</span></td>
                        <td class="group-input volume"><span class="value">${sample.volume}</span></td>
                        <td class="group-input requestedTagNumber"><span class="value">${sample.requestedTagNumber}</span></td>
                        <td class="send"><input type="hidden" class="key" value="${sample.sendDataTo?.id}"><span class="value">${sample.sendDataTo.username}</span></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <script>
        var tagPlaceholder = "Select or type...";
        var noTagPlaceholder = "Select...";
        var users, treatments, targets;
        var s0 = "NONE";

        $(".value").each(function(){
            if ($(this).text() == "") {
                $(this).text(s0);
            }
        });

        $.ajax({url: "/pegr/sample/fetchTreatmentsAjax", success: function(result) {
            treatments = result;
        }});

        $.ajax({ url: "/pegr/user/fetchUserAjax", success: function(result) {
            users = result;
        }});

        $.ajax({url: "/pegr/antibody/fetchTargetAjax", success: function(result){
            targets = result;
        }});

        $("th").each(function(){
                $(this).append(" <span class='glyphicon glyphicon-minus-sign small'></span>");
            });
        $(".glyphicon-minus-sign").click(function() {
            var c = $(this).parent().attr("class");
            $("."+c).hide();
        });

        $(".glyphicon-plus-sign").click(function() {
            $("th").show();
            $("td").show();
        });

        $("td").on("click", ".cancel", function() {
            var td = $(this).parent();
            toggleTd(td);
        });

        $("td.group-target").on("click", ".value", function(){
            var tr = $(this).closest("tr");
            createSelect(tr, ".target-type", targets.types);
            createSelect(tr, ".cterm", targets.targets);
            createSelect(tr, ".nterm", targets.cterms);
            createSelect(tr, ".target", targets.nterms);
            var $save = "<button class='btn btn-primary save'>Save</button>";
            var $cancel = "<button class='btn btn-default cancel-target'>Cancel</button>";
            var td = tr.find(".target-type");
            td.append($save);
            td.append($cancel);
        });

        $("td").on("click", ".cancel-target", function() {
            var tr = $(this).closest("tr");
            tr.find(".group-target .value").show();
            tr.find(".group-target .input").remove();
            tr.find(".group-target .cancel-target").remove();
            tr.find(".group-target .save").remove();
        });

        $("td.group-target").on("click", ".save", function(){
            var tr = $(this).closest("tr");
            var sampleId = tr.find(".sampleId").val();
            var type = tr.find(".target-type select").val();
            var name = tr.find(".target select").val();
            var cterm = tr.find(".cterm select").val();
            var nterm = tr.find(".nterm select").val();
            var map = {"type":type,
                      "name":name,
                      "cterm":cterm,
                      "nterm":nterm};
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "target", value: JSON.stringify(map)},
                success: function(result) {
                    tr.find(".target-type .value").text(type);
                    tr.find(".target .value").text(name);
                    tr.find(".cterm .value").text(cterm);
                    tr.find(".nterm .value").text(nterm);

                    tr.find(".group-target .value").show();
                    tr.find(".group-target .input").remove();
                    tr.find(".group-target .cancel-target").remove();
                    tr.find(".group-target .save").remove();
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            })
        });

        $("td.index").on("click", ".value", function(){
            var oldValue = $(this).text();
            var edit = "<input class='input' value='" + oldValue + "'>";
            appendEdit(this, edit);
        });

        $("td.index").on("click", ".save", function() {
            var td = $(this).parent();
            var tr = td.closest("tr");
            var value = td.find("input").val();
            if (value == s0) {
                value = "";
            }
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "index", value : value},
                success: function(result) {
                    var s = result.sequence;
                    var id = result.id;
                    if (s == null || s == "") s = s0;
                    if (id == null || id == "") id = s0;
                    tr.find(".index-sequence .value").text(s);
                    tr.find(".index-id .value").text(id);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        $("td.genomes").on("click", ".value", function() {
            var td = $(this).parent();
            var oldValue = $(this).text();
            var edit = "<span class='input'><select multiple='multiple' style='width:200px'>";
            $.each(oldValue.split(","), function (index, value) {
                if (value != "NONE") {
                    edit += "<option selected value='" + value + "'>" + value + "</option>";
                }
            });
            edit += "</select></span>";
            appendEdit(this, edit);
            var speciesId = $(this).closest("tr").find(".speciesId").val();
            $.ajax({
                url: "/pegr/cellSource/fetchGenomeAjax?speciesId="+speciesId
            }).done(function(result){
                td.find("select").select2({
                    data: result,
                    placeholder: noTagPlaceholder,
                });
            });
        });

        $("td.genomes").on("click", ".save", function() {
            var td = $(this).parent();
            var value = td.find("select").val();
            var valueToSend = null;
            if (value) {
                valueToSend = value.join(",");
            }
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "genomes", value : valueToSend},
                success: function() {
                    var s = s0;
                    if (value) {
                        s = value.join(",");
                    }
                    td.find(".value").text(s);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        $("td.growthMedia").on("click", ".value", function(){
            var td = $(this).parent();
            var oldValue = $(this).text();
            var edit = "<span class='input'><select style='width:200px; display:none'><option selected value='" + oldValue + "'>" + oldValue + "</option></select></span>";
            appendEdit(this, edit);
            var speciesId = $(this).closest("tr").find(".speciesId").val();
            $.ajax({
                url: "/pegr/sample/fetchGrowthMediaAjax?speciesId="+speciesId
            }).done(function(result){
                td.find("select").select2({
                    data: result,
                    placeholder: tagPlaceholder,
                    tags: true
                });
            });
        });

        $("td.growthMedia").on("click", ".save", function() {
            var td = $(this).parent();
            var value = td.find("select").val();
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "growthMedia", value : value},
                success: function() {
                    var s = s0;
                    if (value) {
                        s = value;
                    }
                    td.find(".value").text(s);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        $("td.treatments").on("click", ".value", function() {
            var oldValue = $(this).text();
            var edit = "<span class='input'><select multiple='multiple' style='width:200px'>";
            $.each(oldValue.split(", "), function (index, value) {
                if (value != "NONE") {
                    edit += "<option selected value='" + value + "'>" + value + "</option>";
                }
            });
            edit += "</select></span>";
            appendEdit(this, edit);
            $(this).parent().find("select").select2({
                data: treatments,
                placeholder: tagPlaceholder,
                tags: true
            });
        });

        $("td.treatments").on("click", ".save", function() {
            var td = $(this).parent();
            var value = td.find("select").val();
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "treatments", value : JSON.stringify(value)},
                success: function() {
                    var s = s0;
                    if (value) {
                        s = value.join(", ");
                    }
                    td.find(".value").text(s);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        // Editable naturalID field
        $("td.naturalId").on("click", ".value", function(){
            var oldValue = $(this).text();
            var edit = "<input class='input' value='" + oldValue + "'>";
            appendEdit(this, edit);
        });

        $("td.naturalId").on("click", ".save", function() {
            var td = $(this).parent();
            var tr = td.closest("tr");
            var value = td.find("input").val();
            if (value == s0) {
                value = "";
            }
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "naturalId", value : value},
                success: function() {
                    var s = s0;
                    if (value) {
                        s = value;
                    }
                    td.find(".value").text(s);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        $("td.group-input .value").click(function() {
            var oldValue = $(this).text();
            var edit = "<input class='input' value='" + oldValue + "'>" ;
            appendEdit(this, edit);
        });

        $("td.group-input").on("click", ".save", function(){
            var td = $(this).parent();
            var classes = td.attr("class").split(' ');
            var name;
            for (n in classes) {
                if (classes[n].substring(0,6) != "group-") {
                    name = classes[n];
                    break;
                }
            }
            var value = td.find("input").val();
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: name, value: value},
                success: function() {
                    var s = s0;
                    if (value) {
                        s = value;
                    }
                    td.find(".value").text(s);
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });

        $("td.send").on("click", ".value", function(){
            var oldKey = $(this).parent().find(".key").val();
            var oldValue = $(this).text();
            var edit = "<span class='input'><select>";
            if (oldKey) {
                edit += "<option value='" + oldKey + "'>" + oldValue + "</option>";
            }
            edit += "<option value='0'>NONE</option></select></span>";
            appendEdit(this, edit);
            $(this).parent().find("select").select2({
                data: users
            });
        });

        $("td.send").on("click", ".save", function(){
            var td = $(this).parent();
            var value = td.find("select").val();
            var sampleId = td.parent().find(".sampleId").val();
            $.ajax({
                type: "POST",
                url: "/pegr/sample/updateAjax",
                data: {sampleId: sampleId, name: "sendToId", value : value},
                success: function() {
                    var s = s0;
                    var selectedElem = td.find("select :selected")
                    if (selectedElem) {
                        s = selectedElem.text();
                    }
                    td.find(".value").text(s);
                    if (value == "0"){
                        td.find(".key").val(null);
                    } else {
                        td.find(".key").val(value);
                    }
                    toggleTd(td);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.responseText);
                }
            });
        });
    </script>
</body>
</html>
