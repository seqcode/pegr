<html>
<head>
    <title>PEGR - Experiments</title> 
    <meta name="layout" content="base"/>
</head>
<body>
    <div class="container-fluid">
    <h4>Samples</h4>
    <g:form controller="sample" action="batchUpdate">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Sample ID</th>
                    <th>Barcode</th>
                    <th>Strain</th>
                    <th>Genotype</th>
                    <th>Antibody</th>
                    <th>Target Type</th>
                    <th>Target</th>
                    <th>C-Term</th>
                    <th>N-Term</th>
                    <th>Index</th>
                    <th>Reference Genome(s)</th>
                    <th>Growth Media</th>
                    <th>Treatments</th>
                    <th>Chrom.(ug)</th>
                    <th>Cell#(M)</th>
                    <th>Volume(ul)</th>
                    <th>Requested Tags(M)</th>
                    <th>Send data to</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${samples}" var="sample" status="n">
                    <tr>
                        <g:hiddenField name="samples[${n}]"></g:hiddenField>
                        <td><g:link controller="sample" action="show" id="${sample.id}" class="sample-id">${sample.id}</g:link></td>
                        <td>${sample.item?.barcode}</td>
                        <td>${sample.cellSource?.strain?.name}</td>
                        <td>${sample.cellSource?.strain?.genotype}</td>
                        <td>${sample.antibody}</td>
                        <td>
                            <g:select name="samples[${n}].targetType" value="${sample.target?.targetType}" from="${pegr.TargetType.list()}" optionKey="name" noSelection="['': '']" class="target-type tag-select2" style="width: 150px"></g:select>
                        </td>
                        <td>
                            <select name="samples[${n}].target" value="${sample.target?.name}" class="target tag-select2 textcontrol" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select name="samples[${n}].cterm" value="${sample.target?.cTermTag}" class="cterm tag-select2" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select name="samples[${n}].nterm" value="${sample.target?.nTermTag}" class="nterm tag-select2" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td><input name="samples[${n}].index" value="${sample.sequenceIndicesString}"></td>
                        <td>
                            <g:select multiple="multiple" name="samples[${n}].genomes" from="${pegr.Genome.list()}" optionKey="name" value="${sample.requestedGenomes}" class="genomes no-tag-select2" style="width: 150px"></g:select>
                        </td>
                        <td>
                            <select name="samples[${n}].growthMedia" class="growth-media tag-select2 textcontrol" style="width: 150px" required>
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select multiple="multiple" class="treatments tag-select2" name="samples[${n}].treatments" style="width: 300px">
                                <option></option>
                            </select>
                        </td>
                        <td><g:textField name="samples[${n}].chrom" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[${n}].cellNum" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[${n}].volume" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[${n}].requestedTags" class="isnumber"></g:textField></td>
                        <td><g:select class="sendTo no-tag-select2" name="samples[${n}].sendToId" style="width: 150px" from="${pegr.User.list()}" optonKey="id"></g:select></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
        <span onclick="window.close();" class="btn btn-default">Cancel</span>
    </g:form>
    </div>
    <script>
        var tagPlaceholder = "Select or type...";
        var noTagPlaceholder = "Select...";

        $(document).ready(function(){
            $("#nav-projects").addClass("active");
            initializeSelect2s();
            $("form").validate();
        
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


            $.ajax({url: "/pegr/sample/fetchGrowthMediaAjax?speciesId="+speciesId, success: function(medias){
                $growthMedia.select2({
                    data: medias,
                    tags: true,
                    placeholder: tagPlaceholder
                });
            }});

            $.ajax({url: "/pegr/cellSource/fetchGenomeAjax?speciesId="+speciesId, success: function(genomes){
                $genomes.select2({
                    data: genomes,
                    placeholder: noTagPlaceholder
                });
            }});
        });
    </script>
</body>
</html>