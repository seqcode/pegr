<div>
	<label for="company">Company</label>
	<select class="company" name="company" style="width: 200px">
        <option value="${antibody?.company}" selected>${antibody?.company}</option>
    </select>
</div>

<div>
	<label for="catalogNumber">Catalog Number</label>
	<select class="catalog textcontrol" name="catalogNumber" style="width: 150px" required>
        <option value="${antibody?.catalogNumber}" selected>${antibody?.catalogNumber}</option>
    </select>
</div>

<div>
	<label for="lotNumber">Lot Number</label>
	<g:textField name="lotNumber" value="${antibody?.lotNumber}"/>
</div>

<div>
	<label for="abHost">Ab Host</label>
	<g:select id="abHost" name="abHost" from="${pegr.AbHost.list()}" optionKey="name" noSelection="['':'']" class="tag-select2 host"  value="${antibody?.abHost}"/>
</div>

<div>
	<label for="immunogene">Immunogene</label>
    <select class="immunogene" name="immunogene" style="width: 150px">
        <option value="${antibody?.immunogene}" selected>${antibody?.immunogene}</option>
    </select>
</div>

<div>
	<label for="clonal">Mono/Poly Clonal</label>
	<g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${antibody?.clonal}"  noSelection="['': '']" class="no-tag-select2 clonal"/>
</div>

<div>
	<label for="igType">Ig Type</label>
	<g:select id="igType" name="igType" from="${pegr.IgType.list()}" optionKey="name" value="${antibody?.igType}" class="tag-select2 ig" noSelection="['null': '']"/>
</div>

<div>
	<label for="concentration">Concentration (ug/ul)</label>
	<g:textField name="concentration" value="${antibody?.concentration}" class="conc isnumber"/>
</div>

<h4>Default Target</h4>
<div>
    <label>Target Type</label>
    <g:select name="targetType" from="${pegr.TargetType.list()}" optionKey="name" noSelection="['': '']" value="${antibody?.targetType}" class="target-type tag-select2" style="width: 150px"></g:select>
</div>
<div>
    <label>Target</label>
    <select name="target" class="target tag-select2 textcontrol" style="width: 150px" required>
        <option value="${antibody?.target}">${antibody?.target}</option>
    </select>
</div>
<div>
    <label>C-Term</label>
    <select name="cterm" class="cterm tag-select2" style="width: 150px">
        <option value="${antibody?.cterm}">${antibody?.cterm}</option>
    </select>
</div>
<div>
    <label>N-Term</label>
    <select name="nterm" class="nterm tag-select2" style="width: 150px">
        <option value="${antibody?.nterm}">${antibody?.nterm}</option>
    </select>
</div>

<script>
    var tagPlaceholder = "Select or type...";
    var noTagPlaceholder = "Select...";
    // select2 initialize
    $(document).ready(function(){
        $(".tag-select2").select2({
            placeholder: tagPlaceholder,
            tags: true
        });

        $(".no-tag-select2").select2({
            placeholder: noTagPlaceholder
        });

        $.ajax({url: "/pegr/antibody/fetchCompanyAjax", success: function(result) {
            $(".company").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }})

        $.ajax({url: "/pegr/antibody/fetchCatalogAjax", success: function(result){
            $(".catalog").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});

        $.ajax({url: "/pegr/antibody/fetchImmunogeneAjax", success: function(result){
            $(".immunogene").select2({
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
        
        $("form").validate();
    });
    
    $(".catalog").on("change", function() {
        var catalog = $(this).val();
        
        var $host = $(".host");
        var $immunogene = $(".immunogene");
        var $clonal = $(".clonal");
        var $ig = $(".ig");
        var $conc = $(".conc");
        var $targetType = $(".target-type");
        var $target = $(".target");
        var $cterm = $(".cterm");
        var $nterm = $(".nterm");
        
        $.ajax({url: "/pegr/antibody/fetchAntibodyAjax?catalog="+catalog, success: function(result){
            $host.val(result.host).trigger("change");
            $immunogene.val(result.immunogene).trigger("change");
            $clonal.val(result.clonal).trigger("change");
            $ig.val(result.ig).trigger("change");
            $conc.val(result.conc);
            
            $targetType.val(result.targetTypeId).trigger("change");
            $target.val(result.target).trigger("change");
            $cterm.val(result.cterm).trigger("change");
            $nterm.val(result.nterm).trigger("change");
        }});   
    });
</script>