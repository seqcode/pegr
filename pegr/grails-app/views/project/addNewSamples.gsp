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
                        <th>Species <button data-toggle="modal" data-target="#new-species" class="edit"> <span class="glyphicon glyphicon-plus"></span> New</button></th>
                        <th>Parent Strain</th>
                        <th>Strain <button data-toggle="modal" data-target="#new-strain" class="edit"> <span class="glyphicon glyphicon-plus"></span> New</button></th>
                        <th>Genotype</th>
                        <th>Mutation</th>
                        <th>Prep User</th>
                        <th>Growth Media <button data-toggle="modal" data-target="#new-growth-media" class="edit"> <span class="glyphicon glyphicon-plus"></span> New</button></th>
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
                        <td><g:select name="provider" from="${pegr.User.list()}" noSelection="['': '']" class="select2" style="width: 150px"></g:select></td>
                        <td><g:select name="sendTo" from="${pegr.User.list()}" noSelection="['': '']" class="select2" style="width: 150px"></g:select></td>
                        <td><g:select name="species" from="${pegr.Species.list()}" noSelection="['': '']" class="select2" style="width: 250px"/></td>
                        <td><g:select name="parentStrain" from="${pegr.Strain.list()}" optionKey="id" noSelection="['': '']" class="select2"  style="width: 150px" /></td>
                        <td><g:select id="strain" name="strain" from="${pegr.Strain.list()}" noSelection="['': '']" onchange="strainChanged(this.value);" optionKey="id" class="select2" style="width: 150px"/></td>
                        <td><g:textField name="genotype"></g:textField></td>
                        <td><g:textField name="mutation"></g:textField></td>
                        <td><g:select name="prepUser" from="${pegr.User.list()}" noSelection="['': '']"  class="select2"  style="width: 150px" ></g:select></td>
                        <td><g:select id="growthMedia" name="growthMedia.id" from="${pegr.GrowthMedia.list()}" optionKey="id" noSelection="['': '']" class="select2" style="width: 150px"/></td>
                        <td><g:select multiple="multiple" name="treatments" from="${pegr.CellSourceTreatment.list(sort:'name')}" optionKey="id"   class="select2" style="width: 300px"></g:select></td>
                        <td><g:textField name="chrom"></g:textField></td>
                        <td><g:textField name="cellNum"></g:textField></td>
                        <td><g:textField name="volume"></g:textField></td>
                        <td><g:select name="genome.id" from="${pegr.Genome.list()}" noSelection="['': '']" class="select2" style="width: 150px"></g:select></td>
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
    
    $(function(){
        $("#nav-projects").addClass("active");
        $(".confirm").confirm();
        $(".select2").select2();
    });
    
    $("#add").click(function() {
        $('#sample tbody>tr:last').clone(false).insertAfter('#sample tbody>tr:last');
        $('#antibody tbody>tr:last').clone(false).insertAfter('#antibody tbody>tr:last');
        $('#antibody tbody>tr:last #antibody-counter').text(++counter);
        $('#antibody tbody>tr:last .select2').remove();
        $('#antibody tbody>tr:last .select2').select2();
        return false;
    });

    function strainChanged(strainId) {
        $.ajax({url: "/pegr/cellSource/showStrainDetailsAjax/"+strainId, success: function(result){
            $("#strainDetails").html(result)
        }});
    }
</script>
</body>
</html>