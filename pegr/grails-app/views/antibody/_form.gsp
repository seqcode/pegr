<%@ page import="pegr.Antibody" %>
<div class="${hasErrors(bean: antibody, field: 'company', 'error')} ">
	<label for="company">Company</label>
	<g:select class="company" name="company" from="[]" value="${antibody?.company}" noSelection="['': '']" style="width: 200px">
    </g:select>
</div>

<div class="${hasErrors(bean: antibody, field: 'catalogNumber', 'error')} ">
	<label for="catalogNumber">Catalog Number</label>
	<select class="catalog tag-select2 textcontrol" name="catalogNumber" value="${antibody?.catalogNumber}" style="width: 150px" required>
        <option></option>
    </select>
</div>

<div class="${hasErrors(bean: antibody, field: 'lotNumber', 'error')} ">
	<label for="lotNumber">Lot Number</label>
	<g:textField name="lotNumber" value="${antibody?.lotNumber}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'abHost', 'error')} required">
	<label for="abHost">Ab Host</label>
	<g:select id="abHost" name="abHostId" from="${pegr.AbHost.list()}" optionKey="id" noSelection="['':'']" class="tag-select2 host"  value="${antibody?.abHostId}"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'immunogene', 'error')} ">
	<label for="immunogene">Immunogene</label>
    <select class="immunogene tag-select2" name="immunogene" value="antibody?.immunogene" style="width: 150px">
        <option></option>
    </select>
</div>

<div class="${hasErrors(bean: antibody, field: 'clonal', 'error')} ">
	<label for="clonal">Mono/Poly Clonal</label>
	<g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${antibody?.clonal}"  noSelection="['': '']" class="no-tag-select2 clonal"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'igType', 'error')} ">
	<label for="igType">Ig Type</label>
	<g:select id="igType" name="igTypeId" from="${pegr.IgType.list()}" optionKey="id" value="${antibody?.igTypeId}" class="tag-select2 ig" noSelection="['null': '']"/>
</div>

<div class="${hasErrors(bean: antibody, field: 'concentration', 'error')} required">
	<label for="concentration">Concentration (ug/ul)</label>
	<g:textField name="concentration" value="${antibody?.concentration}" class="conc isnumber"/>
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
        
        // jquery validation
        jQuery.validator.addMethod("textrule", function(value, element) {
            return this.optional(element) || /^[a-zA-Z0-9-]+$/.test(value);
        }, "Only a-z, A-Z, 0-9 and '-' are allowed!");

        jQuery.validator.addClassRules({
            textcontrol: {
                textrule: true
            },
            isnumber: {
                number: true
            }
        });

        $("form").validate();
    });
    
    $(".catalog").on("change", function() {
        var catalog = $(this).val();
        
        var $host = $(".host");
        var $immunogene = $(".immunogene");
        var $clonal = $(".clonal");
        var $ig = $(".ig");
        var $conc = $(".conc");
      
        $.ajax({url: "/pegr/antibody/fetchAntibodyAjax?catalog="+catalog, success: function(result){
            $host.val(result.host).trigger("change");
            $immunogene.val(result.immunogene).trigger("change");
            $clonal.val(result.clonal).trigger("change");
            $ig.val(result.ig).trigger("change");
            $conc.val(result.conc);
        }});   
    });
</script>