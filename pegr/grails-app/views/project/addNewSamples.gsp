<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<g:form action="saveNewSample" method="post">
    <h4>Assay: ${assay?.name}</h4>
    <g:hiddenField name="assayId" value="${assay?.id}"></g:hiddenField>
    <ul class="nav nav-pills">
        <li><a href="#sample-group" class="btn btn-default">Sample</a></li>
        <li><a href="#antibody-group" class="btn btn-default">Antibody</a></li>
        <li><a href="#target-group" class="btn btn-default">Target</a></li>
    </ul>
    <div class="table-responsive">
        <table class="table table-striped"  style="margin-bottom: 200px">
            <thead>
                <tr>
                    <th></th>
                    <th colspan="16" id="sample-group">Sample</th>
                    <th colspan="12" id="antibody-group">Antibody</th>
                    <th colspan="4" id="target-group">Target</th>
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
                <tr>
                    <td id="sample-counter"><button onclick="removeRow(this)"><span class="glyphicon glyphicon-trash"></span></button></td>
                    <td>
                        <select id="provider" name="provider" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="sendTo" name="sendTo" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="genus" name="genus" onchange="genusChanged(this.value);" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="species" name="species" onchange="speciesChanged(this.value);" class="tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="parent-strain" name="parentStrain" onchange="parentStrainChanged(this.value);" class="tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="strain" name="strain" onchange="strainChanged(this.value);" class="tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="genotype" name="genotype" onchange="genotypeChanged(this.value);" class="tag-select2" style="width: 300px">
                            <option></option>
                        </select></td>
                    <td>
                        <select id="mutation" name="mutation" class="tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="prepUser" name="prepUser" style="width: 150px" >
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="growth-media" name="growthMedia" class="tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select multiple="multiple" id ="treatments" name="treatments" style="width: 300px">
                            <option></option>
                        </select>
                    </td>
                    <td><g:textField name="chrom"></g:textField></td>
                    <td><g:textField name="cellNum"></g:textField></td>
                    <td><g:textField name="volume"></g:textField></td>
                    <td>
                        <select multiple="multiple" id="genomes" name="genomes" class="no-tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td><g:textField name="note" style="width: 500px"></g:textField></td>

                    <td>
                        <select id="company" name="companyId" style="width: 200px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="catalog" name="catalogNumber" class="tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <input name="lotNumber"/>
                    </td>
                    <td>
                        <g:select id="abHost" name="abHostId" from="${pegr.AbHost.list()}" optionKey="id" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select id="immunogene" name="immunogene" onchange="immunogeneChanged(this.value);" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" noSelection="['': '']" class="no-tag-select2" style="width: 150px"/>
                    </td>
                    <td>
                        <g:select id="igType" name="igType.id" from="${pegr.IgType.list()}" optionKey="id" noSelection="['': '']" class="tag-select2" style="width: 150px"/>
                    </td>
                    <td><input name="concentration" /></td>
                    <td><input name="abNotes"></td>
                    <td><input name="abVolumePerSample"></td>
                    <td><input name="ugPerChip"></td>
                    <td><input name="ulPerChip"></td>
                    <td>
                        <g:select id="target-type" name="targetTypeId" from="${pegr.TargetType.list()}" optionKey="id" noSelection="['': '']" onchange="targetTypeChanged(this.value);" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select id="target" name="target" class="tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="cterm" name="cterm" class="tag-select2" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select id="nterm" name="nterm" class="tag-select2" style="width: 150px">
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
    $("#nav-projects").addClass("active");
    $(".confirm").confirm();
    
    var counter = 1 // count of rows
    
    var tagPlaceholder = "Select or type..."
    var noTagPlaceholder = "Select..."
    
    $(".tag-select2").select2({
        placeholder: tagPlaceholder,
        tags: true
    });
    
    $(".no-tag-select2").select2({
        placeholder: noTagPlaceholder
    });
    
    $.ajax({url: "/pegr/cellSource/fetchUserAjax", success: function(result) {
        $("#provider").select2({
            data: result,
            placeholder: noTagPlaceholder
        });
        $("#sendTo").select2({
            data: result,
            placeholder: noTagPlaceholder
        });
        $("#prepUser").select2({
            data: result,
            placeholder: noTagPlaceholder
        });
    }})
        
    $.ajax({url: "/pegr/cellSource/fetchGenusAjax", success: function(result) {
        $("#genus").select2({
            data: result,
            tags: true,
            placeholder: tagPlaceholder
        });
    }})

    $.ajax({url: "/pegr/cellSource/fetchTreatmentsAjax", success: function(result) {
        $("#treatments").select2({
            data: result,
            tags: true,
            placeholder: tagPlaceholder
        });
    }})
    
    $.ajax({url: "/pegr/cellSource/fetchCompanyAjax", success: function(result) {
        $("#company").select2({
            data: result,
            tags: true,
            placeholder: tagPlaceholder
        });
    }})
    
    $.ajax({url: "/pegr/cellSource/fetchCatalogAjax", success: function(result){
        $("#catalog").select2({
            data: result,
            tags: true,
            placeholder: tagPlaceholder
        });
    }});
    
    $.ajax({url: "/pegr/cellSource/fetchImmunogeneAjax", success: function(result){
        $("#immunogene").select2({
            data: result,
            tags: true,
            placeholder: tagPlaceholder
        });
    }});
    
    $("#species").prop("disabled", true);
    $("#parent-strain").prop("disabled", true);
    $("#strain").prop("disabled", true);
    $("#genotype").prop("disabled", true);
    $("#mutation").prop("disabled", true);
    $("#growth-media").prop("disabled", true);
    $("#genomes").prop("disabled", true);
    $("#target-type").prop("disabled", true);
    $("#target").prop("disabled", true);
    $("#cterm").prop("disabled", true);
    $("#nterm").prop("disabled", true);
    
    function genusChanged(genus) {
        $("#species").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchSpeciesAjax?genus="+genus, success: function(result){
            $("#species").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#species").prop("disabled", false);
    }
    
    function speciesChanged(speciesId) {
        $("#parent-strain").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchParentStrainAjax?speciesId="+speciesId, success: function(parents){
            $("#parent-strain").select2({
                data: parents,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#parent-strain").prop("disabled", false);
        
        $("#growth-media").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        
        $.ajax({url: "/pegr/cellSource/fetchGrowthMediaAjax?speciesId="+speciesId, success: function(medias){
            $("#growth-media").select2({
                data: medias,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#growth-media").prop("disabled", false);
        
        $("#genomes").html('').select2({
            data: [{id: '', text: ''}],
        });
        $.ajax({url: "/pegr/cellSource/fetchGenomeAjax?speciesId="+speciesId, success: function(genomes){
            $("#genomes").select2({
                data: genomes,
                placeholder: noTagPlaceholder
            });
        }});
        $("#genomes").prop("disabled", false);
    }
            
    function parentStrainChanged(parentStrain) {
        $("#strain").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        var speciesId = $("#species").val()
        $.ajax({url: "/pegr/cellSource/fetchStrainNameAjax?parentStrain="+parentStrain+"&speciesId="+speciesId, success: function(result){
            $("#strain").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#strain").prop("disabled", false);
    }
    
    function strainChanged(strainName) {
        $("#genotype").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        $.ajax({url: "/pegr/cellSource/fetchGenotypeAjax?strainName="+strainName, success: function(result){
            $("#genotype").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#genotype").prop("disabled", false);
    }
    
    function genotypeChanged(genotype) {
        $("#mutation").html('').select2({
            data: [{id: '', text: ''}],
            tags: true,
            placeholder: tagPlaceholder
        });
        var strainName = $("#strain").val()
        $.ajax({url: "/pegr/cellSource/fetchMutationAjax?strainName="+strainName+"&genotype="+genotype, success: function(result){
            $("#mutation").select2({
                data: result,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#mutation").prop("disabled", false);
    }
    
    function immunogeneChanged(immunogene) {
        $.ajax({url: "/pegr/cellSource/fetchDefaultTargetAjax?immunogene="+immunogene, success: function(result){
            $("#target-type").val(result.targetTypeId);
            $("#target-type").prop("disabled", false);
            
            $("#target").val(result.target);
            $("#cterm").val(result.cterm);
            $("#nterm").val(result.nterm);
        }});
    }
                
    function targetTypeChanged(targetTypeId) {
        $.ajax({url: "/pegr/cellSource/fetchTargetAjax?targetTypeId="+targetTypeId, success: function(result){
            $("#target").select2({
                data: result.targets,
                tags: true,
                placeholder: tagPlaceholder
            });
            $("#nterm").select2({
                data: result.nterms,
                tags: true,
                placeholder: tagPlaceholder
            });
            $("#cterm").select2({
                data: result.cterms,
                tags: true,
                placeholder: tagPlaceholder
            });
        }});
        $("#target").prop("disabled", false);
        $("#nterm").prop("disabled", false);
        $("#cterm").prop("disabled", false);
    }
    
    $("#add").click(function() {
        $('#sample tbody>tr:last').clone(false).insertAfter('#sample tbody>tr:last');
        $('#antibody tbody>tr:last').clone(false).insertAfter('#antibody tbody>tr:last');
        $('#antibody tbody>tr:last #antibody-counter').text(++counter);
        $('#antibody tbody>tr:last .select2').remove();
        $('#antibody tbody>tr:last .select2').select2();
        return false;
    });

</script>
</body>
</html>