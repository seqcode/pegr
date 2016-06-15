<div>
    <label>Genus</label>
    <select class="genus tag-select2" name="genus" style="width: 150px" required>
        <option value="${cellSource?.genus}" selected>${cellSource?.genus}</option>
    </select>
</div>

<div>
	<label>Species</label>
	<select name="speciesId" class="species tag-select2" style="width: 150px" required>
        <option value="${cellSource?.speciesId}" selected>${cellSource?.speciesId}</option>
    </select>
</div>

<div>
    <label>Parent Strain</label>
    <select name="parentStrain" class="parent-strain tag-select2" style="width: 150px" required>
        <option value="${cellSource?.parentStrain}" selected>${cellSource?.parentStrain}</option>
    </select>
</div>

<div>
	<label>Strain</label>
    <select name="strain" class="strain tag-select2 textcontrol" style="width: 150px" required>
        <option value="${cellSource?.strain}" selected>${cellSource?.strain}</option>
    </select>
</div>
   
<div>
    <label>Genotype</label>
    <select name="genotype" class="genotype tag-select2" style="width: 300px">
        <option value="${cellSource?.genotype}" selected>${cellSource?.genotype}</option>
    </select>
</div>

<div>
    <label>Genetic Modifications</label>
    <select name="mutation" class="mutation tag-select2 textcontrol" style="width: 150px">
        <option value="${cellSource?.mutation}" selected>${cellSource?.mutation}</option>
    </select>
</div>

<div>
    <label>Tissue</label>
    <g:select name="tissue" from="${pegr.Tissue.list()}" optionKey="name" noSelection="['':'']" value="${cellSource?.tissue}" class="tag-select2" style="width: 150px"></g:select>
</div>

<div>
    <label>Age</label>
    <g:textField id="age" name="age" value="${cellSource?.age}"/>

</div>

<div>
    <label>Sex </label>
    <g:select name="sex" from="${pegr.Sex.list()}" optionKey="name" noSelection="['':'']" value="${cellSource?.sex}" class="tag-select2" style="width: 150px"></g:select>
</div>

<div>
    <label>Histology</label>
    <g:select name="histology" from="${pegr.Histology.list()}" optionKey="name" noSelection="['':'']" value="${cellSource?.histology}" class="tag-select2" style="width: 150px"></g:select>
</div>

<div>
    <label>Growth Media</label>
    <select name="growthMedia" class="growth-media tag-select2 textcontrol" style="width: 150px" required>
        <option value="${cellSource?.growthMedia}" selected>${cellSource?.growthMedia}</option>
    </select>
</div>

<div>
    <label>Treatments</label>
    <span id="treatments">
    <select multiple="multiple" class="treatments tag-select2 textcontrol" name="treatments" style="width: 300px">
        <g:each in="${cellSource?.treatments}">
            <option value="${it}">${it}</option>
        </g:each>        
    </select>
    <a href="#" class="edit" data-toggle="modal" data-target="#new-treatment">+New</a>
    </span>
</div>

<div>
    <label>Provider User </label>
    <g:select id="providerUser" name="providerUserId" from="${pegr.User.list()}" optionKey="id" value="${cellSource?.providerUser?.id}" noSelection="['null': '']" class="no-tag-select2"/>
</div>

<div>
    <label>Provider Lab</label>
    <g:select id="providerLab" name="providerLabId" from="${pegr.Lab.list()}" optionKey="id" value="${cellSource?.providerLab?.id}" noSelection="['null': '']" class="no-tag-select2"/>
</div>

<div>
    <label>Biological Source ID</label>
    <g:textField name="biologicalSourceId" maxlength="50" value="${cellSource?.biologicalSourceId}"/>
</div>

<script>
    var tagPlaceholder = "Select or type...";
    var noTagPlaceholder = "Select...";
    
    $(document).ready(function(){
        $("#nav-projects").addClass("active");
        initializeSelect2s();
        $("form").validate();
    });
    
    // select2 initialize
    function initializeSelect2s() {
        $(".tag-select2").select2({
            placeholder: tagPlaceholder,
            tags: true
        });

        $(".no-tag-select2").select2({
            placeholder: noTagPlaceholder
        });

        $.ajax({url: "/pegr/cellSource/fetchUserAjax", success: function(result) {
            $(".provider").select2({
                data: result,
                placeholder: noTagPlaceholder
            });
            $(".sendTo").select2({
                data: result,
                placeholder: noTagPlaceholder
            });
        }});

        $.ajax({url: "/pegr/cellSource/fetchGenusAjax", success: function(result) {
            $(".genus").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }})

        $.ajax({url: "/pegr/cellSource/fetchTreatmentsAjax", success: function(result) {
            $(".treatments").select2({
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
    
    // ajax calls in cascade selections
    $(".genus").on("change", function() {
        var genus = $(this).val();
        
        var $species = $(".species");        
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
        
        var $parentStrain = $(".parent-strain");
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
        
        var $growthMedia = $(".growth-media");
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
        
        var $genomes = $(".genomes");
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
        var speciesId = $(".species").val();
        
        var $strain = $(".strain")
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
        
        var $genotype = $(".genotype");
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
        var strainName = $(".strain").val();
        var genotype = $(this).val();
        
        $mutation = $(".mutation");
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
</script>
