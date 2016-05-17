<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <style>
        .sample {
            background-image: none;
            background-color: #d9edf7;
        }
        .antibody {
            background-image: none;
            background-color: #FFFFCC;
        }
        .target {
            background-image: none;
            background-color: #FFD4CC;
        }
    </style>
</head>
<body>
<g:form action="saveNewSamples" method="post">
    <h4>Assay: ${assay?.name}</h4>
    <g:hiddenField name="assayId" value="${assay?.id}"></g:hiddenField>
    <g:hiddenField name="projectId" value="${project?.id}"></g:hiddenField>
    <ul class="nav nav-pills">
        <li><a href="#sample-group" class="btn btn-default">Sample</a></li>
        <li><a href="#antibody-group" class="btn btn-default">Antibody</a></li>
        <li><a href="#target-group" class="btn btn-default">Target</a></li>
    </ul>
    <div class="table-responsive">
        <table class="table table-striped table-bordered" style="margin-bottom: 200px">
            <thead>
                <tr>
                    <th></th>
                    <th colspan="17" id="sample-group" class="sample">Sample</th>
                    <th colspan="12" id="antibody-group" class="antibody">Antibody</th>
                    <th colspan="4" id="target-group" class="target">Target</th>
                </tr>
                <tr>
                    <th></th>
                    <th>Sample Provider</th>
                    <th>Send Data to</th>
                    <th>Genus</th>
                    <th>Species</th>
                    <th>Parent Strain</th>
                    <th>Strain</th>
                    <th>Genotype</th>
                    <th>Mutation</th>
                    <th>Tissue</th>
                    <th>Prep User</th>
                    <th>Growth Media</th>
                    <th>Treatments</th>                       
                    <th>Chrom. (ug)</th>
                    <th>Cell# (M)</th>
                    <th>Volume (ul)</th>
                    <th>Reference Genome(s)</th>
                    <th>Notes</th> 
                    <th>Company</th>
                    <th>Catalog</th>
                    <th>Lot #</th>
                    <th>Host</th>
                    <th>Immunogene</th>
                    <th>Mono/Poly</th>
                    <th>Ig Type</th>
                    <th>Conc.(ug/ul)</th>
                    <th>Notes</th>
                    <th>ul/sample sent</th>
                    <th>ug to use/std ChIP</th>
                    <th>ul to use/std ChIP</th>
                    <th>Target Type</th>
                    <th>Target</th>
                    <th>C-Term</th>
                    <th>N-Term</th>
                </tr>
            </thead>
            <tbody>
                <tr id="tr1">
                    <td><a href="#" class="removeRow"><span class="glyphicon glyphicon-trash"></span></a></td>
                    <td>
                        <select class="provider no-tag-select2" name="sample[].providerId" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select class="sendTo no-tag-select2" name="sample[].sendToId" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select class="genus tag-select2" name="sample[].genus" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].speciesId" class="species tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].parentStrain" class="parent-strain tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].strain" class="strain tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].genotype" class="genotype tag-select2" style="width: 300px">
                            <option></option>
                        </select></td>
                    <td>
                        <select name="sample[].mutation" class="mutation tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <g:select name="sample[].tissue" from="${pegr.Tissue.list()}" optionKey="id" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select class="prepUser no-tag-select2" name="sample[].prepUserId" style="width: 150px" >
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].growthMediaId" class="growth-media tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select multiple="multiple" class="treatments tag-select2" name="sample[].treatmentId" style="width: 300px">
                            <option></option>
                        </select>
                    </td>
                    <td><g:textField name="sample[].chrom"></g:textField></td>
                    <td><g:textField name="sample[].cellNum"></g:textField></td>
                    <td><g:textField name="sample[].volume"></g:textField></td>
                    <td>
                        <select multiple="multiple" name="sample[].genomeId" class="genomes no-tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td><g:textField name="note" style="width: 500px"></g:textField></td>

                    <td>
                        <select class="company tag-select2" name="sample[].companyId" style="width: 200px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select class="catalog tag-select2" name="sample[].catalogNumber" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <input name="sample[].lotNumber"/>
                    </td>
                    <td>
                        <g:select name="sample[].abHostId" from="${pegr.AbHost.list()}" optionKey="id" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select class="immunogene tag-select2" name="sample[].immunogene" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <g:select name="sample[].clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" noSelection="['': '']" class="no-tag-select2" style="width: 150px"/>
                    </td>
                    <td>
                        <g:select name="sample[].igTypeId" from="${pegr.IgType.list()}" optionKey="id" noSelection="['': '']" class="tag-select2" style="width: 150px"/>
                    </td>
                    <td><input name="sample[].concentration" /></td>
                    <td><input name="sample[].abNotes"></td>
                    <td><input name="sample[].abVolumePerSample"></td>
                    <td><input name="sample[].ugPerChip"></td>
                    <td><input name="sample[].ulPerChip"></td>
                    <td>
                        <g:select name="sample[].targetTypeId" from="${pegr.TargetType.list()}" optionKey="id" noSelection="['': '']" class="target-type tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select name="sample[].target" class="target tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].cterm" class="cterm tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="sample[].nterm" class="nterm tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    
    <button id="add" class="pull-right">Add Row</button>
    <div>
        <g:submitButton name="save" class="btn btn-primary confirm" value="Save"></g:submitButton>
        <g:link action="show" id="${project.id}" class="btn btn-default">Cancel</g:link>
    </div>
</g:form>
    
<script>    
    var tagPlaceholder = "Select or type...";
    var noTagPlaceholder = "Select...";
    
    $(document).ready(function(){
        $("#nav-projects").addClass("active");
        $(".confirm").confirm();
        initializeSelect2s(1);
    });
    
    // select2 initialize
    function initializeSelect2s(count) {
        $("#tr"+count+" .tag-select2").select2({
            placeholder: tagPlaceholder,
            tags: true
        });

        $("#tr"+count+" .no-tag-select2").select2({
            placeholder: noTagPlaceholder
        });

        $.ajax({url: "/pegr/cellSource/fetchUserAjax", success: function(result) {
            $("#tr"+count+" .provider").select2({
                data: result,
                placeholder: noTagPlaceholder
            });
            $("#tr"+count+" .sendTo").select2({
                data: result,
                placeholder: noTagPlaceholder
            });
            $("#tr"+count+" .prepUser").select2({
                data: result,
                placeholder: noTagPlaceholder
            });
        }});

        $.ajax({url: "/pegr/cellSource/fetchGenusAjax", success: function(result) {
            $("#tr"+count+" .genus").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }})

        $.ajax({url: "/pegr/cellSource/fetchTreatmentsAjax", success: function(result) {
            $("#tr"+count+" .treatments").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }})

        $.ajax({url: "/pegr/cellSource/fetchCompanyAjax", success: function(result) {
            $("#tr"+count+" .company").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }})

        $.ajax({url: "/pegr/cellSource/fetchCatalogAjax", success: function(result){
            $("#tr"+count+" .catalog").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});

        $.ajax({url: "/pegr/cellSource/fetchImmunogeneAjax", success: function(result){
            $("#tr"+count+" .immunogene").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
    }
    
    // disable the dependent fields in cascade selections
    $(".species").prop("disabled", true);
    $(".parent-strain").prop("disabled", true);
    $(".strain").prop("disabled", true);
    $(".genotype").prop("disabled", true);
    $(".mutation").prop("disabled", true);
    $(".growth-media").prop("disabled", true);
    $(".genomes").prop("disabled", true);
    $(".target-type").prop("disabled", true);
    $(".target").prop("disabled", true);
    $(".cterm").prop("disabled", true);
    $(".nterm").prop("disabled", true);
    
    // ajax calls in cascade selections
    $(".genus").on("change", function() {
        var genus = $(this).val();
        
        var $species = $(this).closest("tr").find(".species");        
        $species.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchSpeciesAjax?genus="+genus, success: function(result){
            $species.select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $species.prop("disabled", false);
    });
    
    $(".species").on("change", function() {
        var speciesId = $(this).val();
        
        var $parentStrain = $(this).closest("tr").find(".parent-strain");
        $parentStrain.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchParentStrainAjax?speciesId="+speciesId, success: function(parents){
            $parentStrain.select2({
                data: parents,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $parentStrain.prop("disabled", false);
        
        var $growthMedia = $(this).closest("tr").find(".growth-media");
        $growthMedia.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        
        $.ajax({url: "/pegr/cellSource/fetchGrowthMediaAjax?speciesId="+speciesId, success: function(medias){
            $growthMedia.select2({
                data: medias,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $growthMedia.prop("disabled", false);
        
        var $genomes = $(this).closest("tr").find(".genomes");
        $genomes.html('').select2({
            data: [{id: '', text: ''}],
        });
        $.ajax({url: "/pegr/cellSource/fetchGenomeAjax?speciesId="+speciesId, success: function(genomes){
            $genomes.select2({
                data: genomes,
                placeholder: noTagPlaceholder
            });
        }});
        $genomes.prop("disabled", false);
    });
            
    $(".parent-strain").on("change", function() {
        var parentStrain = $(this).val();
        var speciesId = $(this).closest("tr").find(".species").val();
        
        var $strain = $(this).closest("tr").find(".strain")
        $strain.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        
        $.ajax({url: "/pegr/cellSource/fetchStrainNameAjax?parentStrain="+parentStrain+"&speciesId="+speciesId, success: function(result){
            $strain.select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $strain.prop("disabled", false);
    });
    
    $(".strain").on("change", function(){
        var strainName = $(this).val();
        
        var $genotype = $(this).closest("tr").find(".genotype");
        $genotype.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchGenotypeAjax?strainName="+strainName, success: function(result){
            $genotype.select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $genotype.prop("disabled", false);
    });
    
    $(".genotype").on("change", function() {
        var strainName = $(this).closest("tr").find(".strain").val();
        var genotype = $(this).val();
        
        $mutation = $(this).closest("tr").find(".mutation");
        $mutation.html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        var strainName = $("#strain").val()
        $.ajax({url: "/pegr/cellSource/fetchMutationAjax?strainName="+strainName+"&genotype="+genotype, success: function(result){
            $mutation.select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $mutation.prop("disabled", false);
    });
    
    $(".immunogene").on("change", function() {
        var immunogene = $(this).val();
        
        var $targetType = $(this).closest("tr").find(".target-type");
        var $target = $(this).closest("tr").find(".target");
        var $cterm = $(this).closest("tr").find(".cterm");
        var $nterm = $(this).closest("tr").find(".nterm");
        $.ajax({url: "/pegr/cellSource/fetchDefaultTargetAjax?immunogene="+immunogene, success: function(result){
            $targetType.val(result.targetTypeId);
            $targetType.prop("disabled", false);
            
            $target.val(result.target);
            $cterm.val(result.cterm);
            $nterm.val(result.nterm);
        }});
    });
                
    $(".target-type").on("change", function() {
        var targetTypeId = $(this).val();
                
        var $target = $(this).closest("tr").find(".target");
        var $cterm = $(this).closest("tr").find(".cterm");
        var $nterm = $(this).closest("tr").find(".nterm");
        $.ajax({url: "/pegr/cellSource/fetchTargetAjax?targetTypeId="+targetTypeId, success: function(result){
            $target.select2({
                data: result.targets,
                tags: true,
                placeholder: tagPlaceholder
            });
            $nterm.select2({
                data: result.nterms,
                tags: true,
                placeholder: tagPlaceholder
            });
            $cterm.select2({
                data: result.cterms,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $target.prop("disabled", false);
        $nterm.prop("disabled", false);
        $cterm.prop("disabled", false);
    });

    // remove row
    $("a.removeRow").click(function(event){
        event.preventDefault();
        $(this).closest("tr").remove();
    });
    
    // add new row at the bottom and copy the value of last row
    var count = 1;
    $("#add").click(function() {
        var $orig = $("#tr"+count);
        count++;
        
        $('select', $orig).each(function(index){
           $(this).select2("destroy");
        });
        $('tbody').append(
            $('<tr/>')
                .attr("id", "tr"+count)
                .append(
                    $orig.children().clone(true))
        );

        var $cloned = $("#tr"+count)
        $('select', $cloned).each(function(index){
            $(this).val($('select', $orig).eq(index).val());
        });
        $('input', $cloned).each(function(index){
            $(this).val($('select', $orig).eq(index).val());
        });
        
        $('.tag-select2', $orig).each(function(index){
           $(this).select2({            
               tags: true,
               placeholder: tagPlaceholder});
        });
        $('.tag-select2', $cloned).each(function(index){
           $(this).select2({
                tags: true,
                placeholder: tagPlaceholder
           });
        });
           
        $('.no-tag-select2', $orig).each(function(index){
           $(this).select2({            
               tags: false,
               placeholder: noTagPlaceholder});
        });
        $('.no-tag-select2', $cloned).each(function(index){
           $(this).select2({
                tags: false,
                placeholder: noTagPlaceholder
           });
        });

        return false;
    });

</script>
</body>
</html>