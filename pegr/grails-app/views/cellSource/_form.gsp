<div>
    <label>Genus</label>
    <select class="genus tag-select2" name="genus" style="width: 150px" required>
        <option value="${cellSource?.genus}" selected>${cellSource?.genus}</option>
    </select>
</div>

<div>
	<label>Species</label>
	<select name="speciesId" class="species tag-select2" style="width: 150px" required>
        <option value="${cellSource?.speciesId}" selected>${cellSource?.speciesName}</option>
    </select>
</div>

<div>
    <label>Parent Strain</label>
    <select name="parentStrain" class="parent-strain tag-select2" style="width: 150px">
        <option value="${cellSource?.parentStrain}" selected>${cellSource?.parentStrain}</option>
    </select>
</div>

<div>
	<label>Strain</label>
    <select name="strain" class="strain tag-select2 textcontrol" style="width: 150px">
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
    <label>Provider User </label>
    <g:select name="providerId" from="${pegr.User.list()}" optionKey="id" value="${cellSource?.providerId}" noSelection="['null': '']" class="no-tag-select2"/>
</div>

<div>
    <label>Provider Lab</label>
    <g:select name="providerLabId" from="${pegr.Lab.list()}" optionKey="id" value="${cellSource?.providerLabId}" noSelection="['null': '']" class="no-tag-select2"/>
</div>

<div>
    <label>Biological Source ID</label>
    <g:textField name="bioSourceId" maxlength="50" value="${cellSource?.bioSourceId}"/>
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

        $.ajax({url: "/pegr/user/fetchUserAjax", success: function(result) {
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
        }});
        
        var genus = $(".genus").val();
        $.ajax({url: "/pegr/cellSource/fetchSpeciesAjax?genus="+genus, success: function(result){
            $(".species").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        
        var speciesId = $(".species").val();
        $.ajax({url: "/pegr/cellSource/fetchParentStrainAjax?speciesId="+speciesId, success:   function(parents){
            $(".parent-strain").select2({
                data: parents,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        
        var parentStrain = $(".parent-strain").val();
        $.ajax({url: "/pegr/cellSource/fetchStrainNameAjax?parentStrain="+parentStrain+"&speciesId="+speciesId, success: function(result){
            $(".strain").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        
        var strainName = $(".strain").val();
        $.ajax({url: "/pegr/cellSource/fetchGenotypeAjax?strainName="+strainName, success: function(result){
            $(".genotype").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        
        var genometype = $(".genotype").val();
        $.ajax({url: "/pegr/cellSource/fetchMutationAjax?strainName="+strainName+"&genotype="+genotype, success: function(result){
            $(".mutation").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
    }
    
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
