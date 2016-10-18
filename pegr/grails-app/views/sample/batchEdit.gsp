<html>
<head>
    <title>PEGR - Experiments</title> 
    <meta name="layout" content="base"/>
</head>
<body>
    <div class="container-fluid">
        <h4>Samples</h4>
        <p><span class="glyphicon glyphicon-minus-sign"></span> Hide the column;<span id="column-toggle"> <span class="glyphicon glyphicon-plus-sign"></span> Show all columns </span></p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th class="sampleId">Sample ID</th>
                    <th class="barcode">Barcode</th>
                    <th class="strain">Strain</th>
                    <th class="genotype">Genotype</th>
                    <th class="antibody">Antibody</th>
                    <th class="target-type">Target Type</th>
                    <th class="target">Target</th>
                    <th class="cterm">C-Term</th>
                    <th class="nterm">N-Term</th>
                    <th class="index">Index</th>
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
                        <td class="barcode">${sample.item?.barcode}</td>
                        <td class="strain">${sample.cellSource?.strain?.name}</td>
                        <td class="genotype">${sample.cellSource?.strain?.genotype}</td>
                        <td class="antibody">${sample.antibody}</td>
                        <td class="group-target target-type"><span class="value">${sample.target?.targetType}</span></td>
                        <td class="group-target target"><span class="value">${sample.target?.name}</span></td>
                        <td class="group-target cterm"><span class="value">${sample.target?.cTermTag}</span></td>
                        <td class="group-target nterm"><span class="value">${sample.target?.nTermTag}</span></td>
                        <td class="index"><span class="value">${sample.sequenceIndicesString}</span></td>
                        <td class="genomes"><span class="value">${sample.requestedGenomes}</span></td>
                        <td class="growthMedia"><span class="value">${sample.growthMedia}</span></td>
                        <td class="treatments"><span class="value">${sample.treatments}</span></td>
                        <td class="group-input chromosomeAmount"><span class="value">${sample.chromosomeAmount}</span></td>
                        <td class="group-input cellNumber"><span class="value">${sample.cellNumber}</span></td>
                        <td class="group-input volume"><span class="value">${sample.volume}</span><button class='btn btn-default change-cancel'>Cancel</button></td>
                        <td class="group-input requestedTagNumber"><span class="value">${sample.requestedTagNumber}</span></td>
                        <td class="send"><span class="value">${sample.sendDataTo}</span></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <script>
        var tagPlaceholder = "Select or type...";
        var noTagPlaceholder = "Select...";
        var $cancel = "<button class='btn btn-default change-cancel'>Cancel</button>";
        
        $(document).ready(function(){
            $("th").each(function(){
                $(this).append(" <span class='glyphicon glyphicon-minus-sign small'></span>");
            });
            
            /*           
            $(".tag-select2").select2({
                placeholder: tagPlaceholder,
                tags: true
            });

            $(".no-tag-select2").select2({
                placeholder: noTagPlaceholder
            });

            $.ajax({url: "/pegr/sample/fetchTreatmentsAjax", success: function(result) {
                $(".treatments").select2({
                    data: result,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});

            $.ajax({url: "/pegr/antibody/fetchTargetAjax", success: function(result){
                $(".target").select2({
                    data: result.targets,
                    tags: true,
                    placeholder: tagPlaceholder
                });
                $(".nterm").select2({
                    data: result.nterms,
                    tags: true,
                    placeholder: tagPlaceholder
                });
                $(".cterm").select2({
                    data: result.cterms,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});
            */
        });
        
        $(".glyphicon-minus-sign").click(function() {
            var c = $(this).parent().attr("class");
            $("."+c).hide();
        });

        $(".glyphicon-plus-sign").click(function() {
            $("th").show();  
            $("td").show();
        });

        $("td.group-input .value").click(function() {
            var oldValue = $(this).text();
            var td = $(this).parent();
            td.find(".value").hide();
            td.append("<input>");
            var $save = "<button class='btn btn-primary change-save'>Save</button>";
            td.append($save);
            td.append($cancel);
        });
        
        $(".change-cancel").on("click", function() {
            var td = $(this).parent();
            
            //td.find(".value").show();
            td.find(".change-cancel").remove();
            //td.find(".save").remove();
        });
    </script>
</body>
</html>