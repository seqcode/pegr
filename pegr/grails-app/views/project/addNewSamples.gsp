<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<g:form action="saveNewSample" method="post">
    <h4>Assay: ${assay?.name}</h4>
    <g:hiddenField name="assayId" value="${assay?.id}"></g:hiddenField>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#sample">Sample</a></li>
        <li><a data-toggle="tab" href="#antibody">Antibody</a></li>
    </ul>
    
    <div class="tab-content">
        <div id="sample" class="tab-pane slide in active table-responsive">
            <table class="table table-striped"  style="margin-bottom: 200px">
                <thead>
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
                        <th>Treatments <button data-toggle="modal" data-target="#new-treatment" class="edit"> <span class="glyphicon glyphicon-plus"></span> New</button></th>                       
                        <th>Chrom. (ug)</th>
                        <th>Cell# (M)</th>
                        <th>Volume (ul)</th>
                        <th>Reference Genome(s)</th>
                        <th>Notes</th> 
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td id="sample-counter">${counter}</td>
                        <td>
                            <g:select id="provider" name="provider" from="${pegr.User.list()}" noSelection="['': '']" class="direct-select2" style="width: 150px"></g:select>
                        </td>
                        <td>
                            <g:select id="sendTo" name="sendTo" from="${pegr.User.list()}" noSelection="['': '']" class="direct-select2" style="width: 150px"></g:select>
                        </td>
                        <td>
                            <select id="genus" name="genus" onchange="genusChanged(this.value);" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select id="species" name="species" onchange="speciesChanged(this.value);" class="direct-select2" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select id="parent-strain" name="parentStrain" onchange="parentStrainChanged(this.value);" class="direct-select2" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select id="strain" name="strain" onchange="strainChanged(this.value);" class="direct-select2" style="width: 150px" >
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select id="genotype" name="genotype" style="width: 300px">
                                <option></option>
                            </select></td>
                        <td>
                            <g:textField id="mutation" name="mutation" style="width: 150px">
                                <option></option>
                            </g:textField>
                        </td>
                        <td><g:select name="prepUser" from="${pegr.User.list()}" noSelection="['': '']" class="direct-select2" style="width: 150px" ></g:select></td>
                        <td>
                            <select id="growth-media" name="growthMedia" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                        <td><g:select multiple="multiple" name="treatments" from="${pegr.CellSourceTreatment.list(sort:'name')}" optionKey="id" class="direct-select2" style="width: 300px"></g:select></td>
                        <td><g:textField name="chrom"></g:textField></td>
                        <td><g:textField name="cellNum"></g:textField></td>
                        <td><g:textField name="volume"></g:textField></td>
                        <td><g:select name="genome.id" from="${pegr.Genome.list()}" noSelection="['': '']" multiple="multiple" class="direct-select2" style="width: 150px"></g:select></td>
                        <td><g:textField name="note" style="width: 500px"></g:textField></td>
                    </tr>    
                </tbody>
            </table>
        </div>

        <g:render template="/cellSource/treatmentModal"></g:render>
        <div id="antibody" class="tab-pane slide table-responsive">
            <table class="table table-striped" style="margin-bottom: 200px">
                <thead>
                    <tr>
                        <th></th>
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
                        <th>Target</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td id="antibody-counter">${counter}</td>
                        <td><g:select id="company" name="company.id" from="${pegr.Company.list()}" optionKey="id" value="${object?.company?.id}" noSelection="['': '']" class="select2-sample select2"/></td>
                        <td><g:textField name="catalogNumber" from="${[]}" class="select2-sample select2"/></td>
                        <td><g:textField name="lotNumber" value="${object?.lotNumber}"/></td>
                        <td><g:select id="abHost" name="abHost.id" from="${pegr.AbHost.list()}" optionKey="id" required="" value="${object?.abHost?.id}" class="select2-sample select2" noSelection="['': '']"/></td>
                        <td><g:textField name="immunogene" value="${object?.immunogene}"/></td>
                        <td><g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${object?.clonal?.name()}"  noSelection="['null': '']" /></td>
                        <td><g:select id="igType" name="igType.id" from="${pegr.IgType.list()}" optionKey="id" value="${object?.igType?.id}" noSelection="['': '']" class="select2-sample select2"/></td>
                        <td><g:field name="concentration" value="${fieldValue(bean: object, field: 'concentration')}" /></td>
                        <td><g:textField name="abNotes"></g:textField></td>
                        <td><g:textField name="abVolumePerSample"></g:textField></td>
                        <td><g:textField name="ugPerChip"></g:textField></td>
                        <td><g:textField name="ulPerChip"></g:textField></td>
                        <td><g:select name="target" from="${pegr.Target}" noSelection="['': '']"></g:select> </td>
                    </tr>    
                </tbody>
            </table>
        </div>
    </div>

    
    
    <button id="add" class="pull-right">Add Row</button>
    <div>
        <g:submitButton name="save" class="btn btn-primary confirm" value="Save"></g:submitButton>
        <g:link action="show" id="${project.id}" class="btn btn-default">Cancel</g:link>
    </div>
</g:form>
<script>
    var counter = 3
    $("#nav-projects").addClass("active");
    $(".confirm").confirm();
    $(".direct-select2").select2();
    
    $.ajax({url: "/pegr/cellSource/fetchGenusAjax", success: function(result) {
        $("#genus").select2({
            data: result
        });
    }})

    $("#species").prop("disabled", true);
    $("#parent-strain").prop("disabled", true);
    $("#strain").prop("disabled", true);
    $("#genotype").prop("disabled", true);
    $("#mutation").prop("disabled", true);
    $("#growth-media").prop("disabled", true);

    function genusChanged(genus) {
        $("#species").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchSpeciesAjax?genus="+genus, success: function(result){
            $("#species").select2({
                data: result
            });
        }});
        $("#species").prop("disabled", false);
    }
    
    function speciesChanged(speciesId) {
        $("#parent-strain").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchParentStrainAjax?speciesId="+speciesId, success: function(parents){
            $("#parent-strain").select2({
                data: parents
            });
        }});
        $("#parent-strain").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchGrowthMediaAjax?speciesId="+speciesId, success: function(medias){
            $("#growth-media").select2({
                data: medias
            });
        }});
    }
            
    function parentStrainChanged(parentStrain) {
        $("#strain").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchStrainNameAjax?parentStrain="+parentStrain, success: function(result){
            $("#strain").select2({
                data: result
            });
        }});
    }
    
    function strainChanged(strainName) {
        $("#genotype").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchGenotypeAjax?strainName="+strainName, success: function(result){
            $("#genotype").select2({
                data: result
            });
        }});
        $("#mutation").html('').select2({data: [{id: '', text: ''}]});
        $.ajax({url: "/pegr/cellSource/fetchMutationAjax?strainName="+strainName, success: function(result){
            $("#mutation").select2({
                data: result
            });
        }});
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