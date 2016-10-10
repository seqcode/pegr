<html>
    <head>
        <title>PEGR-Workbench</title>
        <meta name="layout" content="main"/>
        <style>
        .sample {
            background-image: none;
            background-color: #d9edf7;
        }
        .target {
            background-image: none;
            background-color: #FFD4CC;
        }
    </style>
    </head>
    <body>
        <h4>Create</h4>
        <g:form action="saveNewSamples" method="post">
    <div class="table-responsive">
        <table class="table table-striped table-bordered" style="margin-bottom: 200px">
            <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Barcode</th>
                    <th>Location</th>
                    <th>Notes</th>
                    <th>Genus</th>
                    <th>Species</th>
                    <th>Parent Strain</th>
                    <th>Strain</th>
                    <th>Genotype</th>
                    <th>Mutation</th>
                    <th>Tissue</th>
                    <th>Age</th>
                    <th>Sex</th>
                    <th>Histology</th>
                    <th>Sample Provider</th>
                    <th>Sample Provider Lab</th>
                    <th>Biological Source ID</th>
                </tr>
            </thead>
            <tbody>
                <tr id="tr0">
                    <td><a href="#" class="removeRow"><span class="glyphicon glyphicon-trash"></span></a></td>
                    <td><input name="samples[0].name"></td>
                    <td><g:select name="samples[0].itemTypeId" from="${pegr.ItemType.where{category.id == categoryId}.list()}" optionKey="id" noSelection="['':'']" class="no-tag-select2" style="width: 180px"></g:select></td>
                    <td></td>
                    <td><input name="samples[0].location"></td>
                    <td><g:textField name="samples[0].sampleNotes" style="width: 300px"></g:textField></td>
                    <td>
                        <select class="genus tag-select2" name="samples[0].genus" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="samples[0].speciesId" class="species tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="samples[0].parentStrain" class="parent-strain tag-select2" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="samples[0].strain" class="strain tag-select2 textcontrol" style="width: 150px" required>
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="samples[0].genotype" class="genotype tag-select2" style="width: 300px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <select name="samples[0].mutation" class="mutation tag-select2 textcontrol" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <g:select name="samples[0].tissue" from="${pegr.Tissue.list()}" optionKey="name" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td><input name="samples[0].age"></td>
                    <td>
                        <g:select name="samples[0].sex" from="${pegr.Sex.list()}" optionKey="name" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <g:select name="samples[0].histology" from="${pegr.Histology.list()}" optionKey="name" noSelection="['':'']" class="tag-select2" style="width: 150px"></g:select>
                    </td>
                    <td>
                        <select class="provider no-tag-select2" name="samples[0].providerId" style="width: 150px">
                            <option></option>
                        </select>
                    </td>
                    <td>
                        <g:select class="no-tag-select2" name="samples[0].providerLabId" from="${pegr.Lab.list()}" optionKey="id" noSelection="['':'']" style="width: 150px"></g:select>
                    </td>
                    <td><input name="samples[0].bioSourceId"></td>
                </tr>
            </tbody>
        </table>
    </div>
    
    <button id="add" class="pull-right">Add Row</button>
    <div>
        <g:submitButton name="save" class="btn btn-primary" value="Save"></g:submitButton>
        <g:link controller="item" action="list" params="[categoryId:categoryId]" class="btn btn-default">Cancel</g:link>
    </div>
</g:form>
    
<script>    
    var tagPlaceholder = "Select or type...";
    var noTagPlaceholder = "Select...";
    var count = 0;
    
    $(document).ready(function(){
        $("#nav-inventory").addClass("active");
        initializeSelect2s(count);
        $("form").validate();
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
        }});

        $.ajax({url: "/pegr/cellSource/fetchGenusAjax", success: function(result) {
            $("#tr"+count+" .genus").select2({
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

    // remove row
    $("a.removeRow").click(function(event){
        event.preventDefault();
        $(this).closest("tr").remove();
    });
    
    // add new row at the bottom and copy the value of last row
    $("#add").click(function() {
        var $orig = $("tr").last();
        count++;
        // disable select2
        $('select', $orig).each(function(index){
           $(this).select2("destroy");
        });
        // copy the row
        $('tbody').append(
            $('<tr/>')
                .attr("id", "tr"+count)
                .append(
                    $orig.children().clone(true))
        );
        var $cloned = $("#tr"+count)
        $('select, input', $cloned).each(function(index){
            // copy the values  
            $(this).val($('select, input', $orig).eq(index).val());
            // increase index in the names
            $(this).attr('name', function(index, name){
                return name.replace(/(\d+)/, count)
            });
        });
        // re-enable select2
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
        $("form").validate();

        return false;
    });
        </script>
    </body>
</html>